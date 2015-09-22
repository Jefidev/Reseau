#ifndef CLIENT_H_INCLUDED
#define CLIENT_H_INCLUDED

#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>

class Socket
{
	private :
		struct sockaddr_in socketAdress;
    	int socketHandle;
		
	public :

		Socket(string host, int port, bool isIP);
		~Socket();

		/* SETTERS */
		
		int getSocketHandle();
		struct sockaddr_in * getSockAdd();

		/* GETTERS */


		/* METHODES */

};

#endif