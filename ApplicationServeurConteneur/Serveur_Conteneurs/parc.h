#ifndef PARC_H_INCLUDED
#define PARC_H_INCLUDED

class Parc
{
	private :
		string nomFichier;
		
	public :

		Parc(string n);

		string getFirstFree();
		
};

#endif