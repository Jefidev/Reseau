-- BIDONNAGE DES DONNEES DE BASE


BEGIN
	
	-- INSERTION UTILISATEUR
	INSERT INTO UTILISATEURS VALUES('jerome', 'fink');
	INSERT INTO UTILISATEURS VALUES('oceane', 'seel');
	INSERT INTO UTILISATEURS VALUES('serveur','serveur');

	-- INSERTION DES DESTINATIONS
	INSERT INTO DESTINATIONS (VILLE, DISTANCE_BATEAU, DISTANCE_TRAIN, DISTANCE_ROUTE) VALUES ('Seraing', 5, 10, 30);
	INSERT INTO DESTINATIONS (VILLE, DISTANCE_BATEAU, DISTANCE_TRAIN, DISTANCE_ROUTE) VALUES ('Verviers', 40, 25, 30);
	INSERT INTO DESTINATIONS (VILLE, DISTANCE_BATEAU, DISTANCE_TRAIN, DISTANCE_ROUTE) VALUES ('Herstal', 10, 5, 15);
	INSERT INTO DESTINATIONS (VILLE, DISTANCE_BATEAU, DISTANCE_TRAIN, DISTANCE_ROUTE) VALUES ('Visé', 15, 20, 25);
	INSERT INTO DESTINATIONS (VILLE, DISTANCE_BATEAU, DISTANCE_TRAIN, DISTANCE_ROUTE) VALUES ('Huy', 25, 30, 40);

	-- INSERTION SOCIETE
	INSERT INTO SOCIETES VALUES('TNT', 'Leon Dubois', NULL, NULL, NULL);
	INSERT INTO SOCIETES VALUES('UPS', 'Jean Dubois', NULL, NULL, NULL);
	INSERT INTO SOCIETES VALUES('DHL', 'Kevin Dubois', NULL, NULL, NULL);

	--INSERTION CONTAINER

	INSERT INTO CONTAINERS VALUES('T554', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T555', 'TNT', NULL, NULL, 'R1');
	INSERT INTO CONTAINERS VALUES('T556', 'TNT', NULL, NULL, 'R1');
	INSERT INTO CONTAINERS VALUES('T557', 'TNT', NULL, NULL, 'R1');
	INSERT INTO CONTAINERS VALUES('T558', 'TNT', NULL, NULL, 'R1');
	INSERT INTO CONTAINERS VALUES('T559', 'TNT', NULL, NULL, 'R2');
	INSERT INTO CONTAINERS VALUES('T560', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T561', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T562', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T563', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T564', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T565', 'TNT', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('T566', 'TNT', NULL, NULL, NULL);

	INSERT INTO CONTAINERS VALUES('D554', 'DHL', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('D555', 'DHL', NULL, NULL, 'R3');
	INSERT INTO CONTAINERS VALUES('D556', 'DHL', NULL, NULL, 'R3');
	INSERT INTO CONTAINERS VALUES('D557', 'DHL', NULL, NULL, 'R3');
	INSERT INTO CONTAINERS VALUES('D558', 'DHL', NULL, NULL, 'R3');
	INSERT INTO CONTAINERS VALUES('D559', 'DHL', NULL, NULL, 'R3');
	INSERT INTO CONTAINERS VALUES('D560', 'DHL', NULL, NULL, 'R3');
	INSERT INTO CONTAINERS VALUES('D561', 'DHL', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('D562', 'DHL', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('D563', 'DHL', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('D564', 'DHL', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('D565', 'DHL', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('D566', 'DHL', NULL, NULL, NULL);

	INSERT INTO CONTAINERS VALUES('U554', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U555', 'UPS', NULL, NULL, 'R4');
	INSERT INTO CONTAINERS VALUES('U556', 'UPS', NULL, NULL, 'R4');
	INSERT INTO CONTAINERS VALUES('U557', 'UPS', NULL, NULL, 'R4');
	INSERT INTO CONTAINERS VALUES('U558', 'UPS', NULL, NULL, 'R4');
	INSERT INTO CONTAINERS VALUES('U559', 'UPS', NULL, NULL, 'R4');
	INSERT INTO CONTAINERS VALUES('U560', 'UPS', NULL, NULL, 'R4');
	INSERT INTO CONTAINERS VALUES('U561', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U562', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U563', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U564', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U565', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U566', 'UPS', NULL, NULL, NULL);
	INSERT INTO CONTAINERS VALUES('U567', 'UPS', NULL, NULL, NULL);

	-- INSERTION DANS PARC
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (1, 1, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (2, 3, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (4, 4, 'T559', 1, '11/10/2015', '15/09/2015', 100, 'Seraing', 'BATEAU');
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (5, 6, 'D554', 2, '5/10/2015', '9/10/2015', 200, 'Verviers', 'TRAIN');
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (7, 7, NULL, 2, '8/10/2015', '10/10/2015', 300, 'Seraing', 'TRAIN');
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (8, 8, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (9, 9, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (10, 10, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (11, 11, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (12, 12, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (21, 31, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (14, 14, NULL, 0, NULL, NULL, NULL, NULL, NULL);
	INSERT INTO PARC (X, Y, ID_CONTAINER, ETAT, DATE_RESERVATION, DATE_ARRIVEE, POIDS, DESTINATION, TRANSPORT) VALUES (26, 37, NULL, 0, NULL, NULL, NULL, NULL, NULL);

	-- INSERTION TRANSPORTEURS
	INSERT INTO TRANSPORTEURS VALUES('T1', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T2', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T3', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T4', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T5', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T6', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T7', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T8', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T9', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T10', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T11', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T12', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T13', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T14', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T15', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T16', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T17', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T18', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T19', 'TNT', NULL);
	INSERT INTO TRANSPORTEURS VALUES('T20', 'TNT', NULL);

	--INSERTION MOUVEMENTS
	INSERT INTO MOUVEMENTS VALUES('M1', 'T554', 'T1', '19/09/2015', 'T2', 25, '25/09/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M2', 'T555', 'T3', '01/09/2015', 'T4', 12, '17/09/2015', 'Huy');
	INSERT INTO MOUVEMENTS VALUES('M3', 'T556', 'T5', '02/09/2015', 'T6', 42, '16/09/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M4', 'T557', 'T7', '05/09/2015', 'T8', 5, '01/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M5', 'T558', 'T9', '19/09/2015', 'T10', 100, '21/09/2015', 'Huy');
	INSERT INTO MOUVEMENTS VALUES('M6', 'T559', 'T11', '20/09/2015', 'T12', 75, '05/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M7', 'T560', 'T14', '14/09/2015', 'T13', 38, '18/09/2015', 'Verviers');
	INSERT INTO MOUVEMENTS VALUES('M8', 'T561', 'T15', '28/09/2015', 'T16', 49, '25/10/2015', 'Herstal');
	INSERT INTO MOUVEMENTS VALUES('M9', 'T562', 'T18', '15/09/2015', 'T17', 84, '16/09/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M10','T563', 'T19', '03/09/2015', 'T20', 36, '14/09/2015', 'Seraing');

	INSERT INTO MOUVEMENTS VALUES('M11', 'D554', 'T1', '28/09/2015', 'T2', 98, '05/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M12', 'D555', 'T1', '29/09/2015', 'T2', 17, '08/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M13', 'D556', 'T1', '01/10/2015', 'T2', 83, '12/10/2015', 'Huy');
	INSERT INTO MOUVEMENTS VALUES('M14', 'D557', 'T1', '05/09/2015', 'T2', 26, '01/10/2015', 'Verviers');
	INSERT INTO MOUVEMENTS VALUES('M15', 'D558', 'T1', '11/10/2015', 'T2', 29, '13/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M16', 'D559', 'T1', '15/10/2015', 'T2', 48, '25/10/2015', 'Herstal');
	INSERT INTO MOUVEMENTS VALUES('M17', 'D560', 'T1', '21/08/2015', 'T2', 79, '22/09/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M18', 'D561', 'T1', '20/10/2015', 'T2', 36, '26/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M19', 'D562', 'T1', '21/09/2015', 'T2', 79, '25/09/2015', 'Verviers');
	INSERT INTO MOUVEMENTS VALUES('M20', 'D563', 'T1', '02/10/2015', 'T2', 57, '16/10/2015', 'Seraing');

	INSERT INTO MOUVEMENTS VALUES('M40', 'U554', 'T1', '06/10/2015', 'T2', 67, '11/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M21', 'U555', 'T1', '03/11/2015', 'T2', 14, '26/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M22', 'U556', 'T1', '02/11/2015', 'T2', 57, '06/11/2015', 'Huy');
	INSERT INTO MOUVEMENTS VALUES('M23', 'U557', 'T1', '19/10/2015', 'T2', 34, '21/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M24', 'U558', 'T1', '16/10/2015', 'T2', 69, '02/11/2015', 'Verviers');
	INSERT INTO MOUVEMENTS VALUES('M25', 'U559', 'T1', '19/11/2015', 'T2', 20, NULL, 'Verviers');
	INSERT INTO MOUVEMENTS VALUES('M26', 'U560', 'T1', '16/10/2015', 'T2', 34, '27/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M27', 'U561', 'T1', '19/10/2015', 'T2', 68, '28/10/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M28', 'U562', 'T1', '19/11/2015', 'T2', 61, NULL, 'Huy');
	INSERT INTO MOUVEMENTS VALUES('M29', 'U563', 'T1', '09/09/2015', 'T2', 48, '21/10/2015', 'Seraing');

	INSERT INTO MOUVEMENTS VALUES('M30', 'T564', 'T1', '12/11/2015', 'T2', 18, NULL, 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M31', 'D564', 'T1', '10/11/2015', 'T2', 36, NULL, 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M32', 'U564', 'T1', '15/11/2015', 'T2', 48, NULL, 'Huy');
	INSERT INTO MOUVEMENTS VALUES('M33', 'T565', 'T1', '28/10/2015', 'T2', 69, NULL, 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M34', 'T566', 'T1', '16/11/2015', 'T2', 72, NULL, 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M35', 'D565', 'T1', '29/10/2015', 'T2', 48, '11/11/2015', 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M36', 'D566', 'T1', '14/11/2015', 'T2', 92, NULL, 'Herstal');
	INSERT INTO MOUVEMENTS VALUES('M37', 'U565', 'T1', '18/11/2015', 'T2', 81, NULL, 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M38', 'U566', 'T1', '19/11/2015', 'T2', 12, NULL, 'Seraing');
	INSERT INTO MOUVEMENTS VALUES('M39', 'U567', 'T1', '19/11/2015', 'T2', 40, NULL, 'Verviers');

	COMMIT;
END;
/
