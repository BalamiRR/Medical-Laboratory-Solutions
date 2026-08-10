DROP DATABASE IF EXISTS patient;
CREATE database patient;
USE patient;

DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
id INTEGER NOT NULL AUTO_INCREMENT,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
birth_date DATE NOT NULL,
gender VARCHAR(100) NOT NULL,
address VARCHAR(100),
phone_number VARCHAR(12),
PRIMARY KEY (id)
);

INSERT INTO patient(id, first_name, last_name, birth_date, gender, address, phone_number)
VALUES
("1", "John", "Walker", "1966-12-31", "F", "1 Brookside St ", "100-222-3333"),
("2", "Jack", "Jones", "1945-06-24", "M", "2 High St ", "200-333-4444"),
("3", "Thomas", "Anderson", "2004-06-18", "M", "3 Club Road ", "300-444-5555"),
("4", "Juliette", "Anderson", "2002-06-28", "F", "4 Valley Dr", "400-555-6666"),
("5", "Alison", "Wood", "1990-05-05", "M", "1 Club Road test ", "300-444-1111"),
("6", "Alexander", "Test2", "1980-06-08", "F", "2 Club Road test ", "300-444-2222"),
("7", "Harry", "Potter", "2000-07-28", "F", "3 Club Road test", "300-444-3333");

SELECT * FROM patient;
