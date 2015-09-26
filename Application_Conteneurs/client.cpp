#include <iostream>

#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>

#define PORT 50001
#define FICHIER_PROP "properties.txt"

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketClient.h"
#include "../Librairie/fichierProp/fichierProp.h"

int main()
{
    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    SocketClient* sock = NULL;

    if(isip == "1")
        sock = new SocketClient(host , atoi(port.c_str()), true);
    else
        sock = new SocketClient(host , atoi(port.c_str()), false);


    sock->connecter();

    sock->sendStruct((void*)"Bonjour");

}



