#ifndef FICHIERPROP_H_INCLUDED
#define FICHIERPROP_H_INCLUDED

class FichierProp
{
	private :
		string nomFichier;
		
	public :

		FichierProp(string n);
		~FichierProp();

		/*METHODES*/
		string getValue(string v);
};

#endif