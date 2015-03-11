-- Create Skript zum Erstellen der Tabellen für die Datenbank: segelverein
-- Autor: Lukas Zainzinger
-- Version: 2015-03-09

CREATE TABLE person (
  key int PRIMARY KEY NOT NULL,
  name varchar(255) NOT NULL,
  geburtsdatum varchar(255)
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
  name varchar(255)  NOT NULL,	
  key int  REFERENCES trainer(key),
  aklasse int NOT NULL,
  PRIMARY KEY (name, key)
);

CREATE TABLE regatta (
  name varchar(255) NOT NULL,
  jahr varchar(255) NOT NULL,
  land varchar(100) NOT NULL,
  PRIMARY KEY (name, jahr)
);

CREATE TABLE wettfahrt (
  wname varchar(255),
  wjahr varchar(255),
  datum date NOT NULL,
  laenge int NOT NULL,
  FOREIGN KEY (wname, wjahr) REFERENCES regatta(name, jahr),
  PRIMARY KEY (wname, wjahr, datum)
);

CREATE TABLE bildet (
  bkey int REFERENCES segler(key),
  bname int REFERENCES mannschaft(name), --GEHT NICHT :(
  PRIMARY KEY (bkey, bname)
);

CREATE TABLE zugewiesen (
  id int REFERENCES boot(id),
  zname int REFERENCES mannschaft(name), --Geht AUCH nicht :((
  PRIMARY KEY(id, zname)
);

CREATE TABLE nimmtteil (
  mname int PRIMARY KEY REFERENCES mannschaft(name), --Geht NICHT
  rname int PRIMARY KEY REFERENCES regatta(name),
  rjahr varchar(255) PRIMARY KEY REFERENCES regatta(jahr),
  sportboot int PRIMARY KEY REFERENCES sportboot(id),
  startnr int
);

CREATE TABLE erzielt (
  emname int PRIMARY KEY REFERENCES mannschaft(name), --Geht NICHT
  ewname int PRIMARY KEY REFERENCES wettfahrt(wname),
  ewjahr varchar(255) PRIMARY KEY REFERENCES regatta(jahr),
  wdatum date PRIMARY KEY REFERENCES wettfahrt(datum),
  punkte int
);