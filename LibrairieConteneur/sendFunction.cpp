#include <iostream>
#include <string>
#include <sstream>

using namespace std;

#include "protocole.ini"
#include "sendFunction.h"

string composeConnexion(char l, StructConnexion sc)
{
	string retour;
	retour = retour + SEPARATION;
	retour = retour + sc.nom;

	return retour;
}


StructConnexion decomposeConnexion(string s)
{
	StructConnexion sc;
	cout << s << endl;
	std::istringstream iss(s);
	std::string token;

	std::getline(iss, token, SEPARATION);

	cout << token << endl;

	return sc;
}

