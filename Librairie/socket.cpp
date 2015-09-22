using namespace std;
#include "socket.h"



/* Constructeur d'initialisation */
Socket::Socket(string host, int port, bool isIP);
{
	socketHandle = socket(AF_INET, SOCK_STREAM, 0);

	if(socketHandle == -1)
	{
		cout << "Erreur création socket  : " << errno <<endl;
		exit(-1);
	}

	if(isIP)
	{
		memset(&socketAdress, '0', sizeof(sockaddr_in));

		socketAdress.sin_family = AF_INET;
		socketAdress.sin_port = htons(port);
		socketAdress.s_addr = inet_addr(host.c_str);

		return;
	}
	else
	{
		struct hostent* infohost;

		if((infohost = gethostbyname(host.c_str))==0)
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

int Socket::getSocketHandle()
{
	return socketHandle;
}

struct sockaddr_in * Socket::getSockAdd()
{
	return &socketAdress;
}

