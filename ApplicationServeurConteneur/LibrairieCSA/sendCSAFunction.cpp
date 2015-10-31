#include <iostream>
#include <string>
#include <sstream>
#include <stdlib.h>
#include <stdio.h>
#include <sstream>

using namespace std;

#include "protocole.ini"
#include "sendFunction.h"
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

string composeConnexion(int l, StructConnexion sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.nom + SEPARATION + sc.motDePasse;
	return retour;
}


StructConnexion parseConnexion(string s)
{
	StructConnexion sc;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	sc.nom = token;

	getline(iss, token, SEPARATION);
	sc.motDePasse = token;

	return sc;
}

string composeInputTruck(int l, StructInputTruck sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.immatriculation + SEPARATION + sc.idContainers;

	return retour;
}


StructInputTruck parseInputTruck(string s)
{
	StructInputTruck si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.immatriculation = token;

	getline(iss, token, SEPARATION);
	si.idContainers = token;

	return si;
}

string composeInputDone(int l, StructInputDone sc)
{
	string retour;

	ostringstream convert;

	convert << sc.poids << SEPARATION << sc.transport;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.destination + SEPARATION + convert.str() + SEPARATION + sc.coord + SEPARATION + sc.id;

	return retour;
}

StructInputDone parseInputDone(string s)
{
	StructInputDone si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.destination = token;

	getline(iss, token, SEPARATION);
	si.poids = atoi(token.c_str());

	getline(iss, token, SEPARATION);
	si.transport = atoi(token.c_str());

	getline(iss, token, SEPARATION);
	si.coord = token;

	getline(iss, token, SEPARATION);
	si.id = token;

	return si;
}

string composeContPos(int l, StructContainerPosition sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.coord + SEPARATION + sc.id;

	return retour;
}

StructContainerPosition parseContPos(string s)
{
	StructContainerPosition si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.coord = token;

	getline(iss, token, SEPARATION);
	si.id = token;

	return si;
}




