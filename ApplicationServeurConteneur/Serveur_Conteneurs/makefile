CC = g++ -w

LIB = ../Librairie
LIBESOCK = ../Librairie/socket
LIBPROP = ../Librairie/fichierProp
LIBEX = ../Librairie/exceptions
LIBCONT =  ../LibrairieConteneur
LIBLOG = ../Librairie/log

OBJ = $(LIBESOCK)/socket.o $(LIBESOCK)/socketServeur.o $(LIBPROP)/fichierProp.o $(LIB)/utility.o $(LIBEX)/errnoException.o $(LIBEX)/communicationException.o $(LIBCONT)/sendFunction.o $(LIBLOG)/log.o

serveur:	serveur.cpp $(OBJ)
			echo creation serveur
			$(CC) -o serveur serveur.cpp $(OBJ)


$(LIBESOCK)/socketServeur.o:	$(LIBESOCK)/socketServeur.cpp $(LIBESOCK)/socket.o $(LIBEX)/errnoException.o
								echo creation socketServeur
								$(CC) -c $(LIBESOCK)/socketServeur.cpp $(LIBESOCK)/socket.o
								mv socketServeur.o $(LIBESOCK)


$(LIBESOCK)/socket.o:	$(LIBESOCK)/socket.cpp $(LIBESOCK)/socket.h $(LIB)/utility.o $(LIBEX)/errnoException.o $(LIBEX)/communicationException.o
						echo creation socket.o
						$(CC) -c $(LIBESOCK)/socket.cpp
						mv socket.o $(LIBESOCK)

$(LIBPROP)/fichierProp.o:	$(LIBPROP)/fichierProp.cpp
							echo creation fichierprop.o
							$(CC) -c $(LIBPROP)/fichierProp.cpp
							mv fichierProp.o $(LIBPROP)


$(LIB)/utility.o:	$(LIB)/utility.cpp
					echo creation de utility.o
					$(CC) -c $(LIB)/utility.cpp
					mv utility.o $(LIB)

$(LIBEX)/errnoException.o:	$(LIBEX)/errnoException.cpp
							echo creation errnoException.o
							$(CC) -c $(LIBEX)/errnoException.cpp
							mv errnoException.o $(LIBEX)


$(LIBEX)/communicationException.o:	$(LIBEX)/communicationException.cpp
									echo creation communicationException.o
									$(CC) -c $(LIBEX)/communicationException.cpp
									mv communicationException.o $(LIBEX)

$(LIBCONT)/sendFunction.o:	$(LIBCONT)/sendFunction.cpp $(LIB)/utility.o
							echo creation sendFunction.o
							$(CC) -c $(LIBCONT)/sendFunction.cpp
							mv sendFunction.o $(LIBCONT)

$(LIBLOG)/log.o:	$(LIBLOG)/log.cpp
					echo creation du log
					$(CC) -c $(LIBLOG)/log.cpp
					mv log.o $(LIBLOG)



