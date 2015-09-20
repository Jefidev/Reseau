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

int main()
{
    struct hostent* infohost;
    struct in_addr transfAdresse;
    struct sockaddr_in ip;

    int soHandle = socket(AF_INET, SOCK_STREAM, 0);

    if(soHandle == -1)
    {
        cout << "Erreur creation socket : " << errno << endl;
        return(1);
    }

    if((infohost = gethostbyname("localhost"))==0)
    {
        cout << "Erreur getHost" <<endl;
        return 0;
    }

    memcpy(&transfAdresse, infohost->h_addr, infohost->h_length);

    memset(&ip, 0, sizeof(struct sockaddr_in));
    ip.sin_family = AF_INET;
    ip.sin_port = htons(PORT);
    memcpy(&ip.sin_addr, infohost->h_addr, infohost->h_length);

    unsigned int t = sizeof(struct sockaddr);
    int ser;

    if((connect(soHandle, (struct sockaddr*)&ip, t))==-1)
    {
        cout << "erreur connect" << errno << endl;
        return 0;
    }


    if(send(soHandle, "Bonjour", 50, 0) == -1)
    {
        cout << "erreur send : " << errno << endl;
    }

}



