#include <iostream>

#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>

#define PORT 50001
#define MAXCO 1024

using namespace std;

#include "Librairie/socket.h"
#include "Librairie/socketServeur.h"

int main()
{
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



