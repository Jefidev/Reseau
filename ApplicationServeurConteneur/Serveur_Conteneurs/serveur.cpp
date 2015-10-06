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
void inputDone(Socket*s, int clientTraite, string listContainer, string listPosition);

void outputReady(Socket* s, int clientTraite, string requete);

int main()
{
    //Lecture des informations dans le dossier properties
    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    pthread_cond_init(&condIndiceCourant, NULL);
    pthread_mutex_init(&mutexIndiceCourant, NULL);
    pthread_mutex_init(&mutexLog, NULL);
    pthread_mutex_init(&mutexParc, NULL);

    SocketServeur* sock = NULL; //On prépare une socket qui sera utilisée pour établir la connection.

    try
    {

        if(isip == "1")
            sock = new SocketServeur(host , atoi(port.c_str()), true); //Si dans le fichier prop on a une IP
        else
            sock = new SocketServeur(host , atoi(port.c_str()), false); // Si dans le fichier prop on a un hostname
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "------" << er.getMessage() << endl;
        exit(-1);
    }

    for(int i = 0; i < MAXCLIENT; i++)//On met la liste des sockets ouvertes à NULL
        socketOuverte[i] = NULL; 

    //LANCEMENT DES THREADS
    for(int i = 0; i < MAXCLIENT; i++)
    {
        int ret = pthread_create(&threadsLances[i], NULL, threadClient, (void*) i);
        
        pthread_detach(threadsLances[i]);
    }

    //Choses sérieuses
    while(1)
    {   
        try
        {
            sock->ecouter(); //On se met à l'écoute d'une requête cliente
        }
        catch(ErrnoException er)
        {
            cout << er.getErrorCode() << "------" << er.getMessage() << endl;
            exit(-1);
        }

        pthread_mutex_lock(&mutexThreadsLibres);//On se met en attente sur une variable de condition : pas d'accept si pas de thread libre
        while(threadsLibres == 0)
            pthread_cond_wait(&condThreadsLibres, &mutexThreadsLibres);

        int service = sock->accepter(); //On a un thread libre donc on peut accept

        int j;

        for(j = 0; j < MAXCLIENT && socketOuverte[j] != NULL; j++); //On parcours nos thread pour trouver un libre

        threadsLibres--;
        pthread_mutex_unlock(&mutexThreadsLibres);

        //section critique. On peut pas avoir deux threads qui lisent ces données en meme temps
        pthread_mutex_lock(&mutexIndiceCourant);
        socketOuverte[j] = new Socket(service); //creation d'une nouvelle socket service
        indiceCourant=j; //on met la variable indice courant à la position de la socket créée pour que le thread puisse savoir laquelle prendre
        pthread_mutex_unlock(&mutexIndiceCourant);
        pthread_cond_signal(&condIndiceCourant); //On réveil le thread au chômage
        
    }

}

void* threadClient(void* p) //le thread lancé
{
    int requestType;
    while(1)
    {
        pthread_mutex_lock(&mutexIndiceCourant);//On reste bloqué ici tant qu'il n'y a pas de nouveau client (indice courant à -1)
        while(indiceCourant == -1)
            pthread_cond_wait(&condIndiceCourant, &mutexIndiceCourant);

        int clientTraite = indiceCourant; //on récupère l'indice de notre client dans le tableau de socket ouverte pour pas le perdre
        indiceCourant = -1;//On remet à -1 pour éviter qu'un concurrent nous le pique.

        Socket* socketService = socketOuverte[clientTraite]; //recuperation de la socket du tableau
        pthread_mutex_unlock(&mutexIndiceCourant);

        if(!login(socketService, clientTraite))
            continue;

        bool cont = true;

        while(cont) //boucle sur les demandes du client
        {

            string str = typeRequestParse(socketService->receiveChar(), &requestType);

            switch(requestType)
            {
                case INPUT_TRUCK:
                    inputTruck(socketService, clientTraite, str);
                    break;
                case OUTPUT_READY:
                    outputReady(socketService, clientTraite, str);
                    break;
                case LOGOUT:
                    cont = false;
                    finConnexion(clientTraite, socketService);
                    break;
            }
        }
    }
}

void finConnexion(int cTraite, Socket* s) //On déconnecte le client (on le fait pour LOGOUT ou en cas de problème)
{
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
        inputDone(s, clientTraite, sit.idContainers, retPosition);
    }
}


void inputDone(Socket*s, int clientTraite, string listContainer, string listPosition)
{
    char *lecContainer, *tokContainer, *saveptrContainer, *lecPosition, *tokPosition, *saveptrPosition;
    char sep = CONTAINER_SEPARATION;

    lecContainer =  new char [listContainer.length()+1];
    strcpy(lecContainer, listContainer.c_str());

    lecPosition =  new char [listPosition.length()+1];
    strcpy(lecPosition, listPosition.c_str());


    tokContainer = strtok_r(lecContainer, &sep, &saveptrContainer);
    tokPosition = strtok_r(lecPosition, &sep, &saveptrPosition);

    while(tokContainer != NULL)
    {
        string str;
        int requestType;
        StructInputDone sid;

        sid.coord = tokPosition;
        sid.id = tokContainer;

        s->sendChar(composeInputDone(INPUT_DONE, sid));

        str = typeRequestParse(s->receiveChar(), &requestType);

        if(requestType == INPUT_DONE)
        {

            sid = parseInputDone(str);

            if(sid.poids > 100)
            {   
                pthread_mutex_lock(&mutexParc);
                parcFile.freeSpace(sid.coord);
                pthread_mutex_unlock(&mutexParc);
                s->sendChar(composeAckErr(ERREUR, "Le container est trop lourd : enregistrement annule"));
            }
            else
            {
                pthread_mutex_lock(&mutexParc);
                parcFile.placeContainer(sid);
                pthread_mutex_unlock(&mutexParc);
                s->sendChar(composeAckErr(ACK, "Le container a ete enregistre"));
            }
            
        }
        else
        {
            pthread_mutex_lock(&mutexParc); //On libere les places qui étaient réservées dans le fichier
            parcFile.freeSpace(listPosition);
            pthread_mutex_unlock(&mutexParc);
            finConnexion(clientTraite, s);
            return;
        }

        str = typeRequestParse(s->receiveChar(), &requestType);

        if(requestType != NEXT)
        {
            finConnexion(clientTraite, s);
            return;
        }

        tokContainer = strtok_r(NULL, &sep, &saveptrContainer);
        tokPosition = strtok_r(NULL, &sep, &saveptrPosition);

    }

    s->sendChar(composeAckErr(ACK, "Tous les containers ont ete traites"));
        
}


void outputReady(Socket* s, int clientTraite, string requete)
{
    //StructOuputReady sor = parseOutputReady(requete);

    cout << requete << endl;
}
