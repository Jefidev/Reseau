#ifndef COMMUNICATIONEXCEPTION_H_INCLUDED
#define COMMUNICATIONEXCEPTION_H_INCLUDED

class CommunicationException
{
	private :
		string message;


	public :
		CommunicationException(string message);

		string getMessage();
};

#endif

