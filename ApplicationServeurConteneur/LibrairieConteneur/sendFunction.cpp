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

	cout << s << endl;

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
	retour = retour + SEPARATION + sc.destination + SEPARATION + convert.str();

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

	return si;
}

string composeOutputReady(int l, StructOuputReady sc)
{
	string retour;
	ostringstream convert;

	convert << sc.capaciteMax;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.idTrainBateau + SEPARATION + convert.str();

	return retour;
}

StructOuputReady parseOutputReady(string s)
{
	StructOuputReady si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.idTrainBateau = token;

	getline(iss, token, SEPARATION);
	si.capaciteMax = atoi((char*)token.c_str());

	return si;
}

string composeOutputOne(int l, StructOutputOne sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.idContainer;

	return retour;
}

StructOutputOne parseOutputOne(string s)
{
	StructOutputOne si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.idContainer = token;

	return si;
}

string composeOutputDone(int l, StructOutputDone sc)
{
	string retour;
	ostringstream convert;

	convert << sc.nbrContainers;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.idTrainBateau + SEPARATION + convert.str();

	return retour;
}

StructOutputDone parseOutputDone(string s)
{
	StructOutputDone si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.idTrainBateau = token;

	getline(iss, token, SEPARATION);
	si.nbrContainers = atoi((char*)token.c_str());

	return si;
}



