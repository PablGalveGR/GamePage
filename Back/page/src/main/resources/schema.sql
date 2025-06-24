CREATE TABLE USERS(
   id int auto_increment PRIMARY KEY,
   name varchar(10),
   email varchar(150)
);
CREATE TABLE GAME(
   id int auto_increment PRIMARY KEY,
   name varchar(10),
   path varchar(50) unique,
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