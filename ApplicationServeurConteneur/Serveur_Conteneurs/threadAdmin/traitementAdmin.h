#ifndef TRAITEMENTADMIN_H_INCLUDED
#define TRAITEMENTADMIN_H_INCLUDED

	void* traitementAdmin(void* p);

	bool login(StructLogin log, Socket* s);
	void logout(int cTraite, Socket* s);
	void listClient(Socket* s);

	void pauseServer(Socket* s);
	void continueServer(Socket* s);
	void shutdownServer(Socket* s, int sec);

#endif