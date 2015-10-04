#ifndef LOG_H_INCLUDED
#define LOG_H_INCLUDED

class Log
{
	private :
		string nomFichier;
		FILE* f;
		
	public :

		Log(string n);
		~Log();

		/*METHODES*/
		string writeLog(string l, pthread_mutex_t m);
		string writeLog(string l);

};

#endif