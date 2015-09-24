#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <iostream>
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


/* Constructeur d'initialisation */
SocketServeur::SocketServeur(string host, int port, bool isIP):Socket(host, port, isIP)
{
	if(bind(socketHandle, (struct sockaddr*)&socketAdress, sizeof(struct sockaddr))==-1)
    {
        cout << "Erreur bind : " << errno << endl;
        exit(1);
    }
}

/* Destructeur */
SocketServeur::~SocketServeur()
{

}

void SocketServeur::ecouter()
{
	if(listen(socketHandle, SOMAXCONN) == -1)
    {
        cout << "Erreur de listen" << errno <<endl;
        exit(-1);
    }
}

int SocketServeur::accepter()
{
	int ecoute;

	int t = sizeof(struct sockaddr);

    if((ecoute = accept(socketHandle, (struct sockaddr*)&socketAdress, &t))==-1)
    {
        cout << "erreur accept" << errno << endl;
        exit(-1);
    }

    return ecoute;
}

