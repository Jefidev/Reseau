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
#include "../CommonProtocolFunction/commonFunction.h"

string composeConnexion(int l, StructConnexion sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.nom + SEPARATION + sc.motDePasse + SEPARATION + sc.port;
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

	getline(iss, token, SEPARATION);
	sc.port = token;

	return sc;
}

string composeInputTruck(int l, StructInputTruck sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.immatriculation + SEPARATION + sc.societe+ SEPARATION +sc.idContainers;

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
	si.societe = token;

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
	retour = retour + SEPARATION + convert.str() + SEPARATION + sc.coord + SEPARATION + sc.id;

	return retour;
}

StructInputDone parseInputDone(string s)
{
	StructInputDone si;

	istringstream iss(s);
	string token;

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

string composeOutputReady(int l, StructOuputReady sc)
{
	string retour;
	ostringstream convert;

	convert << sc.capaciteMax << SEPARATION << sc.type << SEPARATION;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.idTrainBateau + SEPARATION + convert.str() + sc.destination;
	
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

	getline(iss, token, SEPARATION);
	si.type = atoi((char*)token.c_str());

	getline(iss, token, SEPARATION);
	si.destination = token;

	return si;
}

string composeOutputOne(int l, StructOutputOne sc)
{
	string retour;

	retour = Utility::intToString(l);
	retour = retour + SEPARATION + sc.emplacement;

	return retour;
}

StructOutputOne parseOutputOne(string s)
{
	StructOutputOne si;

	istringstream iss(s);
	string token;

	getline(iss, token, SEPARATION);
	si.emplacement = token;

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



