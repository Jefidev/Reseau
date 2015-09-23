#ifndef FICHIERPROP_H_INCLUDED
#define FICHIERPROP_H_INCLUDED

typedef struct
{
  char cle[40];
  char valeur[40];
} FPENTREE;


class FichierProp
{
	private :
		string nomFichier;
		
	public :

		FichierProp(string n);
		~FichierProp();
};

#endif