cmake_minimum_required(VERSION 3.3)
project(ApplicationServeurConteneur)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    Application_Conteneurs/client.cpp
    Application_Conteneurs/threadUrgence.cpp
    Application_Conteneurs/threadUrgence.h
    CommonProtocolFunction/commonFunction.cpp
    CommonProtocolFunction/commonFunction.h
    Librairie/exceptions/communicationException.cpp
    Librairie/exceptions/communicationException.h
    Librairie/exceptions/errnoException.cpp
    Librairie/exceptions/errnoException.h
    Librairie/fichierProp/fichierProp.cpp
    Librairie/fichierProp/fichierProp.h
    Librairie/log/log.cpp
    Librairie/log/log.h
    Librairie/socket/socket.cpp
    Librairie/socket/socket.h
    Librairie/socket/socketClient.cpp
    Librairie/socket/socketClient.h
    Librairie/socket/socketServeur.cpp
    Librairie/socket/socketServeur.h
    Librairie/utility.cpp
    Librairie/utility.h
    LibrairieConteneur/sendFunction.cpp
    LibrairieConteneur/sendFunction.h
    LibrairieCSA/sendCSAFunction.cpp
    LibrairieCSA/sendCSAFunction.h
    Serveur_Conteneurs/threadAdmin/threadAdmin.cpp
    Serveur_Conteneurs/threadAdmin/threadAdmin.h
    Serveur_Conteneurs/threadAdmin/traitementAdmin.cpp
    Serveur_Conteneurs/threadAdmin/traitementAdmin.h
    Serveur_Conteneurs/constante.h
    Serveur_Conteneurs/parc.cpp
    Serveur_Conteneurs/parc.h
    Serveur_Conteneurs/serveur.cpp)

add_executable(ApplicationServeurConteneur ${SOURCE_FILES})