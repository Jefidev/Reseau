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
#include "../../LibrairieCSA/sendCSAFunction.h"
#include "traitementAdmin.h"

extern pthread_t threadsAdminLances[MAXADMIN];
extern Socket* socketAdminOuverte[MAXADMIN];
extern int threadsAdminLibres;

extern pthread_mutex_t mutexJobAdminDispo;
extern pthread_cond_t condJobAdminDispo;
extern int indiceThreadAdmin;


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

        
        bool cont = true;
    }
}


