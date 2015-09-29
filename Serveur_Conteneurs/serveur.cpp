#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>

using namespace std;

#include "../Librairie/socket/socket.h"
#include "../Librairie/socket/socketServeur.h"
#include "../Librairie/fichierProp/fichierProp.h"
#include "../LibrairieConteneur/protocole.ini"
#include "../Librairie/exceptions/errnoException.h"
#include "../LibrairieConteneur/sendFunction.h"

int main()
{

    FichierProp fp = FichierProp("properties.txt");

    string host = fp.getValue("HOST");
    string port = fp.getValue("PORT");
    string isip = fp.getValue("ISIP");

    SocketServeur* sock = NULL;

    try
    {

        if(isip == "1")
            sock = new SocketServeur(host , atoi(port.c_str()), true);
        else
            sock = new SocketServeur(host , atoi(port.c_str()), false);
    }
    catch(ErrnoException er)
    {
        cout << er.getErrorCode() << "------" << er.getMessage() << endl;
        exit(-1);
    }

    
    sock->ecouter();

    Socket service = sock->accepter();

    string sc;
    int type;

    sc = typeRequestParse(service.receiveChar(), &type);

    if(type == 1)
    {
        StructConnexion connex;
        connex = parseConnexion(sc);

        cout << connex.nom << "-----" << connex.motDePasse << endl;
    }
}



