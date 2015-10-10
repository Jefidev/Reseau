DROP TABLE UTILISATEURS;
DROP TABLE PARC;
DROP TABLE MOUVEMENTS;
DROP TABLE TRANSPORTEURS;
DROP TABLE CONTAINERS;
DROP TABLE SOCIETES;
DROP TABLE DESTINATIONS;





CREATE TABLE UTILISATEURS
(
	LOGIN		VARCHAR2(30) CONSTRAINT PK_UTILISATEURS_ID PRIMARY KEY,
	PASSWORD	VARCHAR2(30)
);

CREATE TABLE SOCIETES
(
	ID_SOCIETE		VARCHAR2(20) CONSTRAINT PK_SOCIETES_ID PRIMARY KEY,
	CONTACT_NAME	VARCHAR2(20),
	MAIL			VARCHAR2(20),
	TELEPHONE		VARCHAR2(20),
	ADRESSE			VARCHAR2(50)
);

CREATE TABLE CONTAINERS
(
	ID_CONTAINER	VARCHAR2(20) CONSTRAINT PK_CONTAINERS_ID PRIMARY KEY,
	ID_SOCIETE		VARCHAR2(20) CONSTRAINT REF_SOCIETES_CONTAINERS REFERENCES SOCIETES(ID_SOCIETE),
	CONTENU			VARCHAR2(20),
	DANGERS			VARCHAR2(20)
);

CREATE TABLE DESTINATIONS
(
	VILLE				VARCHAR2(20) CONSTRAINT PK_DESTINATIONS_ID	PRIMARY KEY,
	DISTANCE_BATEAU		NUMBER(4),
	DISTANCE_TRAIN		NUMBER(4),
	DISTANCE_ROUTE		NUMBER(4)
);


CREATE TABLE PARC
(
	X					NUMBER(3),
	Y					NUMBER(3),
	ID_CONTAINER		VARCHAR(20) CONSTRAINT REF_CONTAINERS_ID REFERENCES CONTAINERS(ID_CONTAINER),
	ETAT				NUMBER(1) CONSTRAINT CK_ETAT_VALUE CHECK (ETAT = 0 OR ETAT = 1 OR ETAT = 2),
	DATE_RESERVATION	DATE,
	DATE_ARRIVEE		DATE,
	POIDS				NUMBER(3),
	DESTINATION			VARCHAR2(20) CONSTRAINT REF_DESTINATIONS_PARC REFERENCES DESTINATIONS(VILLE),
	TRANSPORT			VARCHAR2(6) CONSTRAINT CK_TRANSPORT_VALUE CHECK (TRANSPORT LIKE 'TRAIN' OR TRANSPORT LIKE 'BATEAU' OR TRANSPORT IS NULL),
	CONSTRAINT PK_PARC_X_Y  PRIMARY KEY (X, Y)
);

CREATE TABLE TRANSPORTEURS
(
	ID_TRANSPORTEUR		VARCHAR2(20) CONSTRAINT PK_TRANSPORTEURS_ID PRIMARY KEY,
	ID_SOCIETE 			VARCHAR2(20) CONSTRAINT REF_SOCIETES_TRANSPORTEURS REFERENCES SOCIETES(ID_SOCIETE),
	CARACTERISITIQUES	VARCHAR(100)

);

CREATE TABLE MOUVEMENTS
(
	ID_MOUVEMENT				VARCHAR2(20) CONSTRAINT PK_MOUVEMENT_ID PRIMARY KEY,
	ID_CONTAINER				VARCHAR2(20) CONSTRAINT REF_CONTAINERS_MOUVEMENTS REFERENCES CONTAINERS(ID_CONTAINER),
	ID_TRANSPORTEUR_ENTRANT		VARCHAR2(20) CONSTRAINT REF_TRANSPORTEURS_ENTRANT REFERENCES TRANSPORTEURS(ID_TRANSPORTEUR),
	DATE_ARRIVEE				DATE,
	ID_TRANSPORTEUR_SORTANT		VARCHAR2(20) CONSTRAINT REF_TRANSPORTEURS_SORTANT REFERENCES TRANSPORTEURS(ID_TRANSPORTEUR),
	POIDS						NUMBER(3),
	DATE_DEPART					DATE,
	DESTINATION					VARCHAR2(20) CONSTRAINT REF_DESTINATIONS_MOUVEMENTS REFERENCES DESTINATIONS(VILLE)
);
