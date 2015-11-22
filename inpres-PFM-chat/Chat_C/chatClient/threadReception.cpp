#include <iostream>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <cstdlib>
#include <cstring>
#include <unistd.h>

#include "threadReception.h"

using namespace std;

extern int socketHandleUDP;
extern string curUser;
extern struct sockaddr_in infoServeurUDP;

extern int tailleSocksddr_in;

extern string ip_group;
extern int port_udp;

void* reception(void* p)
{

	sockaddr_in si_other;

	char messageUDP[1000];
	while(-1 != recvfrom(socketHandleUDP, messageUDP, 1000, 0, (struct sockaddr*)&si_other, &tailleSocksddr_in))
        cout << messageUDP << endl;
}

