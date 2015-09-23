#ifndef SOCKETCLIENT_H_INCLUDED
#define SOCKETCLIENT_H_INCLUDED

class SocketServeur: public Socket
{
	public :

		SocketServeur(string host, int port, bool isIP);
		~SocketServeur();
		
		/* METHODES */

		void ecouter();
		int accepter();
};

#endif
