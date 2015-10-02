#include <iostream>
#include <cstring>
#include <string>

using namespace std;
#include "errnoException.h"

ErrnoException::ErrnoException(int i, string m)
{
	errorNumber = i;
	message = m;
}


int ErrnoException::getErrorCode()
{
	return errorNumber;
}


string ErrnoException::getMessage()
{
	return message;
}
