DROP TABLE RESULTATSSTATDESCR;
DROP TABLE RESULTATSTESTCONF;
DROP TABLE RESULTATSTESTHOMOG;
DROP TABLE RESULTATSTESTANOVA;



CREATE TABLE RESULTATSSTATDESCR
(
	ID				NUMBER CONSTRAINT PK_RESULTATSSTATDESCR PRIMARY KEY;
	MOYENNE			NUMBER
	MODE			VARCHAR2(100) -- Car il peut y avoir plusieurs modes
	MEDIANE			NUMBER
	ECARTTYPE		NUMBER
	NBCONTAINERS	NUMBER
	MOUVEMENT		VARCHAR2(3)
);


CREATE TABLE RESULTATSTESTCONF
(
	ID				NUMBER CONSTRAINT PK_RESULTATSTESTCONF PRIMARY KEY;
	PVALUE			NUMBER
	NBCONTAINERS	NUMBER
);


CREATE TABLE RESULTATSTESTHOMOG
(
	ID				NUMBER CONSTRAINT PK_RESULTATSTESTHOMOG PRIMARY KEY;
	PVALUE			NUMBER
	NBCONTAINERS	NUMBER
	DESTINATIONA	VARCHAR2(30)
	DESTINATIONB	VARCHAR2(30)
);


CREATE TABLE RESULTATSTESTANOVA
(
	ID				NUMBER CONSTRAINT PK_RESULTATSTESTANOVA PRIMARY KEY;
	PVALUE			NUMBER
	NBCONTAINERS	NUMBER
);



CREATE OR REPLACE SEQUENCE ID_RESULTATSSTATDESCR;
CREATE OR REPLACE SEQUENCE RESULTATSTESTCONF;
CREATE OR REPLACE SEQUENCE RESULTATSTESTHOMOG;
CREATE OR REPLACE SEQUENCE RESULTATSTESTANOVA;