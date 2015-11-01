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
#include <pthread.h>

using namespace std;

#include "../../Librairie/socket/socket.h"
#include "../../Librairie/socket/socketServeur.h"
#include "../../Librairie/fichierProp/fichierProp.h"
#include "../../Librairie/exceptions/errnoException.h"
#include "../../LibrairieCSA/CSA.ini"
#include "../../LibrairieCSA/sendCSAFunction.h"
#include "traitementAdmin.h"
#include "threadAdmin.h"

pthread_t threadsAdminLances[MAXADMIN];
Socket* socketAdminOuverte[MAXADMIN];
int threadsAdminLibres = MAXADMIN;

pthread_mutex_t mutexJobAdminDispo;
pthread_cond_t condJobAdminDispo;
int indiceThreadAdmin = -1;


void* threadAdmin(void* p)
{
	pthread_cond_init(&condJobAdminDispo, NULL);
    pthread_mutex_init(&mutexJobAdminDispo, NULL);

	FichierProp fp = FichierProp("properties.txt");

	SocketServeur* sock = NULL; //Socket qui sera en attente d'une connexion admin

	string host = fp.getValue("HOST");
    string port = fp.getValue("PORT_ADMIN");
    string isip = fp.getValue("ISIP");

    cout << port << endl;

    try
    {

        if(isip == "1")
            sock = new SocketServeur(host , atoi(port.c_str()), true); //Si dans le fichier prop on a une IP
        else
            sock = new SocketServeur(host , atoi(port.c_str()), false); // Si dans le fichier prop on a un hostname
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "THREAD ADMIN ------> " << er.getMessage() << endl;
        exit(-1);
    }

    /**LANCEMENT DES THREADS TRAITEMENT ADMIN**/
    for(int i = 0; i < MAXADMIN; i++)
    {
        int ret = pthread_create(&threadsAdminLances[i], NULL, traitementAdmin, (void*) i);
        pthread_detach(threadsAdminLances[i]);
    }
    //initialisation du tableau de socket à NULL
    for(int i = 0; i < MAXADMIN; i++)//On met la liste des sockets ouvertes à NULL
        socketAdminOuverte[i] = NULL; 


    while(1)
    {
        try
        {
            sock->ecouter(); //On se met à l'écoute d'une requête cliente
        }
        catch(ErrnoException er)
        {
            cout << er.getErrorCode() << "THREAD ADMIN------>" << er.getMessage() << endl;
            exit(-1);
        }

        int service = sock->accepter(); //On attend qu'un admin se connecte

        //On va toucher à des variables manipulées par plusieurs threads. On sécurise avec mutex
        pthread_mutex_lock(&mutexJobAdminDispo);

        int j;
        for(j = 0; j < MAXADMIN && socketAdminOuverte[j] != NULL; j++); //On parcourt le tableau de socket pour trouver un emplacement libre

        threadsAdminLibres--;

        socketAdminOuverte[j] = new Socket(service); //creation d'une nouvelle socket service
        indiceThreadAdmin=j; //on met la variable indice courant à la position de la socket créée pour que le thread puisse savoir laquelle prendre
        pthread_mutex_unlock(&mutexJobAdminDispo);
        pthread_cond_signal(&condJobAdminDispo); //On réveille un traitement admin
    }
}


