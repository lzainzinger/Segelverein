-- Create Skript zum Erstellen der Tabellen für die Datenbank: segelverein
-- Autor: Lukas Zainzinger
-- Version: 2015-03-12

CREATE TABLE person (
  key int PRIMARY KEY NOT NULL,
  name varchar(255) NOT NULL,
  geburtsdatum DATE
);

CREATE TABLE segler (
  key int PRIMARY KEY REFERENCES person
);

CREATE TABLE trainer (
  key int PRIMARY KEY REFERENCES person
);

CREATE TABLE boot (
  id int PRIMARY KEY,
  name varchar(255) NOT NULL,
  personen int NOT NULL,
  tiefgang int NOT NULL
);

CREATE TABLE tourenboot (
  id int PRIMARY KEY REFERENCES boot,
  bootsklasse varchar(255) NOT NULL
);

CREATE TABLE sportboot (
  id int PRIMARY KEY REFERENCES boot,
  segelflaeche int NOT NULL
);

CREATE TABLE mannschaft (
  name varchar(255)  PRIMARY KEY NOT NULL,	
  key int  REFERENCES trainer(key),
  aklasse int NOT NULL
);

CREATE TABLE regatta (
  name varchar(255) NOT NULL,
  jahr varchar(4) NOT NULL,
  land varchar(100) NOT NULL,
  PRIMARY KEY (name, jahr)
);

CREATE TABLE wettfahrt (
  wname varchar(255),
  wjahr varchar(4),
  datum date NOT NULL,
  laenge int NOT NULL,
  FOREIGN KEY (wname, wjahr) REFERENCES regatta(name, jahr),
  PRIMARY KEY (wname, wjahr, datum)
);

CREATE TABLE bildet (
  bkey int REFERENCES segler(key),
  bname varchar(255) REFERENCES mannschaft(name), 
  PRIMARY KEY (bkey, bname)
);

CREATE TABLE zugewiesen (
  id int REFERENCES boot(id),
  zname varchar(255) REFERENCES mannschaft(name), 
  PRIMARY KEY(id, zname)
);

CREATE TABLE nimmtteil (
  mname varchar(255) REFERENCES mannschaft(name), 
  rname varchar(255),
  rjahr varchar(4),
  sportboot int REFERENCES sportboot(id),
  startnr int,
  FOREIGN KEY (rname, rjahr) REFERENCES regatta(name, jahr),
  PRIMARY KEY(mname, rname, rjahr, sportboot)
);

CREATE TABLE erzielt (
  emname varchar(255) REFERENCES mannschaft(name),
  ewname varchar(255),
  ewjahr varchar(4),
  wdatum date,
  punkte int,
  FOREIGN KEY(ewname, wdatum, ewjahr) REFERENCES wettfahrt(wname, datum, wjahr),
  PRIMARY KEY(emname, ewname, ewjahr, wdatum)
);