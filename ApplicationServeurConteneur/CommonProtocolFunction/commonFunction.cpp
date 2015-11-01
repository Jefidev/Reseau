#include <iostream>
#include <string>
#include <sstream>
#include <stdlib.h>
#include <stdio.h>
#include <sstream>

using namespace std;

#include "commonFunction.h"
#include "../Librairie/utility.h"

string typeRequestParse(string s, int* type)
{
	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	*type = atoi((char*)token.c_str());

	return s.erase(0, token.size() + 1);
}

string composeAckErr(int l, string m)
{
	string retour;
	retour = Utility::intToString(l);
	retour = retour + SEPARATION + m;
	return retour;
}

