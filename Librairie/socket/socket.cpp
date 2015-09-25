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



/**************************************************************************************
*Constructeur effectuant les actions communes d'un client et serveur : creation socket, 
remplissage de structure sockaddr_in
**************************************************************************************/
Socket::Socket(string host, int port, bool isIP)
{
	socketHandle = socket(AF_INET, SOCK_STREAM, 0);

	if(socketHandle == -1)
	{
		cout << "Erreur création socket  : " << errno <<endl;
		exit(-1);
	}

	if(isIP)//Si l'host est une IP
	{
		memset(&socketAdress, '0', sizeof(sockaddr_in));

		socketAdress.sin_family = AF_INET;
		socketAdress.sin_port = htons(port);
		socketAdress.sin_addr.s_addr = inet_addr(host.c_str());

		return;
	}
	else //Si l'host est un serveur
	{
		struct hostent* infohost;

		if((infohost = gethostbyname(host.c_str()))==0)
	    {
	        cout << "Erreur getHost : " << errno <<endl;
	        exit(-1);
	    }

	    socketAdress.sin_family = AF_INET;
		socketAdress.sin_port = htons(port);
		memcpy(&socketAdress.sin_addr, infohost->h_addr, infohost->h_length);
	}

}

/* Destructeur */
Socket::~Socket()
{
	cout << "je me detruit" << endl;
	close(socketHandle);
}

int Socket::getSocketHandle() const 
{
	return socketHandle;
}

