#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <signal.h>
#include <pthread.h>

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../LibrairieConteneur/protocole.ini"
#include "../LibrairieConteneur/sendFunction.h"
#include "../Librairie/exceptions/errnoException.h"
#include "../Librairie/log/log.h"
#include "../CommonProtocolFunction/commonFunction.h"
#include "threadAdmin/threadAdmin.h"
#include "parc.h"
#include "constante.h"

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
string listLoginClient[MAXCLIENT];

int portUrgence[MAXCLIENT];

void* threadClient(void* p);
void finConnexion(int cTraite, Socket* s);
int login(Socket* s, int clientTraite);

void inputTruck(Socket*s, int clientTraite, string requete);
void inputDone(Socket*s, int clientTraite, string listContainer, string listPosition);

void outputReady(Socket* s, int clientTraite, string requete);
void outputOne(Socket* s, int clientTraite);

/*HANDLERS*/
void handlerPause(int);
void handlerCont(int);
void handlerInt(int);
void handlerPauseClient(int);

bool servInPause = false;
bool servShutdown = false;
int nbrSecBeforeShutdown = 0;

pthread_cond_t condSleepThread;

int main()
{
    /*MISE EN PLACE DES SIGNAUX*/
    struct sigaction hand;
    sigset_t masque;

    hand.sa_handler = handlerPause;
    sigemptyset(&hand.sa_mask);
    hand.sa_flags = 0;
    sigaction(SIGTSTP, &hand, NULL);

    hand.sa_handler = handlerCont;
    sigemptyset(&hand.sa_mask);
    hand.sa_flags = 0;
    sigaction(SIGCONT, &hand, NULL);

    hand.sa_handler = handlerInt;
    sigemptyset(&hand.sa_mask);
    hand.sa_flags = 0;
    sigaction(SIGINT, &hand, NULL);

    hand.sa_handler = handlerPauseClient;
    sigemptyset(&hand.sa_mask);
    hand.sa_flags = 0;
    sigaction(SIGUSR1, &hand, NULL);

    sigfillset(&masque); // on masque tout 
    sigdelset(&masque, SIGTSTP); //on demasque le signal de pause
    sigdelset(&masque, SIGCONT); //on demasque le signal continue (arret de pause)
    sigdelset(&masque, SIGINT); //on demasque le signal continue (arret de pause)
    pthread_sigmask(SIG_SETMASK, &masque, NULL);

    //Lecture des informations dans le dossier properties
    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    pthread_cond_init(&condSleepThread, NULL);

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

    //LANCEMENT THREAD ADMIN
    pthread_t admin;
    int testificate = pthread_create(&admin, NULL, threadAdmin, NULL);

    //LANCEMENT DES THREADS + INIT LIST CLIENT
    for(int i = 0; i < MAXCLIENT; i++)
    {
        int ret = pthread_create(&threadsLances[i], NULL, threadClient, (void*) i);
        pthread_detach(threadsLances[i]);

        listLoginClient[i] = "";
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

        int service;
        try
        {
            service = sock->accepter(); //On a un thread libre donc on peut accept
        }
        catch(ErrnoException er)
        {
            if(er.getErrorCode() == 4)
                continue;
        }

        cout << "Nouveau client accepte " << endl;

        int j;

        for(j = 0; j < MAXCLIENT && socketOuverte[j] != NULL; j++); //On parcourt nos threads pour trouver un libre

        threadsLibres--;
        pthread_mutex_unlock(&mutexThreadsLibres);

        //section critique. On peut pas avoir deux threads qui lisent ces données en meme temps
        pthread_mutex_lock(&mutexIndiceCourant);
        socketOuverte[j] = new Socket(service); //creation d'une nouvelle socket service
        indiceCourant=j; //on met la variable indice courant à la position de la socket créée pour que le thread puisse savoir laquelle prendre
        pthread_mutex_unlock(&mutexIndiceCourant);
        pthread_cond_signal(&condIndiceCourant); //On réveille le thread au chômage
    }
}

