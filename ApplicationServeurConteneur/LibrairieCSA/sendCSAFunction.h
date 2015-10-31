#ifndef SENDFUNCTION_H_INCLUDED
#define SENDFUNCTION_H_INCLUDED
	
	string typeRequestParse(string s, int* type);

	string composeAckErr(int l, string m);

	string composeLogin(int l, StructLogin sc);
	StructLogin parseLogin(string s);

#endif

