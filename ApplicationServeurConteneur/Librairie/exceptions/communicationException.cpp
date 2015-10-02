#include <iostream>
#include <cstring>
#include <string>

using namespace std;
#include "communicationException.h"

CommunicationException::CommunicationException(string m)
{
	message = m;
}


string CommunicationException::getMessage()
{
	return message;
}
