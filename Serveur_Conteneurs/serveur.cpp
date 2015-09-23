#include <iostream>

#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>

#define PORT 50001

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/fichierProp/fichierProp.h"

int main()
{

    FichierProp fp = FichierProp("properties.txt");

    SocketServeur sock = SocketServeur("localhost", PORT, false);
    int ecoute;
    
    sock.ecouter();

    ecoute = sock.accepter();
    
    char message[50];

    if(recv(ecoute, message, 50, 0) == -1)
    {
        cout << "erreur receive : " << errno << endl;
        return 0;
    }

    cout << "Test : " << message;

}



