#include <iostream>

#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/fichierProp/fichierProp.h"

int main()
{

    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    SocketServeur* sock = NULL;

    cout << host << "---- test " << endl;

    if(isip == "1")
        sock = new SocketServeur(host , atoi(port.c_str()), true);
    else
        sock = new SocketServeur(host , atoi(port.c_str()), false);


    int service;
    
    sock->ecouter();

    service = sock->accepter();
    
    char message[50];

    if(recv(service, message, 50, 0) == -1)
    {
        cout << "erreur receive : " << errno << endl;
        return 0;
    }

    cout << "Test : " << message;

}



