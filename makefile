
CC = g++ 

all: serveur client

serveur:	serveur.cpp
	echo creation serveur
	$(CC) -o serveur serveur.cpp

client:	client.cpp
	echo creation client
	$(CC) -o client client.cpp
