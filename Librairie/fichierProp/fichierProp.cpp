#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <errno.h>

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
	delete cstr;

	nomFichier = n;
}

FichierProp::~FichierProp(){}

string FichierProp::getValue(string v)
{
	FILE* f;
	char lec[255];
	char* p;
	char * cstr = new char [nomFichier.length()+1];
  	strcpy (cstr, nomFichier.c_str());

	f = fopen(cstr, "r");

	if(f == (FILE*)NULL)
	{
		cout << "Erreur de lecture du fichier : " << errno << endl;
		exit(-1);
	}

	int sortie = 0;

	while (sortie == 0 && (fgets(lec, 255, f)))
	{
	 	strtok(lec, "=");
	  	
	  	if(v.compare(lec) == 0)
	  		sortie = 1;
	  	  	
	}
	  
	  if(sortie == 0)
	  	return 0;
	  	
	  p = strtok(NULL, "=");

	  fclose(f);
	  delete cstr;
	  
	  return string(p);  

}

