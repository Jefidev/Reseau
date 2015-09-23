CC = g++

OBJ = ../Librairie/socket/socket.o ../Librairie/socket/socketClient.o


client:	client.cpp $(OBJ)
		echo creation client
		$(CC) -o client client.cpp $(OBJ)

Librairie/socketClient.o:	../Librairie/socket/socketClient.cpp ../Librairie/socket/socket.o
							echo creation socketClient
							$(CC) -c ../Librairie/socket/socketClient.cpp
							mv socketClient.o ../Librairie/socket


Librairie/socket.o:	../Librairie/socket/socket.cpp ../Librairie/socket/socket.h
					echo creation socket.o
					$(CC) -c ../Librairie/socket/socket.cpp
					mv socket.o ../Librairie/socket


