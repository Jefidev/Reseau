CREATE OR REPLACE TRIGGER SEQUENCESTATDESCR
BEFORE INSERT ON RESULTATSSTATDESCR
FOR EACH ROW
BEGIN
	SELECT SEQUENCESTATDESCR.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/



CREATE OR REPLACE TRIGGER SEQUENCETESTCONF
BEFORE INSERT ON RESULTATSTESTCONF
FOR EACH ROW
BEGIN
	SELECT SEQUENCETESTCONF.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/



CREATE OR REPLACE TRIGGER SEQUENCETESTHOMOG
BEFORE INSERT ON RESULTATSTESTHOMOG
FOR EACH ROW
BEGIN
	SELECT SEQUENCETESTHOMOG.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/



CREATE OR REPLACE TRIGGER SEQUENCETESTANOVA
BEFORE INSERT ON RESULTATSTESTANOVA
FOR EACH ROW
BEGIN
	SELECT SEQUENCETESTANOVA.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/
