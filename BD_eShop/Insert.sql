--BIDONNAGE DE LA BD DU SITE E-COMMERCE

BEGIN
	INSERT INTO CUSTOMERS VALUES ('oce', 'oce');
	INSERT INTO CUSTOMERS VALUES ('je', 'je');


	INSERT INTO PRODUITS VALUES (1, 'carte', 'carte du site', 7, 10, 0);
	INSERT INTO PRODUITS VALUES (2, 'peluche', 'peluche en poil d''écureuil', 20, 5, 0);
	INSERT INTO PRODUITS VALUES (3, 'boussole', 'Ca indique le nord normalement', 5, 3, 0);
	INSERT INTO PRODUITS VALUES (4, 'canette d''air', 'Pour respirer de l''air pure', 20, 9, 0);

	INSERT INTO LANGUES VALUES ('Français');
	INSERT INTO LANGUES VALUES ('Anglais');
	
	COMMIT;
END;
/
