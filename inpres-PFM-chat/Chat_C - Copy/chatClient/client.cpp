#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <pthread.h>
#include <time.h>
#include <sstream>

#define FICHIER_PROP "properties.txt"
#define SEPARATION '#'

using namespace std;

#include "../Librairie/utility.h"
#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketClient.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../Librairie/exceptions/errnoException.h"
#include "threadReception.h"

int hashLogin(string c);
string typeRequestParse(string s, string* type);
string parseIpPort(string s, int* port);

int socketHandleUDP;
string curUser;
struct sockaddr_in infoServeurUDP;

int tailleSocksddr_in;

string ip_group;
int port_udp;

int main()
{
    tailleSocksddr_in = sizeof(struct sockaddr_in);

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
        cout << "Serveur hors ligne" << er.getErrorCode() <<endl;
        exit(-1);
    }

    //LOGIN
    string mdp;

    cout << "LOGIN :" << endl;
    cout << "-------" << endl << endl;
    cout << "user name : ";
    cin >> curUser;
    cout <<endl << "password : ";
    cin >> mdp;

    //SEL
    srand(time(NULL));

    string sel1 = Utility::intToString(rand());
    string sel2 = Utility::intToString(time(0));

    int digest = hashLogin(sel2+mdp+sel1);

    sock->sendChar("LOGIN_GROUP#"+curUser+"#"+Utility::intToString(digest)+"#"+sel1+"#"+sel2);

    string reponse;
    string typeReponse;

    reponse = typeRequestParse(sock->receiveChar(), &typeReponse);

    if(typeReponse.compare("ACK") != 0)
    {
        cout << "Erreur d'authentification " << endl;
        exit(0);
    }

    ip_group = parseIpPort(reponse, &port_udp);

    cout << endl << port_udp << "-----" << ip_group<<endl <<endl;

    struct in_addr localInterface;

    socketHandleUDP = socket(AF_INET, SOCK_DGRAM, 0);
    if(socketHandleUDP == -1)
    {
        cout << "Erreur de connexion au serveur de chat" << endl;
        exit(0);
    }

    memset((char *) &infoServeurUDP, 0, sizeof(infoServeurUDP));
    infoServeurUDP.sin_family = AF_INET;
    infoServeurUDP.sin_addr.s_addr = inet_addr(ip_group.c_str());
    infoServeurUDP.sin_port = htons(port_udp);

    localInterface.s_addr = inet_addr("255.255.255.255");
    if(setsockopt(socketHandleUDP, IPPROTO_IP, IP_MULTICAST_IF, (char *)&localInterface, sizeof(localInterface)) < 0)
    {
        exit(1);
    }


    //demarrage du thread de reception
    pthread_t t_reception;
    pthread_create(&t_reception, NULL, reception, NULL);

    char messageUDP[1000];
    bool terminer = false;
    while(!terminer)
    {
        char tmp[1000];
        memset(messageUDP, '\0', sizeof(messageUDP));
        string message;
        cout << endl << "Message : ";
        cin >> tmp;

        strcpy(messageUDP, (char*)curUser.c_str());
        strcat(messageUDP, "#Infos#");
        strcat(messageUDP, tmp);

        sendto(socketHandleUDP, messageUDP, 1000, 0, (struct sockaddr*)&infoServeurUDP, tailleSocksddr_in);
    }
    
} 

int hashLogin(string c)
{
    int hash = 0;

    for(int i = 0; i < c.size(); i++)
        hash += c.at(i);

    return hash%67;
}

string typeRequestParse(string s, string* type)
{
    istringstream iss(s);
    string token;

    getline(iss, token, SEPARATION);
    *type = token;

    return s.erase(0, token.size() + 1);
}

string parseIpPort(string s, int* port)
{
    istringstream iss(s);
    string token;
    string ip;

    getline(iss, token, SEPARATION);
    ip = token;

    getline(iss, token, SEPARATION);
    *port = atoi((char*)token.c_str());

    return ip;
}

