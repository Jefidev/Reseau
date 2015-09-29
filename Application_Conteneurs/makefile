CC = g++ -fpermissive

OBJ = ../Librairie/socket/socket.o ../Librairie/socket/socketClient.o ../Librairie/fichierProp/fichierProp.o ../Librairie/utility.o ../Librairie/exceptions/errnoException.o ../Librairie/exceptions/communicationException.o ../LibrairieConteneur/sendFunction.o


client:	client.cpp $(OBJ)
		echo creation client
		$(CC) -o client client.cpp $(OBJ)

../Librairie/socket/socketClient.o:	../Librairie/socket/socketClient.cpp ../Librairie/socket/socket.o ../Librairie/exceptions/errnoException.o
									echo creation socketClient
									$(CC) -c ../Librairie/socket/socketClient.cpp
									mv socketClient.o ../Librairie/socket


../Librairie/socket/socket.o:	../Librairie/socket/socket.cpp ../Librairie/socket/socket.h ../Librairie/utility.o ../Librairie/exceptions/errnoException.o ../Librairie/exceptions/communicationException.o
								echo creation socket.o
								$(CC) -c ../Librairie/socket/socket.cpp
								mv socket.o ../Librairie/socket

../Librairie/fichierProp/fichierProp.o:	../Librairie/fichierProp/fichierProp.cpp
										echo creation fichierprop.o
										$(CC) -c ../Librairie/fichierProp/fichierProp.cpp
										mv fichierProp.o ../Librairie/fichierProp

../Librairie/utility.o:	../Librairie/utility.cpp
						echo creation de utility.o
						$(CC) -c ../Librairie/utility.cpp
						mv utility.o ../Librairie

../Librairie/exceptions/errnoException.o:	../Librairie/exceptions/errnoException.cpp
											echo creation errnoException.o
											$(CC) -c ../Librairie/exceptions/errnoException.cpp
											mv errnoException.o ../Librairie/exceptions


../Librairie/exceptions/communicationException.o:	../Librairie/exceptions/communicationException.cpp
													echo creation communicationException.o
													$(CC) -c ../Librairie/exceptions/communicationException.cpp
													mv communicationException.o ../Librairie/exceptions

../LibrairieConteneur/sendFunction.o:	../LibrairieConteneur/sendFunction.cpp
										echo creation sendFunction.o
										$(CC) -c ../LibrairieConteneur/sendFunction.cpp
										mv sendFunction.o ../LibrairieConteneur
													