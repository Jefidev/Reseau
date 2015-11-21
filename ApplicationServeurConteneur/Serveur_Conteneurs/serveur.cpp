#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <signal.h>
#include <pthread.h>
#include <arpa/inet.h>
#include <unistd.h>

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/socket/socketClient.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../LibrairieConteneur/protocole.ini"
#include "../LibrairieConteneur/sendFunction.h"
#include "../Librairie/exceptions/errnoException.h"
#include "../Librairie/log/log.h"
#include "../CommonProtocolFunction/commonFunction.h"
#include "threadAdmin/threadAdmin.h"
#include "../Librairie/utility.h"
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
SocketClient* socketUrgence[MAXCLIENT];

//Liste des éléments à rollback en cas de coupure
string listContAdd[MAXCLIENT];

void* threadClient(void* p);
void finConnexion(int cTraite, Socket* s);
int login(Socket* s, int clientTraite);

void inputTruck(Socket*s, int clientTraite, string requete);
void inputDone(Socket*s, int clientTraite, string listContainer);

void outputReady(Socket* s, int clientTraite, string requete);
void outputOne(Socket* s, int clientTraite);

/*HANDLERS*/
void handlerPause(int);
void handlerCont(int);
void handlerInt(int);
void handlerPauseClient(int);
void handlerAlarm(int);

bool servInPause = false;
bool servShutdown = false;
int nbrSecBeforeShutdown = 0;

pthread_cond_t condSleepThread;
pthread_mutex_t mutexPause;

