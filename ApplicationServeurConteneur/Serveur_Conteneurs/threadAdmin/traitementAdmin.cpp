#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <sstream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>

using namespace std;

#include "../../Librairie/socket/socket.h"
#include "../../Librairie/exceptions/errnoException.h"
#include "../../LibrairieCSA/CSA.ini"
#include "../../Librairie/fichierProp/fichierProp.h"
#include "../../LibrairieCSA/sendCSAFunction.h"
#include "../../CommonProtocolFunction/commonFunction.h"
#include "../constante.h"
#include "traitementAdmin.h"

extern pthread_t threadsAdminLances[MAXADMIN];
extern Socket* socketAdminOuverte[MAXADMIN];
extern int threadsAdminLibres;

extern pthread_mutex_t mutexJobAdminDispo;
extern pthread_cond_t condJobAdminDispo;
extern int indiceThreadAdmin;

extern string status;
extern bool pause;

extern string listLoginClient[MAXCLIENT];

void* traitementAdmin(void* p)
{
	int requestType;
    while(1)
    {
        pthread_mutex_lock(&mutexJobAdminDispo);//On reste bloqué ici tant qu'il n'y a pas de nouveau client (indice courant à -1)
        while(indiceThreadAdmin == -1)
            pthread_cond_wait(&condJobAdminDispo, &mutexJobAdminDispo);

        int clientTraite = indiceThreadAdmin; //on récupère l'indice de notre client dans le tableau de socket ouverte pour pas le perdre
        indiceThreadAdmin = -1;//On remet à -1 pour éviter qu'un concurrent nous le pique.

        Socket* socketService = socketAdminOuverte[clientTraite]; //recuperation de la socket du tableau
        pthread_mutex_unlock(&mutexJobAdminDispo);

        string str = typeRequestParse(socketService->receiveChar(), &requestType);

        if(requestType != LOGIN)
        {
            socketService->sendChar(composeRequest(ERREUR, "NOT A LOGIN"));
            logout(clientTraite, socketService);
            continue;
        }

        StructLogin Slog = parseLogin(str);

        if(!login(Slog, socketService))
        {
            logout(clientTraite, socketService);
            continue;
        }
        
        bool cont = true;

        while(cont) //boucle sur les demandes du client
        {
            cout << "imhere" << endl;
            string str = typeRequestParse(socketService->receiveChar(), &requestType);
            cout << requestType << endl;
            switch(requestType)
            {
                case LISTCLIENT:
                    listClient(clientTraite, socketService);
                    break;
                case PAUSE:
                    break;
                case CONTINUER:
                    break;
                case STOP:
                    break;
                case LOGOUTCSA:
                    logout(clientTraite, socketService);
                    cont = false;
                    break;
            }
        }
    }
}


bool login(StructLogin log, Socket* s)
{
    FichierProp fp("loginAdmin.csv", ';');

    string test = fp.getValue(log.nom);

    if(!test.compare(""))   // Si chaine vide
    {
        s->sendChar(composeAckErr(ERREUR, "LOGERR"));
    }
    else
    {
        if(!test.compare(log.motDePasse))
        {
            s->sendChar(composeAckErr(ACK, "ALLRIGHT"));
            return true;
        }
        else
            s->sendChar(composeAckErr(ERREUR, "LOGERR"));            
    }
    return false;
}

void logout(int cTraite, Socket* s) //On déconnecte le client (on le fait pour LOGOUT ou en cas de problème)
{
    cout << "testificate"<<endl;
    s->sendChar(composeRequest(LOGOUTCSA, ""));
    s->finConnexion();
    delete s;

    pthread_mutex_lock(&mutexJobAdminDispo);
    socketAdminOuverte[cTraite] = NULL;

    threadsAdminLibres++;
    pthread_mutex_unlock(&mutexJobAdminDispo);
}

void listClient(int cTraite, Socket* s)
{
    string m = "";
    cout << "yes" << endl;
    for(int i = 0; i<MAXCLIENT; i++)
    {
        if(listLoginClient[i].compare("")) //Si la chaine n'est pas vide
            m += listLoginClient[i] + SEPARATION;
    }

    s->sendChar(composeAckErr(ACK, m));
}

