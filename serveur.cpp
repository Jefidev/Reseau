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

int main()
{
    Socket sock = Socket("localhost", PORT, false);
    int ecoute;

    if(bind(sock.getSocketHandle(), (struct sockaddr*)sock.getSockAdd(), sizeof(struct sockaddr))==-1)
    {
        cout << "Erreur bind" << endl;
        return 0;
    }

    if(listen(sock.getSocketHandle(), MAXCO) == -1)
    {
        cout << "Erreur de listen" << endl;
        return 0;
    }

    int t = sizeof(struct sockaddr);

    if((ecoute = accept(sock.getSocketHandle(), (struct sockaddr*)sock.getSockAdd(), &t))==-1)
    {
        cout << "erreur accept" << errno << endl;
        return 0;
    }

    char message[50];

    if(recv(ecoute, message, 50, 0) == -1)
    {
        cout << "erreur receive : " << errno << endl;
        return 0;
    }

    cout << "Test : " << message;

}



