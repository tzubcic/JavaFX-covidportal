CREATE TABLE SIMPTOM(
 ID LONG GENERATED ALWAYS AS IDENTITY,
 NAZIV VARCHAR(25) NOT NULL,
 VRIJEDNOST VARCHAR(25) NOT NULL,
 PRIMARY KEY (ID) 
);
CREATE TABLE BOLEST(
 ID LONG GENERATED ALWAYS AS IDENTITY,
 NAZIV VARCHAR(25) NOT NULL,
 VIRUS BOOLEAN NOT NULL,
 PRIMARY KEY (ID)
); 

CREATE TABLE BOLEST_SIMPTOM(
 BOLEST_ID LONG NOT NULL,
 SIMPTOM_ID LONG NOT NULL,
 PRIMARY KEY (BOLEST_ID, SIMPTOM_ID),
 FOREIGN KEY (BOLEST_ID) REFERENCES BOLEST(ID),
 FOREIGN KEY (SIMPTOM_ID) REFERENCES SIMPTOM(ID)
); 

CREATE TABLE ZUPANIJA(
 ID LONG GENERATED ALWAYS AS IDENTITY,
 NAZIV VARCHAR(50) NOT NULL,
 BROJ_STANOVNIKA INT NOT NULL,
 BROJ_ZARAZENIH_STANOVNIKA INT NOT NULL,
 PRIMARY KEY (ID)
); 

CREATE TABLE OSOBA(
 ID LONG GENERATED ALWAYS AS IDENTITY,
 IME VARCHAR(25) NOT NULL,
 PREZIME VARCHAR(25) NOT NULL,
 DATUM_RODJENJA DATE NOT NULL,
 ZUPANIJA_ID LONG NOT NULL,
 BOLEST_ID LONG NOT NULL,
 PRIMARY KEY (ID),
 FOREIGN KEY (ZUPANIJA_ID) REFERENCES ZUPANIJA(ID),
 FOREIGN KEY (BOLEST_ID) REFERENCES BOLEST(ID)
); 

CREATE TABLE KONTAKTIRANE_OSOBE(
 OSOBA_ID LONG NOT NULL,
 KONTAKTIRANA_OSOBA_ID LONG NOT NULL,
 PRIMARY KEY (OSOBA_ID, KONTAKTIRANA_OSOBA_ID),
 FOREIGN KEY (OSOBA_ID) REFERENCES OSOBA(ID),
 FOREIGN KEY (KONTAKTIRANA_OSOBA_ID ) REFERENCES OSOBA(ID)
); 

INSERT INTO SIMPTOM(NAZIV, VRIJEDNOST) VALUES ('Kašalj',
'Produktivni');
INSERT INTO SIMPTOM(NAZIV, VRIJEDNOST) VALUES ('Curenje nosa',
'Intenzivno');
INSERT INTO SIMPTOM(NAZIV, VRIJEDNOST) VALUES ('Temperatura',
'Visoka');
INSERT INTO SIMPTOM(NAZIV, VRIJEDNOST) VALUES ('Grlobolja', 'Jaka');
INSERT INTO SIMPTOM(NAZIV, VRIJEDNOST) VALUES ('Glavobolja',
'Produktivni');

INSERT INTO BOLEST(NAZIV, VIRUS) VALUES ('Prehlada', 'TRUE');
INSERT INTO BOLEST(NAZIV, VIRUS) VALUES ('Gripa', 'TRUE');
INSERT INTO BOLEST(NAZIV, VIRUS) VALUES ('Covid-19', 'TRUE');
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (1, 1);
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (1, 2);
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (2, 3);
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (2, 4);
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (2, 5);
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (3, 3);
INSERT INTO BOLEST_SIMPTOM(BOLEST_ID, SIMPTOM_ID) VALUES (3, 5);

INSERT INTO ZUPANIJA(NAZIV, BROJ_STANOVNIKA,
BROJ_ZARAZENIH_STANOVNIKA) VALUES('Grad Zagreb', 1000000, 3242);
INSERT INTO ZUPANIJA(NAZIV, BROJ_STANOVNIKA,
BROJ_ZARAZENIH_STANOVNIKA) VALUES('Zagrebačka', 200000, 1232);
INSERT INTO ZUPANIJA(NAZIV, BROJ_STANOVNIKA,
BROJ_ZARAZENIH_STANOVNIKA) VALUES('Splitsko-Dalmatinska',
200000, 2324);
INSERT INTO ZUPANIJA(NAZIV, BROJ_STANOVNIKA,
BROJ_ZARAZENIH_STANOVNIKA) VALUES('Međimurska', 120000, 4323);
INSERT INTO ZUPANIJA(NAZIV, BROJ_STANOVNIKA,
BROJ_ZARAZENIH_STANOVNIKA) VALUES('Varaždinska', 140000, 3976);
INSERT INTO OSOBA(IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID,
BOLEST_ID) VALUES ('Perica', 'Perić', '1968-10-12', 1, 2);
INSERT INTO OSOBA(IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID,
BOLEST_ID) VALUES ('Marija', 'Horvat', '1962-05-02', 2, 1);
INSERT INTO OSOBA(IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID,
BOLEST_ID) VALUES ('Mladen', 'Barić', '1978-02-23', 3, 3);
INSERT INTO OSOBA(IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID,
BOLEST_ID) VALUES ('Ivana', 'Milić', '1975-03-15', 4, 2);
INSERT INTO OSOBA(IME, PREZIME, DATUM_RODJENJA, ZUPANIJA_ID,
BOLEST_ID) VALUES ('Marko', 'Kovačević', '1971-05-19', 5, 3);

INSERT INTO KONTAKTIRANE_OSOBE VALUES(1, 2);
INSERT INTO KONTAKTIRANE_OSOBE VALUES(1, 3);
INSERT INTO KONTAKTIRANE_OSOBE VALUES(1, 4);
INSERT INTO KONTAKTIRANE_OSOBE VALUES(2, 3);
INSERT INTO KONTAKTIRANE_OSOBE VALUES(2, 4);
INSERT INTO KONTAKTIRANE_OSOBE VALUES(3, 5);
INSERT INTO KONTAKTIRANE_OSOBE VALUES(4, 5); 


