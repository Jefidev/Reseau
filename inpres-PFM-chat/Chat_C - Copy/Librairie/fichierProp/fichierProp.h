#ifndef FICHIERPROP_H_INCLUDED
#define FICHIERPROP_H_INCLUDED

class FichierProp
{
	private :
		string nomFichier;
		char separator;
		
	public :

		FichierProp(string n);
		FichierProp(string n, char sepa);
		~FichierProp();

		/*METHODES*/
		string getValue(string v);
};

#endif