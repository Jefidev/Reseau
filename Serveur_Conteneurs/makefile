CC = g++ -w

OBJ = ../Librairie/socket/socket.o ../Librairie/socket/socketServeur.o ../Librairie/fichierProp/fichierProp.o ../Librairie/utility.o ../Librairie/exceptions/errnoException.o ../Librairie/exceptions/communicationException.o ../LibrairieConteneur/sendFunction.o

serveur:	serveur.cpp $(OBJ)
			echo creation serveur
			$(CC) -o serveur serveur.cpp $(OBJ)


../Librairie/socket/socketServeur.o:	../Librairie/socket/socketServeur.cpp ../Librairie/socket/socket.o ../Librairie/exceptions/errnoException.o
										echo creation socketServeur
										$(CC) -c ../Librairie/socket/socketServeur.cpp ../Librairie/socket/socket.o
										mv socketServeur.o ../Librairie/socket


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

