CC = g++

OBJ = Librairie/socket.o

all: serveur client

serveur:	serveur.cpp $(OBJ)
			echo creation serveur
			$(CC) -o serveur serveur.cpp $(OBJ)

client:	client.cpp $(OBJ)
		echo creation client
		$(CC) -o client client.cpp $(OBJ)

Librairie/socket.o:	Librairie/socket.cpp Librairie/socket.h
					echo creation socket.o
					$(CC) -c Librairie/socket.cpp
					mv socket.o Librairie
