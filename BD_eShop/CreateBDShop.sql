DROP TABLE PRODUITS_COMMMANDES;
DROP TABLE COMMMANDES;
DROP TABLE CUSTOMERS;
DROP TABLE RESERVATIONS_PARC;
DROP TABLE PRODUITS;

CREATE TABLE CUSTOMERS
(
	LOGIN				VARCHAR2(30) CONSTRAINT PK_CUSTOMERS PRIMARY KEY,
	PASSWORD			VARCHAR2(30)
);

CREATE TABLE RESERVATIONS_PARC
(
	DATE_RESERVATION		VARCHAR2(30) CONSTRAINT PK_RESERVATION PRIMARY KEY,
	NBR_RESERVATIONS		NUMBER,
	ATTENTE_CONFIRMATION	NUMBER
);

CREATE TABLE PRODUITS
(
	ID_PRODUIT	NUMBER CONSTRAINT PK_PRODUITS PRIMARY KEY,
	NOM			VARCHAR2(30),
	DESCRIPTION	VARCHAR2(200),
	PRIX 		NUMBER,
	QUANTITE	NUMBER,
	RESERVE		NUMBER
);

CREATE TABLE COMMANDES
(
	ID_COMMANDE		NUMBER CONSTRAINT PK_COMMANDE PRIMARY KEY,
	ID_CLIENT		VARCHAR2(30) CONSTRAINT REF_CLIENT REFERENCES CUSTOMERS(LOGIN),
	DATE_COMMANDE 	NUMBER,
	TOTAL 			NUMBER
);

CREATE TABLE PRODUITS_COMMMANDES
(
	ID_COMMANDE NUMBER CONSTRAINT REF_COMMANDE REFERENCES COMMANDES(ID_COMMANDE),
	ID_PRODUIT 	NUMBER CONSTRAINT REF_PRODUIT REFERENCES PRODUITS(ID_PRODUIT),
	QUANTITE	NUMBER
);
