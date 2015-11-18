#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <sstream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <signal.h>

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/exceptions/errnoException.h"

extern string portUrgence;
extern string ip;

extern bool inPause;
extern pthread_cond_t condServeurPause;

void* threadUrgence(void* p)
{
	SocketServeur* sock = NULL; //Socket qui sera en attente d'une connexion d'urgence
	cout << "test: "<<ip << endl;
    try
    {
    	if(ip.compare("localhost")) // si c'est pas localhost
        	sock = new SocketServeur(ip , atoi(portUrgence.c_str()), true); //Si dans le fichier prop on a une IP
        else
        	sock = new SocketServeur(ip , atoi(portUrgence.c_str()), false);
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "THREAD URGENCE------" << er.getMessage() << endl;
        exit(-1);
    }

    while(1)
    {
        int service;
        try
        {
            sock->ecouter(); 
            service = sock->accepter();
        }
        catch(ErrnoException er)
        {
            cout << er.getErrorCode() << "------" << er.getMessage() << endl;
            exit(-1);
        }

        Socket sockService(service);

        string str = sockService.receiveChar();

        if(str.compare("pause") == 0)
        {
            inPause = true;
        }
        else if(str.compare("continuer") == 0)
        {
            inPause = false;
            pthread_cond_signal(&condServeurPause);
        }
        else
        {
            cout << str << endl;
            while(1)
                cout << sockService.receiveChar() << endl;
        }

        sockService.finConnexion();
    }
}




