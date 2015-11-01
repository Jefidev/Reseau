#include <iostream>
#include <string>
#include <sstream>
#include <stdlib.h>
#include <stdio.h>
#include <sstream>

using namespace std;

#include "CSA.ini"
#include "sendCSAFunction.h"
#include "../Librairie/utility.h"
#include "../CommonProtocolFunction/commonFunction.h"

string composeLogin(int l, StructLogin sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.nom + SEPARATION + sc.motDePasse;
	return retour;
}


StructLogin parseLogin(string s)
{
	StructLogin sc;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	sc.nom = token;

	getline(iss, token, SEPARATION);
	sc.motDePasse = token;

	return sc;
}

string composeRequest(int l, string r)
{
	string retour;
	retour = Utility::intToString(l);
	retour = retour + SEPARATION + r;
	return retour;
}


