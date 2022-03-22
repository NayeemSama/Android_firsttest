--
-- File generated with SQLiteStudio v3.3.3 on Fri Feb 11 15:45:36 2022
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: android_metadata
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO android_metadata (locale) VALUES ('en_US');

-- Table: Registered_User
CREATE TABLE Registered_User (Username VARCHAR (50) NOT NULL, Password INTEGER NOT NULL, UserID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Phone INTEGER NOT NULL, Gender INTEGER NOT NULL, Email VARCHAR (50) NOT NULL, Country VARCHAR (10) NOT NULL, Birthdate DATE NOT NULL, Birthtime TIME NOT NULL, Rating DOUBLE NOT NULL, Age INTEGER NOT NULL);
INSERT INTO Registered_User (Username, Password, UserID, Phone, Gender, Email, Country, Birthdate, Birthtime, Rating, Age) VALUES ('Ngg', 5326, 1, 1234567890, 1, 'ngg@ng.co', 'India', '01/06/93', '11:45', 1.5, 56);
INSERT INTO Registered_User (Username, Password, UserID, Phone, Gender, Email, Country, Birthdate, Birthtime, Rating, Age) VALUES ('Dfdf', 202022, 2, 2020202020, 2131231230, 'dff@dfs.co', 'China', '02/11/04', '13:10', 4.0, 20);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