void* threadClient(void* p) //le thread lancé
{
    int requestType;
    sigset_t masque;

    //Mise en place des masques

    sigfillset(&masque); // on masque tout 
    sigdelset(&masque, SIGUSR1); //on demasque le signal sigusr1 (pause)
    sigdelset(&masque, SIGUSR2); //arret/continue à voir
    pthread_sigmask(SIG_SETMASK, &masque, NULL);

    while(1)
    {
        pthread_mutex_lock(&mutexIndiceCourant);//On reste bloqué ici tant qu'il n'y a pas de nouveau client (indice courant à -1)
        while(indiceCourant == -1)
            pthread_cond_wait(&condIndiceCourant, &mutexIndiceCourant);

        cout << "je m'echappe" << endl;

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
    listLoginClient[cTraite] = "";
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

            cout << "trame recue : " << str;
            sc = parseConnexion(str);

            string test = fp.getValue(sc.nom);

            if(!test.compare(""))   // Si chaine vide
            {
                s->sendChar(composeAckErr(ERREUR, "LOGERR"));
            }
            else
            {
                cout << sc.motDePasse << endl;
                if(!test.compare(sc.motDePasse))
                {
                    listLoginClient[clientTraite] = sc.nom;
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
        ret = parcFile.getFirstFree(); //Renvoie les coord du premier emplacement libre sous forme x;y
        pthread_mutex_unlock(&mutexParc);

        if(!ret.compare(""))    // Plus assez de place
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

        if(retPosition.compare("")) // retPosition non vide
        {
            pthread_mutex_lock(&mutexParc); //On libere les places qui étaient réservées dans le fichier
            parcFile.freeSpace(retPosition);
            pthread_mutex_unlock(&mutexParc);
        }
        return;
    }
    else
    {
        s->sendChar(composeAckErr(ACK, ""));
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
        StructContainerPosition scp;

        scp.coord = tokPosition;
        scp.id = tokContainer;

        s->sendChar(composeContPos(CONT_POS, scp)); // Envoi de la position

        str = typeRequestParse(s->receiveChar(), &requestType); // Réception des données du container

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
        else    // On annule tout et on demande au client de se couper car problème sur le réseau
        {
            pthread_mutex_lock(&mutexParc); //On libere les places qui étaient réservées dans le fichier
            parcFile.freeSpace(listPosition);
            pthread_mutex_unlock(&mutexParc);
            finConnexion(clientTraite, s);
            return;
        }

        str = typeRequestParse(s->receiveChar(), &requestType); // Attend un NEXT du client (accord)

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
    StructOuputReady sor = parseOutputReady(requete);

    pthread_mutex_lock(&mutexParc); 
    string listeContainer = parcFile.outputList(sor);
    pthread_mutex_unlock(&mutexParc);

    if(listeContainer.compare(""))  // Si chaine remplie
    {
        s->sendChar(composeAckErr(ACK, listeContainer));
        outputOne(s, clientTraite);
    }
    else
        s->sendChar(composeAckErr(ERREUR, "Aucun containers pour cette destination"));
}

void outputOne(Socket* s, int clientTraite)
{
    int requestType;
    string reponse = typeRequestParse(s->receiveChar(), &requestType);
    
    while(requestType == OUTPUT_ONE)
    {
        StructOutputOne soo = parseOutputOne(reponse);

        pthread_mutex_lock(&mutexParc); 
        parcFile.freeSpace(soo.emplacement);
        pthread_mutex_unlock(&mutexParc);

        cout << "retire du fichier" << endl;

        s->sendChar(composeAckErr(ACK, "container retire"));

        reponse = typeRequestParse(s->receiveChar(), &requestType);
    }

    if(requestType != OUTPUT_DONE)
    {
        finConnexion(clientTraite, s);
    }

    s->sendChar(composeAckErr(ACK, "les containers ont bien ete retires"));
}

void handlerPause(int)
{
    sigset_t masque;
    sigfillset(&masque); 
    sigdelset(&masque, SIGCONT); // on met en place un masque aurorisant les signaux permettant la sortie de pause.

    int sigRecu;

    servInPause = true;
    
    for(int i = 0; i < MAXCLIENT; i++) // je préviens tous mes threads qu'il faut se mettre en pause
        pthread_kill(threadsLances[i], SIGUSR1);

    sigwait(&masque, &sigRecu);

    cout <<"fini d'attendre"<<endl;
}

void handlerCont(int)
{
    for(int i = 0; i < MAXCLIENT; i++) // je préviens tous mes threads qu'il faut se mettre en pause
        pthread_cond_signal(&condSleepThread);

    servInPause = false;
}

void handlerInt(int)
{
    servShutdown = true;
    cout << nbrSecBeforeShutdown << endl;
}

void handlerPauseClient(int)
{
    pthread_cond_wait(&condSleepThread, NULL); //NOTE pas tip top à changer si possible.
}
