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

Socket::Socket(int h)
{
	socketHandle = h;
}


/* Destructeur */
Socket::~Socket()
{
	close(socketHandle);
}

int Socket::getSocketHandle() const 
{
	return socketHandle;
}

void Socket::sendChar(string message)
{
	int nbrByteSend, messageLength;
	string stringSend;

	messageLength = message.size();

	cout << messageLength << endl;

	stringSend = '#';

	while(messageLength > 0)
	{
		int digit = messageLength%10;

		char digitToChar = digit + 48;//on convertis le nombre isolé en son symbol dans la table ascii

		messageLength =  messageLength / 10;

		stringSend = digitToChar + stringSend;
	}

	stringSend = stringSend + message;

	cout << stringSend << "-----" << stringSend.size() << endl;

	nbrByteSend = send(socketHandle, (void*)stringSend.c_str(), stringSend.size(), 0);

	if(nbrByteSend == -1)
    {
        cout << "Le message n'a pas été correctement envoye" << endl;
    }
}


void Socket::sendStruct(void* stru)
{
	int nbrByteSend = send(socketHandle, stru, sizeof(stru), 0);

	if(nbrByteSend == -1)
    {
        cout << "Le message n'a pas été correctement envoye" << endl;
    }
}

void Socket::receiveStruct(void* r, int size)
{
	int totBytesReceives = 0;
	int bytes;
	do
	{

		if((bytes = recv(socketHandle, r, size, 0)) == -1)
		{
			cout << "Erreur de reception " << endl;
			exit(-1);
		}

		totBytesReceives += bytes;

		if(totBytesReceives > size)
		{
			cout << "Erreur de reception " << endl;
			exit(-1);
		}

		cout << totBytesReceives << "------" << size << endl;

	}while(totBytesReceives < size);
}

