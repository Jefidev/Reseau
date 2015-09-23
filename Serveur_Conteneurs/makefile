CC = g++

OBJ = ../Librairie/socket/socket.o ../Librairie/socket/socketServeur.o

serveur:	serveur.cpp $(OBJ)
			echo creation serveur
			$(CC) -o serveur serveur.cpp $(OBJ)


Librairie/socketServeur.o:	../Librairie/socket/socketServeur.cpp ../Librairie/socket/socket.o
							echo creation socketServeur
							$(CC) -c ../Librairie/socket/socketServeur.cpp ../Librairie/socket/socket.o
							mv socketServeur.o ../Librairie/socket


Librairie/socket.o:	../Librairie/socket/socket.cpp ..Librairie/socket/socket.h
					echo creation socket.o
					$(CC) -c ..Librairie/socket/socket.cpp
					mv socket.o ../Librairie/socket


