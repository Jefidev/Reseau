
CC = g++ 

serveur:	serveur.cpp
	echo creation serveur
	$(CC) -o serveur serveur.cpp
