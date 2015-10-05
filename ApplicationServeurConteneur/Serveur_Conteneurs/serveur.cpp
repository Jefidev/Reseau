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
#include "../Librairie/log/log.h"
#include "parc.h"

#define MAXCLIENT 2


pthread_mutex_t mutexIndiceCourant;
pthread_cond_t condIndiceCourant;
int indiceCourant = -1;

pthread_mutex_t mutexThreadsLibres;
pthread_cond_t condThreadsLibres;
int threadsLibres = MAXCLIENT;

pthread_mutex_t mutexLog;
Log logFile("logServeur.txt");

pthread_mutex_t mutexParc;
Parc parcFile("parc.dat");

pthread_t threadsLances[MAXCLIENT];
Socket* socketOuverte[MAXCLIENT];

void* threadClient(void* p);
void finConnexion(int cTraite, Socket* s);
int login(Socket* s, int clientTraite);

void inputTruck(Socket*s, int clientTraite, string requete);
void inputDone(Socket*s, int clientTraite, string requete, string listContainer, string listPosition);

int main()
{

    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    pthread_cond_init(&condIndiceCourant, NULL);
    pthread_mutex_init(&mutexIndiceCourant, NULL);
    pthread_mutex_init(&mutexLog, NULL);
    pthread_mutex_init(&mutexParc, NULL);

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
        cout << threadsLibres << endl;
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
    int requestType;
    while(1)
    {
        pthread_mutex_lock(&mutexIndiceCourant);
        while(indiceCourant == -1)
            pthread_cond_wait(&condIndiceCourant, &mutexIndiceCourant);

        int clientTraite = indiceCourant;
        indiceCourant = -1;

        Socket* socketService = socketOuverte[clientTraite];
        pthread_mutex_unlock(&mutexIndiceCourant);

        if(!login(socketService, clientTraite))
            continue;

        bool cont = true;

        while(cont)
        {

            string str = typeRequestParse(socketService->receiveChar(), &requestType);

            switch(requestType)
            {
                case INPUT_TRUCK:
                    inputTruck(socketService, clientTraite, str);
                    break;
                case OUTPUT_READY:
                    break;
                case LOGOUT:
                    cont = false;
                    finConnexion(clientTraite, socketService);
                    break;
            }
        }
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


int login(Socket* s, int clientTraite)
{
    string str;
    int requestType;
    while(1)
    {
        str = typeRequestParse(s->receiveChar(), &requestType);

        if(requestType != LOGIN)
        {
            s->sendChar(composeAckErr(ERREUR, "INVALIDE"));

            finConnexion(clientTraite, s);
            return 0;
        }

        if(requestType == LOGIN)
        {   
            StructConnexion sc;
            FichierProp fp("login.csv", ';');


            sc = parseConnexion(str);

            string test = fp.getValue(sc.nom);

            if(!test.compare("#"))
            {
                s->sendChar(composeAckErr(ERREUR, "LOGERR"));
            }
            else
            {
                cout << sc.motDePasse << endl;
                if(!test.compare(sc.motDePasse))
                {
                    s->sendChar(composeAckErr(ACK, "ALLRIGHT"));
                    return 1;
                }
                else
                    s->sendChar(composeAckErr(ERREUR, "LOGERR"));
                
            }
        }
        else if(requestType == LOGOUT)
        {
            finConnexion(clientTraite, s);
            return 0;
        }
    }
}


void inputTruck(Socket*s, int clientTraite, string requete)
{
    StructInputTruck sit = parseInputTruck(requete);
    char *lec, *tok;
    char sep = CONTAINER_SEPARATION;
    char* saveptr;
    string retPosition = "", ret = "";
    bool erreur = false, cont = true;

    lec =  new char [sit.idContainers.length()+1];
    strcpy(lec, sit.idContainers.c_str());

    tok = strtok_r(lec, &sep, &saveptr);

    while(tok != NULL)
    {
        pthread_mutex_lock(&mutexParc);
        ret = parcFile.getFirstFree(); //Renvois les coord du premier emplacement libre sous forme x;y
        pthread_mutex_unlock(&mutexParc);

        if(!ret.compare(""))
        {
            erreur = true;
            break;
        }

        retPosition = retPosition + ret;

        tok = strtok_r(NULL, &sep, &saveptr);

        if(tok != NULL)
            retPosition = retPosition + CONTAINER_SEPARATION;

    }

    if(erreur)
    {
        s->sendChar(composeAckErr(ERREUR, "Pas assez de place dans le parc"));

        if(retPosition.compare(""))
        {
            pthread_mutex_lock(&mutexParc); //On libere les places qui étaient réservées dans le fichier
            parcFile.freeSpace(retPosition);
            pthread_mutex_unlock(&mutexParc);
        }
        return;
    }
    else
    {
        s->sendChar(composeAckErr(ACK, retPosition));

        string str;
        int requestType;

        str = typeRequestParse(s->receiveChar(), &requestType);

        if(requestType == INPUT_DONE)
        {
            inputDone(s, clientTraite, str, requete, retPosition);
        }
        else
        {
            pthread_mutex_lock(&mutexParc); //On libere les places qui étaient réservées dans le fichier
            parcFile.freeSpace(retPosition);
            pthread_mutex_unlock(&mutexParc);
            finConnexion(clientTraite, s);
            return;
        }
    }

}


void inputDone(Socket*s, int clientTraite, string requete, string listContainer, string listPosition)
{
    char *lec, *tok, *saveptr;
    char sep = CONTAINER_SEPARATION;

    tok = strtok_r(lec, &sep, &saveptr);

    while(tok != NULL)
    {
        
    }
}

