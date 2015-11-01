CC = g++ -w

LIB = ../Librairie
LIBESOCK = ../Librairie/socket
LIBPROP = ../Librairie/fichierProp
LIBEX = ../Librairie/exceptions
LIBCONT =  ../LibrairieConteneur
LIBLOG = ../Librairie/log
LIBCOMMON = ../CommonProtocolFunction

OBJ = $(LIBCONT)/sendFunction.o $(LIBCOMMON)/commonFunction.o
OBJSOCKET = $(LIBESOCK)/socket.o $(LIBESOCK)/socketClient.o
OBJUTILITY = $(LIBPROP)/fichierProp.o $(LIB)/utility.o $(LIBLOG)/log.o
OBJEXCEPTION = $(LIBEX)/errnoException.o $(LIBEX)/communicationException.o

client:	client.cpp $(OBJ) $(OBJSOCKET) $(OBJUTILITY) $(OBJEXCEPTION)
		echo creation client
		$(CC) -o client client.cpp $(OBJ) $(OBJSOCKET) $(OBJUTILITY) $(OBJEXCEPTION)

$(LIBESOCK)/socketClient.o:		$(LIBESOCK)/socketClient.cpp $(LIBESOCK)/socket.o $(LIBEX)/errnoException.o
								echo creation socketClient
								$(CC) -c $(LIBESOCK)/socketClient.cpp $(LIBESOCK)/socket.o
								mv socketClient.o $(LIBESOCK)


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

$(LIBCOMMON)/commonFunction.o:	$(LIBCOMMON)/commonFunction.cpp $(LIB)/utility.o
								echo creation commonFunction
								$(CC) -c $(LIBCOMMON)/commonFunction.cpp
								mv commonFunction.o $(LIBCOMMON)

													