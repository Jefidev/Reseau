#ifndef SOCKETCLIENT_H_INCLUDED
#define SOCKETCLIENT_H_INCLUDED

class SocketClient: public Socket
{
	public :

		SocketClient();
		SocketClient(string host, int port, bool isIP);
		~SocketClient();

		/* METHODES */

		void connecter();
};

#endif

