#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>

using namespace std;

#include "parc.h"
#include "../LibrairieConteneur/protocole.ini"

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

void Parc::getFirstFree(int* x, int* y)
{
	char * nomFichierChar = new char [nomFichier.length()+1];
  	strcpy (nomFichierChar, nomFichier.c_str());
  	FILE* f;

	f = fopen(nomFichierChar, "r");

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
		*x = r.x;
		*y = r.y;
		return;
	}

	*x = -1;
	*y = -1;
}

