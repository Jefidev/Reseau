#ifndef TRAITEMENTADMIN_H_INCLUDED
#define TRAITEMENTADMIN_H_INCLUDED

	void* traitementAdmin(void* p);

	bool login(StructLogin log, Socket* s);
	void logout(int cTraite, Socket* s);

#endif