#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <unistd.h>

#define FICHIER_PROP "properties.txt"

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketClient.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../LibrairieConteneur/protocole.ini"
#include "../Librairie/exceptions/errnoException.h"
#include "../CommonProtocolFunction/commonFunction.h"
#include "../LibrairieConteneur/sendFunction.h"

void login(SocketClient* sock);
void logout(SocketClient* sock);
void inputTruck(SocketClient* sock);
void inputDone(SocketClient* sock);
void outputReady(SocketClient* sock);
void outputOne(SocketClient* sock, int capaMax, string idTransport);
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
                outputReady(sock);
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
    
    inputDone(sock);

}


void inputDone(SocketClient* sock)
{
    int reponseType;
    string lecRecus;

    lecRecus = typeRequestParse(sock->receiveChar(), &reponseType);

    while(reponseType == CONT_POS)
    {

        StructContainerPosition scp = parseContPos(lecRecus);
        StructInputDone sid;
        sid.coord = scp.coord;
        sid.id = scp.id;

        cout << endl << endl << "Container ID : " << sid.id << endl;
        cout << "Emplacement : " << sid.coord << endl;
        cout << "poids (en tonne): ";
        cin >> sid.poids;
        cout << endl << "Destination : ";
        cin >> sid.destination;

        do
        {
            cout << endl << "Moyen de transport (1 = train 0 = bateau) : ";
            cin >> sid.transport;
        }while(sid.transport != 0 && sid.transport != 1);


        sock->sendChar(composeInputDone(INPUT_DONE, sid));

        lecRecus = typeRequestParse(sock->receiveChar(), &reponseType);

        if(reponseType == ACK)
            cout << endl << lecRecus;
        else if(reponseType == ERREUR)
            cout << endl << lecRecus;
        else //On a reçus des trucs pas cool on coupe
            logout(sock);

        sock->sendChar(composeAckErr(NEXT, ""));

        lecRecus = typeRequestParse(sock->receiveChar(), &reponseType);
        
    }

    cout << endl << endl << lecRecus;
}


void outputReady(SocketClient* sock)
{
    StructOuputReady sor;

    cout << endl << endl << endl << endl << "Sortie de container" << endl;
    cout << "-------------------" << endl << endl;

    do
    {
        cout << endl << "Moyen de transport (1 = train 0 = bateau) : ";
        cin >> sor.type;
    }while(sor.type != 0 && sor.type != 1);

    cout << endl << endl << "ID du moyen de transport : ";
    cin >> sor.idTrainBateau;

    cout << endl << endl << "Destination : ";
    cin >> sor.destination;

    do
    {
        cout << endl << "Capacite maximale (en container) : ";
        cin >> sor.capaciteMax;
    }while(sor.capaciteMax < 1);

    cout << endl << endl << endl;

    sock->sendChar(composeOutputReady(OUTPUT_READY, sor));

    outputOne(sock, sor.capaciteMax, sor.idTrainBateau);
}

void outputOne(SocketClient* sock, int capaMax, string idTransport)
{
    int reponseType;
    string reponse = typeRequestParse(sock->receiveChar(), &reponseType);

    if(reponseType == ERREUR)
    {
        cout << reponse << endl;
        return;
    }

    if(reponseType != ACK)
        logout(sock);

    char *lecContainer, sep = SEPARATION, *saveptr, sepaContainer = CONTAINER_SEPARATION;
    char* copieReponse =  new char [reponse.length()+1];
    strcpy (copieReponse, reponse.c_str());

    lecContainer = strtok_r(copieReponse, &sep, &saveptr);
    int nbrCharge = 0;
    bool finChargement = false;

    while(lecContainer != NULL)
    {

        cout << endl <<"ID container : " << strtok(lecContainer, &sepaContainer) << endl << endl;

        char* emplacement = strtok(NULL, &sepaContainer);

        cout << "Emplacement : " << emplacement << endl << endl;

        int tmp = 0;

        do
        {
            cout <<endl << "Charger ce container ? (1 oui / 0 non)";
            cin >> tmp;
        }while(tmp !=0 && tmp != 1);

        if(tmp == 1)
        {
            StructOutputOne soo;
            soo.emplacement = emplacement;

            sock->sendChar(composeOutputOne(OUTPUT_ONE, soo));
            cout << "after send" << endl;
            string lecRecus = typeRequestParse(sock->receiveChar(), &reponseType);
            cout << "after rcv" << endl;

            if(reponseType ==  ACK)
            {
                nbrCharge++;
                cout << endl <<"Le container a ete sortis du parc" << endl;
            }
            else
                cout << endl << lecRecus << endl;
        }

        if(nbrCharge == capaMax)
            break;

        do
        {
            cout << endl << endl << "Charger un autre container ? (1 oui / 0 non)";
            cin >> tmp;
        }while(tmp !=0 && tmp != 1);

        if(tmp == 0)
        {
            finChargement = true;
            break;
        }

        lecContainer = strtok_r(NULL, &sep, &saveptr);
    }

    if(nbrCharge == capaMax)
        cout << endl << endl <<"Plus de place pour d'autre container sur le transport " << endl << endl;

    if(finChargement == true)
        cout << endl << endl <<"Chargement termine" << endl << endl;

    if(lecContainer == NULL)
        cout << endl << endl << "Plus de container en attente pour la destination " << endl << endl;


    StructOutputDone sod;

    sod.nbrContainers = nbrCharge;
    sod.idTrainBateau = idTransport;

    sock->sendChar(composeOutputDone(OUTPUT_DONE, sod));

    string lecRecus = typeRequestParse(sock->receiveChar(), &reponseType);

    if(reponseType ==  ACK)
        cout << "Les container ont ete retire du parc" << endl << endl;

    else
        cout << "Erreur lors de la sortie des containers" << endl << endl;

    delete copieReponse;
}


