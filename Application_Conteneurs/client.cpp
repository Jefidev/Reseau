#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>

#define FICHIER_PROP "properties.txt"

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketClient.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../protocol.ini"
/**********************EXEMPLE DE CLIENT****************************/

int main()
{
    FichierProp fp = FichierProp("properties.txt");//On créer un objet permettant de lire le fichier properties

    string host = fp.getValue("HOST");//On peut recuperer une valeur grâce à getValue
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    SocketClient* sock = NULL;

    if(isip == "1")//On test pour savoir si on doit créer le client avec une IP ou un HostName
        sock = new SocketClient(host , atoi(port.c_str()), true);
    else
        sock = new SocketClient(host , atoi(port.c_str()), false);


    sock->connecter();//Connexion au serveur

    sock->sendChar("testificate");//Envois d'une string au serveur 

    /*Pour info pour composer une string j'ai mis une fonction intToString(int chiffre) qui permet de renvoyé sous forme de string un nombre entier

    Le fichier protocol.ini reprend les différents types de messages et structures*/

}   



