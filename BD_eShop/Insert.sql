--BIDONNAGE DE LA BD DU SITE E-COMMERCE

BEGIN
	INSERT INTO CUSTOMERS VALUES ('oce', 'oce');
	INSERT INTO CUSTOMERS VALUES ('je', 'je');


	INSERT INTO PRODUITS VALUES (1, 'carte', 'DescCarte', 7, 10, 0);
	INSERT INTO PRODUITS VALUES (2, 'peluche', 'DescPeluche', 20, 5, 0);
	INSERT INTO PRODUITS VALUES (3, 'boussole', 'DescBoussole', 5, 3, 0);
	INSERT INTO PRODUITS VALUES (4, 'container', 'DescContainer', 20, 9, 0);

	INSERT INTO LANGUES VALUES ('Français');
	INSERT INTO LANGUES VALUES ('Anglais');
	
	COMMIT;
END;
/
