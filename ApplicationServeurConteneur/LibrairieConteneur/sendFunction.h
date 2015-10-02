#ifndef SENDFUNCTION_H_INCLUDED
#define SENDFUNCTION_H_INCLUDED
	
	string typeRequestParse(string s, int* type);

	string composeConnexion(char l, StructConnexion sc);
	StructConnexion parseConnexion(string s);

	string composeInputTruck(char l, StructInputTruck sc);
	StructInputTruck parseInputTruck(string s);

	string composeInputDone(char l, StructInputDone sc);
	StructInputDone parseInputDone(string s);

	string composeOutputReady(char l, StructOuputReady sc);
	StructOuputReady parseOutputReady(string s);

	string composeOutputOne(char l, StructOutputOne sc);
	StructOutputOne parseOutputOne(string s);

	string composeOutputDone(char l, StructOutputDone sc);
	StructOutputDone parseOutputDone(string s);

#endif

