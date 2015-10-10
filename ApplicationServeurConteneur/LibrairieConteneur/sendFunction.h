#ifndef SENDFUNCTION_H_INCLUDED
#define SENDFUNCTION_H_INCLUDED
	
	string typeRequestParse(string s, int* type);

	string composeAckErr(int l, string m);

	string composeConnexion(int l, StructConnexion sc);
	StructConnexion parseConnexion(string s);

	string composeInputTruck(int l, StructInputTruck sc);
	StructInputTruck parseInputTruck(string s);

	string composeInputDone(int l, StructInputDone sc);
	StructInputDone parseInputDone(string s);

	string composeOutputReady(int l, StructOuputReady sc);
	StructOuputReady parseOutputReady(string s);

	string composeOutputOne(int l, StructOutputOne sc);
	StructOutputOne parseOutputOne(string s);

	string composeOutputDone(int l, StructOutputDone sc);
	StructOutputDone parseOutputDone(string s);

	string composeContPos(int l, StructContainerPosition sc);
	StructContainerPosition parseContPos(string s);

#endif

