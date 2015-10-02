#ifndef ERRNOEXCEPTION_H_INCLUDED
#define ERRNOEXCEPTION_H_INCLUDED

class ErrnoException
{
	private :
		int errorNumber;
		string message;


	public :
		ErrnoException(int i, string message);

		int getErrorCode();
		string getMessage();
};

#endif