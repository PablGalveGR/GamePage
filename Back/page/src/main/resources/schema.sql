CREATE TABLE USERS(
   id int auto_increment PRIMARY KEY,
   name varchar(30),
   email varchar(150)
);
CREATE TABLE GAME(
   id int auto_increment PRIMARY KEY,
   name varchar(30),
   path varchar(150) unique,
   description varchar(1500),
   portrait varchar(50) unique
);
CREATE TABLE SCORE(
   id int auto_increment PRIMARY KEY,
   game int NOT NULL,
   username int NOT NULL,
   score int,
   FOREIGN KEY(game) REFERENCES GAME(id),
   FOREIGN KEY(username) REFERENCES USERS(id)
);