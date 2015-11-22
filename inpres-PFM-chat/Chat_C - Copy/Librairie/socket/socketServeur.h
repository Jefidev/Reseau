#ifndef SOCKETSERVEUR_H_INCLUDED
#define SOCKETSERVEUR_H_INCLUDED

class SocketServeur: public Socket
{
	public :

		SocketServeur();
		SocketServeur(string host, int port, bool isIP);
		~SocketServeur();
		
		/* METHODES */

		void ecouter();
		int accepter();
};

#endif
