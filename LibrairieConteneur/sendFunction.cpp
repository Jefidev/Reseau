#include <iostream>
#include <string>
#include <sstream>

using namespace std;

#include "protocole.ini"
#include "sendFunction.h"

string composeConnexion(char l, StructConnexion sc)
{
	string retour;

	retour = l;
	retour = retour + SEPARATION + sc.nom + SEPARATION + sc.motDePasse;

	return retour;
}


StructConnexion decomposeConnexion(string s)
{
	StructConnexion sc;
	cout << s << endl;
	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);//On passe le type de message
	getline(iss, token, SEPARATION);
	sc.nom = token;

	getline(iss, token, SEPARATION);
	sc.motDePasse = token;

	return sc;
}

