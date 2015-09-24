#ifndef SOCKET_H_INCLUDED
#define SOCKET_H_INCLUDED

class Socket
{
	protected :
		struct sockaddr_in socketAdress;
    	int socketHandle;
		
	public :

		Socket(string host, int port, bool isIP);
		~Socket();


		/*getter*/
		int getSocketHandle() const;

		/*METHODE*/
		int receiveChar(char* r, char sep);
		//int receiveByte(void* r, int size);

};

#endif