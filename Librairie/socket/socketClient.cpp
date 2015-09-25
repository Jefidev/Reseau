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
#include "socketClient.h"


/* Constructeur d'initialisation (appel le constructeur de socket)*/
SocketClient::SocketClient(string host, int port, bool isIP):Socket(host, port, isIP)
{

}

/* Destructeur */
SocketClient::~SocketClient(){}


/************************************************************************
*Connecte le client au serveur via le socket créée dans le constructeur
*************************************************************************/
void SocketClient::connecter()
{
	unsigned int t = sizeof(struct sockaddr);

    if((connect(socketHandle, (struct sockaddr*)&socketAdress, t))==-1)
    {
        cout << "erreur connect" << errno << endl;
        exit(-1);
    }
}

