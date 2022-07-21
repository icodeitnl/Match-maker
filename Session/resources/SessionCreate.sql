drop schema if exists  Session;
CREATE SCHEMA Session;
USE Session;

-- create tabellen
drop table if exists TechnicheOndersteuning;
drop table if exists Deelname;
drop table if exists OpenSession;
drop table if exists GeslotenSession ;
drop table if exists Session;
drop table if exists Muzikant;
drop table if exists Technicus;
--
-- Tabel structuur voor tabel Muzikant
--
CREATE TABLE Muzikant (
  muzikantId INT NOT NULL AUTO_INCREMENT,
  artistenaam VARCHAR(45) NOT NULL,
  ervaring INT NOT NULL,
  instrument VARCHAR(45) NOT NULL,
  PRIMARY KEY (`muzikantId`)
);
--
-- Tabel structuur voor tabel Session
--
CREATE TABLE Session (
  sessionId INT NOT NULL,
  organisator INT NOT NULL,
  datum DATE NOT NULL,
  opgenomen VARCHAR (45) NOT NULL,
  duur DECIMAL (4,2) NOT NULL,
  PRIMARY KEY (`sessionId`),
  KEY `organiseert` (`organisator`),
  CONSTRAINT `organiseert` FOREIGN KEY (`organisator`) REFERENCES `Muzikant` (`muzikantId`) ON DELETE RESTRICT ON UPDATE CASCADE

);
--
-- Tabel structuur voor tabel OpenSession
--
CREATE TABLE OpenSession (
  sessionId INT,
  maximumAantalMuzikanten INT NOT NULL,
  aantalLuisteraars INT,
  PRIMARY KEY (`sessionId`),
  KEY `is een soort` (`sessionId`),
  CONSTRAINT `is een soort` FOREIGN KEY (`sessionId`) REFERENCES `Session` (`sessionId`) ON DELETE RESTRICT ON UPDATE CASCADE
);
--
-- Tabel structuur voor tabel GeslotenSession
--
CREATE TABLE GeslotenSession (
  sessionId INT,
  aantalMuzikanten INT NOT NULL,
  PRIMARY KEY (`sessionId`),
  KEY `is ook een type` (`sessionId`),
  CONSTRAINT `is ook een type` FOREIGN KEY (`sessionId`) REFERENCES `Session` (`sessionId`) ON DELETE NO ACTION ON UPDATE NO ACTION  
);
--
-- Tabel structuur voor tabel Deelname
-- 
CREATE TABLE Deelname (
  sessionId INT NOT NULL,
  muzikantId INT NOT NULL,
  aantalMinuten INT NOT NULL,
  PRIMARY KEY (`muzikantId`,`sessionId`),
  KEY `neemt deel aan` (`muzikantId`),
  KEY `met deelname van` (`sessionId`),
  CONSTRAINT `met deelname van` FOREIGN KEY (`sessionId`) REFERENCES `OpenSession` (`sessionId`) ON DELETE cascade,
  CONSTRAINT `neemt deel aan` FOREIGN KEY (`muzikantId`) REFERENCES `Muzikant` (`muzikantId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;
--
-- Tabel structuur voor tabel Technicus
--
CREATE TABLE Technicus (
  technicusId INT NOT NULL,
  emailadres VARCHAR(45) NOT NULL,
  mobielnummer INT NOT NULL,
  naam VARCHAR (45) NOT NULL,
  PRIMARY KEY (`technicusId`)
);
--
-- Tabel structuur voor tabel TechnicheOndersteuning
--
CREATE TABLE TechnicheOndersteuning (
  sessionId INT NOT NULL,
  technicusId INT NOT NULL,
  rolTechnicus VARCHAR (45) NOT NULL,
  PRIMARY KEY (`technicusId`,`sessionId`),
  KEY `ondersteund door` (`sessionId`),
  KEY `word gedaan door` (`technicusId`),
  CONSTRAINT `word gedaan door` FOREIGN KEY (`technicusId`) REFERENCES `Technicus` (`technicusId`),
  CONSTRAINT `ondersteund door` FOREIGN KEY (`sessionId`) REFERENCES `Session` (`sessionId`) ON DELETE cascade
);

CREATE USER 'userSessionService'@'localhost' IDENTIFIED BY 'pwSessionService';
GRANT ALL PRIVILEGES ON Session . * TO 'userSessionService'@'localhost';