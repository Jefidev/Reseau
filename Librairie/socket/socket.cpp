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
#include "../utility.h"



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


/*********************************************************
*Prend une chaine de caractere en parametre et l'envois au serveur
*********************************************************/

void Socket::sendChar(string message)
{
	int nbrByteSend, messageLength;
	string stringSend;

	messageLength = message.size();
	stringSend = Utility::intToString(messageLength) + '#' + message;


	nbrByteSend = send(socketHandle, (void*)stringSend.c_str(), stringSend.size(), 0);

	if(nbrByteSend == -1)
    {
        cout << "Le message n'a pas été correctement envoye" << endl;
    }
}

/******************************************************************************************
*Prend une struct castée en void* en parametre et le sizeof() de cette struct.
*********************************************************************************************/

void Socket::sendStruct(void* stru, int size)
{
	int nbrByteSend = send(socketHandle, stru, size, 0);

	if(nbrByteSend == -1)
    {
        cout << "Le message n'a pas été correctement envoye" << endl;
    }
}

/******************************************************************************************
*Place les bytes reçus dans void* r (struct attendue castée en void*) et a besoin de sizeof() de cette struct
******************************************************************************************/

void Socket::receiveStruct(void* r, int size)
{
	int totBytesReceives = 0;
	int bytes, sizeMax;

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

	if(totBytesReceives > size)
	{
		cout << "Erreur message trop long" << endl;
		exit(-1);
	}
}


/******************************************************************************************
*Renvois la chaine de caractere lue sur le réseau.
*******************************************************************************************/
string Socket::receiveChar()
{
	int totBytesReceives = 0;
	int bytesReceived, stringLength = 0;

	char buff[500], cpBuff[500], *messageSize;
	string retString;

	do
	{
		if((bytesReceived = recv(socketHandle, buff, 500, 0)) == -1)
		{
			cout << "Erreur de reception " << endl;
			exit(-1);
		}
		else
		{
			if(totBytesReceives == 0)//Si on est au début du message (0 caracteres traités)
			{
				strcpy(cpBuff, buff);
				messageSize = strtok(cpBuff, "#");

				//le nombre de byte à lire  = taille du message + taille du chiffres au debut + le caractere # "effacé" au strtok ci dessus
				stringLength =  atoi(messageSize) + strlen(messageSize) + 1;
			}
			
			totBytesReceives += bytesReceived; //On met à jour la longueur
			retString += buff;

			if(stringLength == 0)
			{
				cout << "Chaine reçue non valide " << endl;
				exit(-1);
			}
		}


	}while(totBytesReceives < stringLength);

	cout << retString << endl;

	if(totBytesReceives > stringLength)
	{
		cout << "Erreur message trop long" << endl;
		exit(-1);
	}

	return retString.erase(0, strlen(messageSize) + 1); //On retourne la chaine composée sans les caractères devant 
}