//Variable pour la communication avec le serveur trafic
int port_trafic;
string ip_trafic;
SocketClient* socketTrafic[MAXCLIENT];

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

    hand.sa_handler = handlerAlarm;
    sigemptyset(&hand.sa_mask);
    hand.sa_flags = 0;
    sigaction(SIGALRM, &hand, NULL);

    sigfillset(&masque); // on masque tout 
    sigdelset(&masque, SIGTSTP); //on demasque le signal de pause
    sigdelset(&masque, SIGCONT); //on demasque le signal continue (arret de pause)
    sigdelset(&masque, SIGINT); //on demasque le signal continue (arret de pause)
    sigdelset(&masque, SIGALRM);
    pthread_sigmask(SIG_SETMASK, &masque, NULL);

    //Lecture des informations dans le dossier properties
    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    port_trafic = atoi(fp.getValue("PORT_TRAFIC").c_str());
    ip_trafic = fp.getValue("IP_TRAFIC");

    pthread_cond_init(&condSleepThread, NULL);

    pthread_cond_init(&condIndiceCourant, NULL);
    pthread_mutex_init(&mutexIndiceCourant, NULL);
    pthread_mutex_init(&mutexLog, NULL);
    pthread_mutex_init(&mutexParc, NULL);
    pthread_mutex_init(&mutexPause, NULL);


    SocketServeur* sock = NULL; //On prépare une socket qui sera utilisée pour établir la connection.

    //affichage fichier parc 
    parcFile.afficheFichier();

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
        portUrgence[i] = 0;
        socketUrgence[i] = NULL;
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

        int clientTraite = indiceCourant; //on récupère l'indice de notre client dans le tableau de socket ouverte pour pas le perdre
        indiceCourant = -1;//On remet à -1 pour éviter qu'un concurrent nous le pique.

        Socket* socketService = socketOuverte[clientTraite]; //recuperation de la socket du tableau
        pthread_mutex_unlock(&mutexIndiceCourant);

        if(!login(socketService, clientTraite))
            continue;

        bool cont = true;

        //Connexion au serveur trafic

        if(ip_trafic == "localhost")
            socketTrafic[clientTraite] = new SocketClient(ip_trafic, port_trafic, false);
        else
            socketTrafic[clientTraite] = new SocketClient(ip_trafic, port_trafic, true);

        
        socketTrafic[clientTraite]->connecter();
        socketTrafic[clientTraite]->sendChar("LOGIN#serveur#serveur");

        string typeReponse;
        string reponse;

        reponse = typeRequestParse(socketTrafic[clientTraite]->receiveChar(), &typeReponse);

        if(typeReponse == "ERR")
        {
            cout << reponse << endl;
            exit(0);
        }

        while(cont) //boucle sur les demandes du client
        {
            string str;
            try
            {
                str = typeRequestParse(socketService->receiveChar(), &requestType);
            }
            catch(ErrnoException ex)
            {
                cout << ex.getErrorCode() << endl;
            }

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

    socketTrafic[cTraite]->sendChar("LOGOUT");
    socketTrafic[cTraite]->finConnexion();
    free(socketTrafic[cTraite]);

    s->sendChar(composeConnexion(LOGOUT, sc));
    s->finConnexion();
    delete s;

    pthread_mutex_lock(&mutexIndiceCourant);
    socketOuverte[cTraite] = NULL;
    listLoginClient[cTraite] = "";
    portUrgence[cTraite] =0;
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
        try
        {
            str = typeRequestParse(s->receiveChar(), &requestType);
        }
        catch(ErrnoException ex)
        {
            cout << ex.getErrorCode() << endl;
        }

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

            if(!test.compare(""))   // Si chaine vide
            {
                s->sendChar(composeAckErr(ERREUR, "LOGERR"));
            }
            else
            {
                cout << sc.motDePasse << endl;
                if(!test.compare(sc.motDePasse))
                {
                    listLoginClient[clientTraite] = sc.nom; //on recupere des infos sur le client.
                    portUrgence[clientTraite] = atoi(sc.port.c_str());
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
    string ret = "";
    bool erreur = false, cont = true;
    listContAdd[clientTraite] = "";

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

        listContAdd[clientTraite] = listContAdd[clientTraite] + ret;

        tok = strtok_r(NULL, &sep, &saveptr);

        if(tok != NULL)
            listContAdd[clientTraite] = listContAdd[clientTraite] + CONTAINER_SEPARATION;
    }

    if(erreur)
    {
        s->sendChar(composeAckErr(ERREUR, "Pas assez de place dans le parc"));

        if(listContAdd[clientTraite].compare("")) // liste non vide
        {
            pthread_mutex_lock(&mutexParc); //On libere les places qui étaient réservées dans le fichier
            parcFile.freeSpace(listContAdd[clientTraite]);
            pthread_mutex_unlock(&mutexParc);
        }
        return;
    }
    else
    {
        s->sendChar(composeAckErr(ACK, ""));
        inputDone(s, clientTraite, sit.idContainers);
    }
}


void inputDone(Socket*s, int clientTraite, string listContainer)
{
    char *lecContainer, *tokContainer, *saveptrContainer, *lecPosition, *tokPosition, *saveptrPosition;
    char sep = CONTAINER_SEPARATION;

    lecContainer =  new char [listContainer.length()+1];
    strcpy(lecContainer, listContainer.c_str());

    lecPosition =  new char [listContAdd[clientTraite].length()+1];
    strcpy(lecPosition, listContAdd[clientTraite].c_str());


    tokContainer = strtok_r(lecContainer, &sep, &saveptrContainer);
    tokPosition = strtok_r(lecPosition, &sep, &saveptrPosition);
    sleep(1);
    while(tokContainer != NULL)
    {
        string str;
        int requestType;
        StructInputDone sid;
        StructContainerPosition scp;

        scp.coord = tokPosition;
        scp.id = tokContainer;

        s->sendChar(composeContPos(CONT_POS, scp)); // Envoi de la position

        try
        {
            str = typeRequestParse(s->receiveChar(), &requestType); // Réception des données du container
        }
        catch(ErrnoException ex)
        {
            cout << ex.getErrorCode() << endl;
        }

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
            parcFile.freeSpace(listContAdd[clientTraite]);
            pthread_mutex_unlock(&mutexParc);
            finConnexion(clientTraite, s);
            return;
        }

        try
        {
            str = typeRequestParse(s->receiveChar(), &requestType); // Réception des données du container
        }
        catch(ErrnoException ex)
        {
            cout << ex.getErrorCode() << endl;
        }


        if(requestType != NEXT)
        {
            finConnexion(clientTraite, s);
            return;
        }

        tokContainer = strtok_r(NULL, &sep, &saveptrContainer);
        tokPosition = strtok_r(NULL, &sep, &saveptrPosition);
    }

    s->sendChar(composeAckErr(ACK, "Tous les containers ont ete traites"));
    listContAdd[clientTraite] = "";
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
    string reponse;
    try
    {
        reponse = typeRequestParse(s->receiveChar(), &requestType); // Réception des données du container
    }
    catch(ErrnoException ex)
    {
        cout << ex.getErrorCode() << endl;
    }
    
    while(requestType == OUTPUT_ONE)
    {
        StructOutputOne soo = parseOutputOne(reponse);

        pthread_mutex_lock(&mutexParc); 
        parcFile.freeSpace(soo.emplacement);
        pthread_mutex_unlock(&mutexParc);

        cout << "retire du fichier" << endl;

        s->sendChar(composeAckErr(ACK, "container retire"));
        try
        {
            reponse = typeRequestParse(s->receiveChar(), &requestType); // Réception des données du container
        }
        catch(ErrnoException ex)
        {
            cout << ex.getErrorCode() << endl;
        }
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

    socklen_t len;
    struct sockaddr_storage addr;
    char ipstr[INET6_ADDRSTRLEN];

    len = sizeof addr;

    for(int i = 0; i < MAXCLIENT; i++) // je préviens tous mes threads qu'il faut se mettre en pause
    {
        pthread_kill(threadsLances[i], SIGUSR1);
        
        if(portUrgence[i] == 0)
            continue;

        //du bordel pour recupérer l'IP du client
        getpeername(socketOuverte[i]->getSocketHandle(), (struct sockaddr*)&addr, &len);
        if (addr.ss_family == AF_INET) 
        {
            struct sockaddr_in *s = (struct sockaddr_in *)&addr;
            inet_ntop(AF_INET, &s->sin_addr, ipstr, sizeof ipstr);
        } 
        else 
        {
            struct sockaddr_in6 *s = (struct sockaddr_in6 *)&addr;
            inet_ntop(AF_INET6, &s->sin6_addr, ipstr, sizeof ipstr);
        }
        string tmp(ipstr);
        SocketClient* sc =  new SocketClient(tmp, portUrgence[i], true);
        sc->connecter();
        sc->sendChar("pause");
        sc->finConnexion();
    }

    sigwait(&masque, &sigRecu);

    cout <<"fini d'attendre"<<endl;
}

void handlerCont(int)
{
    servInPause = false;

    socklen_t len;
    struct sockaddr_storage addr;
    char ipstr[INET6_ADDRSTRLEN];

    len = sizeof addr;

    for(int i = 0; i < MAXCLIENT; i++) // je préviens tous mes threads qu'il faut se mettre en pause
    {
        pthread_cond_signal(&condSleepThread);

        if(portUrgence[i] == 0)
            continue;

        //du bordel pour recupérer l'IP du client
        getpeername(socketOuverte[i]->getSocketHandle(), (struct sockaddr*)&addr, &len);
        if (addr.ss_family == AF_INET) 
        {
            struct sockaddr_in *s = (struct sockaddr_in *)&addr;
            inet_ntop(AF_INET, &s->sin_addr, ipstr, sizeof ipstr);
        } 
        else 
        {
            struct sockaddr_in6 *s = (struct sockaddr_in6 *)&addr;
            inet_ntop(AF_INET6, &s->sin6_addr, ipstr, sizeof ipstr);
        }
        string tmp(ipstr);
        SocketClient* sc =  new SocketClient(tmp, portUrgence[i], true);
        sc->connecter();
        sc->sendChar("continuer");
        sc->finConnexion();
    }
    
}

void handlerInt(int)
{
    servShutdown = true;
    
    socklen_t len;
    struct sockaddr_storage addr;
    char ipstr[INET6_ADDRSTRLEN];

    len = sizeof addr;

    for(int i =0; i < MAXCLIENT; i++)
    {
        if(portUrgence[i] == 0)
            continue;

        //du bordel pour recupérer l'IP du client
        getpeername(socketOuverte[i]->getSocketHandle(), (struct sockaddr*)&addr, &len);
        if (addr.ss_family == AF_INET) 
        {
            struct sockaddr_in *s = (struct sockaddr_in *)&addr;
            inet_ntop(AF_INET, &s->sin_addr, ipstr, sizeof ipstr);
        } 
        else 
        {
            struct sockaddr_in6 *s = (struct sockaddr_in6 *)&addr;
            inet_ntop(AF_INET6, &s->sin6_addr, ipstr, sizeof ipstr);
        }
        string tmp(ipstr);
        socketUrgence[i] =  new SocketClient(tmp, portUrgence[i], true);
        socketUrgence[i]->connecter();
        socketUrgence[i]->sendChar("Le serveur va s'arreter dans " + Utility::intToString(nbrSecBeforeShutdown) + " secondes");
    }
    cout << "lancement alarm" << endl;
    alarm(1);
}

void handlerPauseClient(int)
{
    cout << " je me met en wait" << endl;
    pthread_cond_wait(&condSleepThread, &mutexPause);
    cout << " je sors du wait" << endl;
}

void handlerAlarm(int)
{
    if(nbrSecBeforeShutdown > 0)
        nbrSecBeforeShutdown--;

    cout << "arret dans " << nbrSecBeforeShutdown << endl;

    if(nbrSecBeforeShutdown != 0)
    {
        for(int i = 0; i <MAXCLIENT; i++)
        {
            if(socketUrgence[i] != NULL)
                socketUrgence[i]->sendChar(Utility::intToString(nbrSecBeforeShutdown) + " secondes");
        }
        alarm(1);
        return;
    }

    pthread_mutex_lock(&mutexParc);
    for(int i = 0; i <MAXCLIENT; i++)
    {
        if(socketUrgence[i] != NULL)
            socketUrgence[i]->sendChar("Serveur arrete");

        if(listContAdd[i] != "")
            parcFile.freeSpace(listContAdd[i]);

    }
    pthread_mutex_unlock(&mutexParc);

    exit(1);
}

