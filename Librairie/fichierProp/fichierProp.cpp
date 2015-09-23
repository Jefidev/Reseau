#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>

using namespace std;

#include "fichierProp.h"

FichierProp::FichierProp(string n)
{
	FILE* f;

	char * cstr = new char [n.length()+1];
  	strcpy (cstr, n.c_str());

	f = fopen(cstr, "r");

	if(f == (FILE*) NULL)
	{
		f = fopen(cstr, "w");

		if(f == (FILE*)NULL)
		{
			cout << "Impossible de créer le fichier properties" << endl;
			exit(-1);
		}

		fputs("HOST=localhost\n", f);
		fputs("PORT=31040\n", f);
		fputs("ISIP=0\n", f);
	}

	fclose(f);

	nomFichier = n;
}

FichierProp::~FichierProp()
{

}
