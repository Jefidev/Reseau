#ifndef PARC_H_INCLUDED
#define PARC_H_INCLUDED

class Parc
{
	private :
		string nomFichier;
		
	public :

		Parc(string n);

		string getFirstFree();
		void freeSpace(string str);
		void placeContainer(StructInputDone sid);
		string outputList(StructOuputReady sor);
		void afficheFichier();
		
};

#endif