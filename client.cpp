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
#include "Librairie/socketClient.h"

int main()
{
    SocketClient sock = SocketClient("localhost", PORT, false);

    sock.connecter();

    if(send(sock.getSocketHandle(), "Bonjour", 50, 0) == -1)
    {
        cout << "erreur send : " << errno << endl;
    }

}



