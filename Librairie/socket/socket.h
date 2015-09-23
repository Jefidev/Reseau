#ifndef SOCKET_H_INCLUDED
#define SOCKET_H_INCLUDED

class Socket
{
	protected :
		struct sockaddr_in socketAdress;
    	int socketHandle;
		
	public :

		Socket();
		Socket(string host, int port, bool isIP);
		~Socket();


		/*getter*/
		int getSocketHandle() const;
};

#endif