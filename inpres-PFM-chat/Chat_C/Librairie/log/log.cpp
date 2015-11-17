#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <iostream>
#include <errno.h>
#include <pthread.h>

using namespace std;

#include "log.h"

Log::Log(string n)
{
	char * nomFichierChar = new char [n.length()+1];
  	strcpy (nomFichierChar, n.c_str());

	f = fopen(nomFichierChar, "a");

	if(f == (FILE*) NULL)
	{
		f = fopen(nomFichierChar, "w");

		if(f == (FILE*)NULL)
		{
			cout << "Impossible de créer le fichier properties" << endl;
			exit(-1);
		}
	}

	delete nomFichierChar;

	nomFichier = n;
}


Log::~Log()
{
	fclose(f);
}

string Log::writeLog(string l, pthread_mutex_t m)
{
	pthread_mutex_lock(&m);
	fputs(l.c_str(), f);
	pthread_mutex_unlock(&m);
}

string Log::writeLog(string l)
{
	fputs(l.c_str(), f);
}


