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
#include "../LibrairieConteneur/protocole.ini"
#include "../Librairie/exceptions/errnoException.h"
#include "../LibrairieConteneur/sendFunction.h"

void login(SocketClient* sock);

int main()
{
    FichierProp fp = FichierProp("properties.txt");//On créer un objet permettant de lire le fichier properties

    string host = fp.getValue("HOST");//On peut recuperer une valeur grâce à getValue
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    SocketClient* sock = NULL;

    try
    {
        if(isip == "1")//On test pour savoir si on doit créer le client avec une IP ou un HostName
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
        cout << er.getErrorCode() << "------" << er.getMessage() << endl;
        exit(-1);
    }

    login(sock);

} 

void login(SocketClient* sock)
{
    StructConnexion sc;
    int nbrTentative = 0;
    int reponseType;
    do
    {

        cout << "Connexion au serveur" << endl;
        cout << "--------------------" << endl << endl;

        cout << "Login : ";
        cin >> sc.nom;
        cout << endl;

        cout << "mot de passe : ";
        cin >> sc.motDePasse;
        cout << endl;

        sock->sendChar(composeConnexion(LOGIN, sc));
        
       

        string str = typeRequestParse(sock->receiveChar(), &reponseType);

        if(reponseType == ERREUR)
        {

            cout << str << endl;
            if(!str.compare("INVALIDE"))
            {
                cout << "Requete recue invalide" << endl;
                sock->sendChar(composeConnexion(LOGOUT, sc));
                string str = typeRequestParse(sock->receiveChar(), &reponseType);
                sock->finConnexion();
                exit(-1); 
            }
            else if(!str.compare("LOGERR"))
            {
                cout << "Infos de login invalides" << endl << endl << endl;
                nbrTentative++;
            }
            
        }
        else if(reponseType == ACK)
            return;

    }while(nbrTentative < 3);

    cout << "Login ou mot de passe invalide connexion refusee" << endl << endl;

    sock->sendChar(composeConnexion(LOGOUT, sc));

    string str = typeRequestParse(sock->receiveChar(), &reponseType);

    sock->finConnexion();
    exit(0);
}



