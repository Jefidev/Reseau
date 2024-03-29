#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cstdlib>
#include <unistd.h>
#include <cstring>
#include <string>

using namespace std;
#include "socket.h"
#include "socketServeur.h"
#include "../exceptions/errnoException.h"


/* Constructeur d'initialisation (Appel le constructeur de la classe socket et effectue le bind)*/
SocketServeur::SocketServeur(string host, int port, bool isIP):Socket(host, port, isIP)
{
	if(bind(socketHandle, (struct sockaddr*)&socketAdress, sizeof(struct sockaddr))==-1)
        throw ErrnoException(errno, "Erreur bind");  
}

/* Destructeur */
SocketServeur::~SocketServeur(){}


//Ecoute sur le port de la socket créée par le constructeur
void SocketServeur::ecouter()
{
	if(listen(socketHandle, SOMAXCONN) == -1)
        throw ErrnoException(errno, "Erreur listen");  
}


//Accepte une connection pendante et renvois le handle de socket dupliqué
int SocketServeur::accepter()
{
	int service;

	int t = sizeof(struct sockaddr);

    if((service = accept(socketHandle, (struct sockaddr*)&socketAdress, &t))==-1)
        throw ErrnoException(errno, "Erreur accept");
    
    else
        return service;
}

