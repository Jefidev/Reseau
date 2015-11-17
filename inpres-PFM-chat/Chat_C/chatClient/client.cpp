#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <pthread.h>

#define FICHIER_PROP "properties.txt"

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketClient.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../Librairie/exceptions/errnoException.h"

int hashLogin(string c);

string portUrgence;
string ip;

int main()
{
    FichierProp fp = FichierProp("properties.txt");//On créer un objet permettant de lire le fichier properties
    string host = fp.getValue("HOST");//On peut recuperer une valeur grâce à getValue
    string port = fp.getValue("PORT");

    SocketClient* sock = NULL;
    try
    {
        if(host.compare("localhost"))//On test pour savoir si on doit créer le client avec une IP ou un HostName
            sock = new SocketClient(host , atoi(port.c_str()), true);
        else
            sock = new SocketClient(host , atoi(port.c_str()), false);
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "------" << er.getMessage() << endl;
        exit(-1);
    }

    try
    {
        sock->connecter();//Connexion au serveur
    }
    catch(ErrnoException er)
    {
        cout << "Serveur hors ligne" << endl;
        exit(-1);
    }

    //LOGIN
    string mdp;
    string login;

    sel1 = 
    sel2 = 

    cout << "LOGIN :" << endl;
    cout << "-------" << endl << endl;
    cout << "user name : ";
    cin >> login;
    cout <<endl << "password : ";
    cin >> mdp;


    cout << hashLogin("test") << endl;

} 

int hashLogin(string c)
{
    int hash = 0;

    for(int i = 0; i < c.size(); i++)
        hash += c.at(i);

    return hash%67;
}
