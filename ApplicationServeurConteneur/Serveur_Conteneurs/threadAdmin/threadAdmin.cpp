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

using namespace std;

#include "../../Librairie/socket/socket.h"
#include "../../Librairie/socket/socketServeur.h"
#include "../../Librairie/fichierProp/fichierProp.h"
#include "../../Librairie/exceptions/errnoException.h"
#include "threadAdmin.h"


void* threadAdmin(void* p)
{
	cout << "testificate" <<endl;

	FichierProp fp = FichierProp("properties.txt");

	SocketServeur* sock = NULL; //Socket qui sera en attente d'une connexion admin

	string host = fp.getValue("HOST");
    string port = fp.getValue("PORT_ADMIN");
    string isip = fp.getValue("ISIP");

    cout << port << endl;

    try
    {

        if(isip == "1")
            sock = new SocketServeur(host , atoi(port.c_str()), true); //Si dans le fichier prop on a une IP
        else
            sock = new SocketServeur(host , atoi(port.c_str()), false); // Si dans le fichier prop on a un hostname
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "THREAD ADMIN ------> " << er.getMessage() << endl;
        exit(-1);
    }

    cout << "mise en attente " << endl;

    sock->ecouter();

    int service = sock->accepter();

    Socket s = Socket(service);

   	loginAdmin(s.receiveChar());
}


void loginAdmin()
{
	
}