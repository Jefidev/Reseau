#ifndef SOCKET_H_INCLUDED
#define SOCKET_H_INCLUDED

class Socket
{
	protected :
		struct sockaddr_in socketAdress;
    	int socketHandle;
		
	public :

		Socket(string host, int port, bool isIP);
		Socket(int h);
		~Socket();


		/*getter*/
		int getSocketHandle() const;

		/*METHODE*/
		void sendChar(string message);
		void sendStruct(void* stru);


		void receiveStruct(void* r, int size);

};

#endif