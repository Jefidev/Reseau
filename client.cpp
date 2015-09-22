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

    unsigned int t = sizeof(struct sockaddr);

    if((connect(sock.getSocketHandle(), (struct sockaddr*)sock.getSockAdd(), t))==-1)
    {
        cout << "erreur connect" << errno << endl;
        return 0;
    }


    if(send(sock.getSocketHandle(), "Bonjour", 50, 0) == -1)
    {
        cout << "erreur send : " << errno << endl;
    }

}



