CC = g++

OBJ = Librairie/socket.o Librairie/socketServeur.o Librairie/socketClient.o

all: serveur client

serveur:	serveur.cpp $(OBJ)
			echo creation serveur
			$(CC) -o serveur serveur.cpp $(OBJ)

client:	client.cpp $(OBJ)
		echo creation client
		$(CC) -o client client.cpp $(OBJ)

Librairie/socketClient.o:	Librairie/socketClient.cpp Librairie/socket.o
							echo creation socketClient
							$(CC) -c Librairie/socketClient.cpp Librairie/socket.o
							mv socketClient.o Librairie$

Librairie/socketServeur.o:	Librairie/socketServeur.cpp Librairie/socket.o
							echo creation socketServeur
							$(CC) -c Librairie/socketServeur.cpp Librairie/socket.o
							mv socketServeur.o Librairie


Librairie/socket.o:	Librairie/socket.cpp Librairie/socket.h
					echo creation socket.o
					$(CC) -c Librairie/socket.cpp
					mv socket.o Librairie


