#ifndef SOCKET_H_INCLUDED
#define SOCKET_H_INCLUDED

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