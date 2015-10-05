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
void logout(SocketClient* sock);
void inputTruck(SocketClient* sock);
void inputDone(SocketClient* sock, string listContainer, string listePosition);
int menu();

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
        cout << "Serveur hors ligne" << endl;
        exit(-1);
    }

    login(sock);

    while(1)
    {

        int action = menu();
        
        switch(action)//Choix de l'action à faire selon le menu
        {
            case 1://INPUT READY
                inputTruck(sock);
                break;
            case 2: //OUTPUT READY
                break;
            case 3: //LOGOUT
                logout(sock);
                break;
        }
    }
} 


/************************************
*On va demander au client d'entrer son LOGIN et mot de passe au serveur. Celui-ci va vérifier si le login est ok (3 essais apres deconnexion)
***************************************/
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
        cout << endl << "Tentative de connexion au serveur ..." << endl << endl;

        sock->sendChar(composeConnexion(LOGIN, sc));
        
       

        string str = typeRequestParse(sock->receiveChar(), &reponseType);

        if(reponseType == ERREUR)
        {

            cout << str << endl;
            if(!str.compare("INVALIDE"))//Si le serveur à reçus un type de requete invalide (hacker ou autre client sur la meme socket c'est la merde)
            {
                cout << "Requete recue invalide" << endl;
                sock->sendChar(composeConnexion(LOGOUT, sc));
                string str = typeRequestParse(sock->receiveChar(), &reponseType);
                sock->finConnexion();
                exit(-1); 
            }
            else if(!str.compare("LOGERR"))//Si le couple LOGIN MDP etaient invalide
            {
                cout << "Infos de login invalides" << endl << endl << endl;
                nbrTentative++;
            }
            
        }
        else if(reponseType == ACK)
            return;

    }while(nbrTentative < 3);

    cout << "Login ou mot de passe invalide connexion refusee" << endl << endl;//trop d'erreurs

    logout(sock);
}

void logout(SocketClient* sock)// on envoit la demande de LOGOUT au serveur qui nous ACK le LOGOUT puis on met fin à la connexion
{
    StructConnexion sc;
    int reponseType;
    sock->sendChar(composeConnexion(LOGOUT, sc)); //envois demande LOGOUT

    string str = typeRequestParse(sock->receiveChar(), &reponseType); //Reception ACK

    sock->finConnexion();
    exit(0);
}


int menu()//Bon la si tu comprend pas ... :p
{   
    int choix = 0;

    do
    {
        cout << endl << endl << "\tMENU" << endl;
        cout << "\t----" << endl << endl;

        cout << "\t1) Entree container" << endl;
        cout << "\t2) Sortie container" << endl << endl;
        cout << "\t3) deconnexion" << endl << endl;

        cout << "choix : ";
        cin >> choix;

        if(choix < 1  || choix > 3)
            cout << endl << endl << "Choix invalide (choisissez un nombre entre 1 et 3" << endl << endl;

    }while(choix < 1 || choix > 3);

    return choix;

}

void inputTruck(SocketClient* sock)
{
    StructInputTruck sit;
    cout << endl << endl <<"\tEntree container" << endl;
    cout << "\t----------------" << endl << endl;

    cout << "Immatriculation du camion : ";
    cin >> sit.immatriculation;

    bool continuer = true;
    string containersList, c;

    do
    {
        cout << endl << endl << endl << "ID du container : ";
        cin >> c;

        containersList = containersList + c;

        int choix = 0;

        do{
            cout << endl << endl << "Ajouter un container ? (1 oui / 0 non) : ";
            cin >> choix;

            if(choix < 0 || choix > 1)
                cout << "choix invalide" << endl << endl;

        }while (choix < 0 || choix > 1);

        if(choix == 0)
            continuer = false;
        else
            containersList = containersList + CONTAINER_SEPARATION;

    }while(continuer);

    sit.idContainers = containersList;

    sock->sendChar(composeInputTruck(INPUT_TRUCK, sit));

    int reponseType;
    string str = typeRequestParse(sock->receiveChar(), &reponseType);

    if(reponseType == ERREUR)
    {
        cout << endl << str << endl;
        return;
    }
    
    inputDone(sock, containersList, str);

}


void inputDone(SocketClient* sock, string listContainer, string listePosition)
{
    
}

