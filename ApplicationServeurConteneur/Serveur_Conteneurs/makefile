CC = g++ -w

LIB = ../Librairie
LIBESOCK = ../Librairie/socket
LIBPROP = ../Librairie/fichierProp
LIBEX = ../Librairie/exceptions
LIBCONT =  ../LibrairieConteneur
LIBLOG = ../Librairie/log
LIBADMIN = threadAdmin
LIBCSA = ../LibrairieCSA
LIBCOMMON = ../CommonProtocolFunction

OBJ =  $(LIBCONT)/sendFunction.o  $(LIBADMIN)/threadAdmin.o $(LIBCSA)/sendCSAFunction.o $(LIBCOMMON)/commonFunction.o $(LIBADMIN)/traitementAdmin.o
OBJSOCKET = $(LIBESOCK)/socket.o $(LIBESOCK)/socketServeur.o
OBJUTILITY = $(LIBPROP)/fichierProp.o $(LIB)/utility.o $(LIBLOG)/log.o parc.o
OBJEXCEPTION = $(LIBEX)/errnoException.o $(LIBEX)/communicationException.o

serveur:	serveur.cpp $(OBJ) $(OBJSOCKET) $(OBJUTILITY) $(OBJEXCEPTION)
			echo creation serveur
			$(CC) -o serveur serveur.cpp $(OBJ) $(OBJSOCKET) $(OBJUTILITY) $(OBJEXCEPTION)


$(LIBESOCK)/socketServeur.o:	$(LIBESOCK)/socketServeur.cpp $(LIBESOCK)/socket.o $(LIBEX)/errnoException.o
								echo creation socketServeur
								$(CC) -c $(LIBESOCK)/socketServeur.cpp $(LIBESOCK)/socket.o
								mv socketServeur.o $(LIBESOCK)


$(LIBESOCK)/socket.o:	$(LIBESOCK)/socket.cpp $(LIBESOCK)/socket.h $(LIB)/utility.o $(OBJEXCEPTION)
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

$(LIBCONT)/sendFunction.o:	$(LIBCONT)/sendFunction.cpp $(LIB)/utility.o $(LIBCOMMON)/commonFunction.o
							echo creation sendFunction.o
							$(CC) -c $(LIBCONT)/sendFunction.cpp
							mv sendFunction.o $(LIBCONT)

$(LIBLOG)/log.o:	$(LIBLOG)/log.cpp
					echo creation du log
					$(CC) -c $(LIBLOG)/log.cpp
					mv log.o $(LIBLOG)

parc.o:	parc.cpp $(LIB)/utility.o
		echo creation du log
		$(CC) -c parc.cpp

$(LIBADMIN)/threadAdmin.o:	$(LIBADMIN)/threadAdmin.cpp $(OBJSOCKET) $(LIBPROP)/fichierProp.o $(LIBEX)/errnoException.o $(LIBADMIN)/traitementAdmin.o
							echo creation threadAdmin
							$(CC) -c $(LIBADMIN)/threadAdmin.cpp
							mv threadAdmin.o $(LIBADMIN)

$(LIBCSA)/sendCSAFunction.o: 	$(LIBCSA)/sendCSAFunction.cpp  $(LIB)/utility.o $(LIBCOMMON)/commonFunction.o
								echo creation sendCSAFuncion
								$(CC) -c $(LIBCSA)/sendCSAFunction.cpp
								mv sendCSAFunction.o $(LIBCSA)

$(LIBCOMMON)/commonFunction.o:	$(LIBCOMMON)/commonFunction.cpp $(LIB)/utility.o
								echo creation commonFunction
								$(CC) -c $(LIBCOMMON)/commonFunction.cpp
								mv commonFunction.o $(LIBCOMMON)

$(LIBADMIN)traitementAdmin.o:	$(LIBADMIN)/traitementAdmin.cpp $(LIBESOCK)/socket.o $(LIBEX)/errnoException.o
								echo creation traitementAdmin
								$(CC) -c $(LIBADMIN)/traitementAdmin.cpp
								mv traitementAdmin.o $(LIBADMIN)

