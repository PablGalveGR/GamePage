CREATE TABLE USERS(
   id int PRIMARY KEY AUTO_INCREMENT(4, 1),
   name varchar(30) NOT NULL unique,
   passwd varchar(150) NOT NULL
);
CREATE TABLE GAME(
   id int auto_increment PRIMARY KEY,
   name varchar(30) NOT NUll, 
   path varchar(150) unique,
   description varchar(1500),
   portrait varchar(50)
);
CREATE TABLE SCORE(
   id int auto_increment PRIMARY KEY,
   game int NOT NULL,
   username int NOT NULL,
   score int,
   FOREIGN KEY(game) REFERENCES GAME(id),
   FOREIGN KEY(username) REFERENCES USERS(id)
);
CREATE TABLE COMMENT(
   id int auto_increment PRIMARY KEY,
   game int NOT NUll,
   username int NOT NULL,
   comment text,
   date datetime
);
CREATE TABLE TAG(
   id int auto_increment PRIMARY KEY,
   tag varchar(10)
)