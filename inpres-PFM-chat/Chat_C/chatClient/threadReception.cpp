#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <unistd.h>
#include <sstream>

#include "threadReception.h"

using namespace std;

int hashQuestion(string c);

extern int port_udp;
extern string ip_group;

extern int tailleSocksddr_in;

extern string tabQuestion[500];
extern int nbrQuestion;

void* reception(void* p)
{

	struct sockaddr_in localSock;
	struct ip_mreq group;
	int sd;

	sd = socket(AF_INET, SOCK_DGRAM, 0);

	if(sd == -1)
	{
		cout << "Erreur socket thread reception " << endl;
		exit(0);
	}

	int reuse = 1;
	setsockopt(sd, SOL_SOCKET, SO_REUSEADDR, (char *)&reuse, sizeof(reuse));

	memset(&localSock, 0, tailleSocksddr_in);
    localSock.sin_family = AF_INET;
    localSock.sin_port = htons(port_udp);
    localSock.sin_addr.s_addr = htonl(INADDR_ANY);

    if(bind(sd, (struct sockaddr*)&localSock, tailleSocksddr_in) == -1)
    {
        cout << "erreur bind " << errno << endl;
        exit(0);
    }

    struct ip_mreq imr;
    imr.imr_multiaddr.s_addr = inet_addr(ip_group.c_str());
    imr.imr_interface.s_addr = inet_addr("255.255.255.255");

    setsockopt(sd , IPPROTO_IP, IP_ADD_MEMBERSHIP, &imr, sizeof(imr));

	char messageUDP[1000];
	while(-1 != recvfrom(sd, messageUDP, 1000, 0, (struct sockaddr*)&localSock, &tailleSocksddr_in))
	{
		string tag, user, message;

		istringstream iss(messageUDP);//outils de decoupage
		string token;

		getline(iss, token, '#');
		user = token;

		getline(iss, token, '#');
		tag = token;

		getline(iss, token, '#');
		message = token;

		if(tag.at(0) == 'Q')// c'est une question
		{
			getline(iss, token, '#');
			int hash = atoi(token.c_str());

			if(hash != hashQuestion(message))
				continue;
			tabQuestion[nbrQuestion] = tag;
			nbrQuestion++;
		}

        cout << endl <<"(" <<tag <<") ["<<user << "] : "<<message << endl;
    }
}


int hashQuestion(string c)
{
    int hash = 0;

    for(int i = 0; i < c.size(); i++)
        hash += c.at(i);

    return hash%67;
}

