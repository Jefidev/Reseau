#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <sstream>

using namespace std;

#include "../LibrairieConteneur/protocole.ini"
#include "parc.h"
#include "../Librairie/utility.h"

Parc::Parc(string n)
{
	char * nomFichierChar = new char [n.length()+1];
  	strcpy (nomFichierChar, n.c_str());
  	FILE* f;

	f = fopen(nomFichierChar, "r");

	if(f == (FILE*) NULL)
	{
		f = fopen(nomFichierChar, "w");

		if(f == (FILE*)NULL)
		{
			cout << "Impossible de créer le fichier parc" << endl;
			exit(-1);
		}

		RECORD r;
		r.flagEtat = 0;

		for(int i = 1; i < 11; i++)
		{
			r.x = i;
			r.y = i;
			fwrite(&r, 1, sizeof(RECORD), f);
		}
	}

	delete nomFichierChar;
	fclose(f);

	nomFichier = n;
}

string Parc::getFirstFree()
{
	char * nomFichierChar = new char [nomFichier.length()+1];
  	strcpy (nomFichierChar, nomFichier.c_str());
  	FILE* f;

	f = fopen(nomFichierChar, "r+");

	if(f == (FILE*) NULL)
	{
		cout << "erreur lecture " << endl;
		exit(0);
	}

	RECORD r;
	while(fread(&r, 1, sizeof(RECORD), f) == sizeof(RECORD))
	{
		if(r.flagEtat == 0)
			break;
	}

	if(r.flagEtat == 0)
	{
		string ret = Utility::intToString(r.x) + ";" + Utility::intToString(r.y);
		r.flagEtat = 1;
		
		fseek(f, -(long)sizeof(RECORD), SEEK_CUR);
		fwrite(&r, 1, sizeof(RECORD), f);

		fclose(f);
		delete nomFichierChar;
		return ret;
	}

	fclose(f);
	delete nomFichierChar;
	return "";
}


void Parc::freeSpace(string str)
{
	char * nomFichierChar = new char [nomFichier.length()+1];
  	strcpy (nomFichierChar, nomFichier.c_str());
  	FILE* f;

  	f = fopen(nomFichierChar, "r+");

  	if(f == (FILE*) NULL)
	{
		cout << "erreur lecture " << endl;
		exit(0);
	}

	char *lec, *tok, *saveptrAllChar, *saveptrx;
	char sep = CONTAINER_SEPARATION, sep2 = ';';

	lec = new char [str.length()+1];
	strcpy (lec, str.c_str());

	tok = strtok_r(lec, &sep, &saveptrAllChar);

	while(tok != NULL)
	{
		
		int x, y;

		x = atoi(strtok_r(tok, &sep2, &saveptrx));
		y = atoi(strtok_r(NULL, &sep2, &saveptrx));
		RECORD r;

		while(fread(&r, 1, sizeof(RECORD), f) == sizeof(RECORD))
		{
			if(r.x == x && r.y == y)
				break;
		}

		if(r.x == x && r.y == y)
		{
			r.flagEtat = 0;
			fseek(f, -(long)sizeof(RECORD), SEEK_CUR);
			fwrite(&r, 1, sizeof(RECORD), f);
		}

		fseek(f, 0, SEEK_SET);

		tok = strtok_r(NULL, &sep, &saveptrAllChar);
	}
	delete lec;
	delete nomFichierChar;
	fclose(f);
}

void Parc::placeContainer(StructInputDone sid)
{

	char *lec, sep = ';', *saveptr;

	lec = new char [sid.coord.length()+1];
	strcpy (lec, sid.coord.c_str());

	int x, y;

	x = atoi(strtok_r(lec, &sep, &saveptr));
	y = atoi(strtok_r(NULL, &sep, &saveptr));

	char * nomFichierChar = new char [nomFichier.length()+1];
  	strcpy (nomFichierChar, nomFichier.c_str());
  	FILE* f;

  	f = fopen(nomFichierChar, "r+");

  	if(f == (FILE*) NULL)
	{
		cout << "erreur lecture " << endl;
		exit(0);
	}

	RECORD r;

	while(fread(&r, 1, sizeof(RECORD), f) == sizeof(RECORD))
	{
		if(r.x == x && r.y == y)
			break;
	}

	if(r.x == x && r.y == y)
	{
		r.flagEtat = 2;
		strcpy(r.IDcontainer, sid.id.c_str());
		strcpy(r.destination, sid.destination.c_str());
		r.moyenTransport = sid.transport;

		fseek(f, -(long)sizeof(RECORD), SEEK_CUR);
		fwrite(&r, 1, sizeof(RECORD), f);
	}

	fseek(f, 0, SEEK_SET);

	while(fread(&r, 1, sizeof(RECORD), f) == sizeof(RECORD))
	{
		cout << r.flagEtat << endl;
	}


	fclose(f);
	delete lec;
	delete nomFichierChar;
}

string Parc::outputList(StructOuputReady sor)
{
	char * nomFichierChar = new char [nomFichier.length()+1];
  	strcpy (nomFichierChar, nomFichier.c_str());
  	FILE* f;
  	string retour;
  	RECORD r;

  	f = fopen(nomFichierChar, "r");

  	if(f == (FILE*) NULL)
	{
		cout << "erreur lecture " << endl;
		exit(0);
	}

	while(fread(&r, 1, sizeof(RECORD), f) == sizeof(RECORD))
	{
		if(!strcmp(sor.destination.c_str(), r.destination) && sor.type == r.moyenTransport)
		{
			ostringstream convert;
			convert << r.IDcontainer << CONTAINER_SEPARATION << r.x << ";" << r.y;

			retour = retour + convert.str() + SEPARATION;
		}
	}


	fclose(f);
	delete nomFichierChar;
	return retour;

}

