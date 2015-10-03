#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <pthread.h>

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../LibrairieConteneur/protocole.ini"
#include "../Librairie/exceptions/errnoException.h"
#include "../LibrairieConteneur/sendFunction.h"

#define MAXCLIENT 2


pthread_mutex_t mutexIndiceCourant;
pthread_cond_t condIndiceCourant;
int indiceCourant = -1;

pthread_mutex_t mutexThreadsLibres;
pthread_cond_t condThreadsLibres;
int threadsLibres = MAXCLIENT;

pthread_t threadsLances[MAXCLIENT];
Socket* socketOuverte[MAXCLIENT];

void* threadClient(void* p);
void finConnexion(int cTraite, Socket* s);
void login(Socket* s, int clientTraite);

int main()
{

    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    pthread_cond_init(&condIndiceCourant, NULL);
    pthread_mutex_init(&mutexIndiceCourant, NULL);

    SocketServeur* sock = NULL;

    try
    {

        if(isip == "1")
            sock = new SocketServeur(host , atoi(port.c_str()), true);
        else
            sock = new SocketServeur(host , atoi(port.c_str()), false);
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "------" << er.getMessage() << endl;
        exit(-1);
    }

    for(int i = 0; i < MAXCLIENT; i++)
        socketOuverte[i] = NULL; 

    //LANCEMENT DES THREADS
    for(int i = 0; i < MAXCLIENT; i++)
    {
        int ret = pthread_create(&threadsLances[i], NULL, threadClient, (void*) i);
        
        pthread_detach(threadsLances[i]);
    }

    while(1)
    {   
        try
        {
            sock->ecouter();
        }
        catch(ErrnoException er)
        {
            cout << er.getErrorCode() << "------" << er.getMessage() << endl;
            exit(-1);
        }

        pthread_mutex_lock(&mutexThreadsLibres);
        while(threadsLibres == 0)
            pthread_cond_wait(&condThreadsLibres, &mutexThreadsLibres);

        int service = sock->accepter();

        int j;

        for(j = 0; j < MAXCLIENT && socketOuverte[j] != NULL; j++);

        threadsLibres--;
        pthread_mutex_unlock(&mutexThreadsLibres);

        pthread_mutex_lock(&mutexIndiceCourant);
        socketOuverte[j] = new Socket(service);
        indiceCourant=j;
        pthread_mutex_unlock(&mutexIndiceCourant);
        pthread_cond_signal(&condIndiceCourant);
        
    }

}

void* threadClient(void* p)
{
    while(1)
    {
        pthread_mutex_lock(&mutexIndiceCourant);
        while(indiceCourant == -1)
            pthread_cond_wait(&condIndiceCourant, &mutexIndiceCourant);

        cout << "Au boulot" << endl;

        int clientTraite = indiceCourant;
        indiceCourant = -1;

        Socket* socketService = socketOuverte[clientTraite];
        pthread_mutex_unlock(&mutexIndiceCourant);

        login(socketService, clientTraite);

        finConnexion(clientTraite, socketService);

    }
}

void finConnexion(int cTraite, Socket* s)
{
    cout << "degage" << endl;

    StructConnexion sc;

    s->sendChar(composeConnexion(LOGOUT, sc));
    s->finConnexion();
    delete s;

    pthread_mutex_lock(&mutexIndiceCourant);
    socketOuverte[cTraite] = NULL;
    pthread_mutex_unlock(&mutexIndiceCourant);

    pthread_mutex_lock(&mutexThreadsLibres);
    threadsLibres++;
    pthread_mutex_unlock(&mutexThreadsLibres);
    pthread_cond_signal(&condThreadsLibres);
}


void login(Socket* s, int clientTraite)
{
    string str;
    int requestType, nbrReq = 0;

    FichierProp fp("login.csv", ';');

    while(1)
    {
        str = typeRequestParse(s->receiveChar(), &requestType);

        if(requestType != LOGIN)
        {
            s->sendChar(composeAckErr(ERREUR, "INVALIDE"));

            finConnexion(clientTraite, s);
        }

        if(requestType == LOGIN)
        {   
            StructConnexion sc;

            sc = parseConnexion(str);

            string test = fp.getValue(sc.nom);

            if(!test.compare("#"))
            {
                s->sendChar(composeAckErr(ERREUR, "LOGERR"));
                nbrReq++;
            }
            else
            {
                cout << sc.motDePasse << endl;
                if(!test.compare(sc.motDePasse))
                {
                    s->sendChar(composeAckErr(ACK, "ALLRIGHT"));
                    return;
                }
                else
                {
                    s->sendChar(composeAckErr(ERREUR, "LOGERR"));
                    nbrReq++;
                }
            }
        }
        else if(requestType == LOGOUT)
        {
            finConnexion(clientTraite, s);
        }
    }
}

