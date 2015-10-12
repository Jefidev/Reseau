#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <errno.h>

using namespace std;

#include "fichierProp.h"


/****************************************************************************
*Vérifie si le fichier existe. Sinon il est créé avec des valeurs par défaut
*****************************************************************************/
FichierProp::FichierProp(string n)
{
	FILE* file;

	char * nomFichierChar = new char [n.length()+1];
  	strcpy (nomFichierChar, n.c_str());

	file = fopen(nomFichierChar, "r");

	if(file == (FILE*) NULL)
	{
		file = fopen(nomFichierChar, "w");

		if(file == (FILE*)NULL)
		{
			cout << "Impossible de créer le fichier properties" << endl;
			exit(-1);
		}

		fputs("HOST=localhost\n", file);	// Bidonnage de fichier à la première ouverture
		fputs("PORT=31040\n", file);
		fputs("ISIP=0\n", file);
	}

	fclose(file);
	delete nomFichierChar;

	nomFichier = n;
	separator = '=';
}

FichierProp::~FichierProp(){}

FichierProp::FichierProp(string n, char sepa)
{
	FILE* file;

	char * nomFichierChar = new char [n.length()+1];
  	strcpy (nomFichierChar, n.c_str());

	file = fopen(nomFichierChar, "r");

	if(file == (FILE*) NULL)
	{
		file = fopen(nomFichierChar, "w");

		if(file == (FILE*)NULL)
		{
			cout << "Impossible de créer le fichier properties" << endl;
			exit(-1);
		}

		fputs("jerome;fink\n", file);
		fputs("oceane;seel\n", file);
		fputs("ben;dem\n", file);
	}

	fclose(file);
	delete nomFichierChar;

	nomFichier = n;
	separator = sepa;
}


/******************************************************************************************************
*Reçoit la valeur d'une clé en parametre et cherche la valeur correspondante dans la fichier properties
*******************************************************************************************************/
string FichierProp::getValue(string v)
{
	FILE* file;
	char lec[255];
	char* p, *saveptr;
	char * nomFichierChar = new char [nomFichier.length()+1];
  	strcpy (nomFichierChar, nomFichier.c_str());

	file = fopen(nomFichierChar, "r");
	delete nomFichierChar;

	if(file == (FILE*)NULL)
	{
		cout << "Erreur de lecture du fichier : " << errno << endl;
		exit(-1);
	}

	int sortie = 0;

	while (sortie == 0 && (fgets(lec, 255, file)))
	{
	 	strtok_r(lec, &separator, &saveptr);
	  	
	  	if(v.compare(lec) == 0)
	  		sortie = 1;
	  	  	
	}

	if(sortie == 0)
	  return "";
	  	
	p = strtok_r(NULL, &separator, &saveptr);

	fclose(file);

	string fullString = string(p);

	return fullString.substr(0, fullString.size()-1);  

}

