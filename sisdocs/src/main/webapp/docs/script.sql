DROP DATABASE IF EXISTS dbsisdocs;

CREATE database dbsisdocs CHARACTER SET utf8 COLLATE utf8_general_ci;

use dbsisdocs;

CREATE TABLE cliente(
	id int primary key auto_increment,
	nome varchar(100),
	login varchar(100) UNIQUE,
	senha varchar(100)
);

CREATE TABLE pasta(
	id int PRIMARY KEY auto_increment,
	nome varchar(100),
    clienteid INT,
	FOREIGN KEY (clienteid) REFERENCES cliente(id)
);

CREATE TABLE arquivo(
	id int PRIMARY KEY auto_increment,
	nome varchar(100),
	size varchar(100),
    pastaid INT,
	FOREIGN KEY (pastaid) REFERENCES pasta(id)
);

INSERT INTO cliente(id,nome,login,senha) VALUES (1,"Rodrigo","rrodrigoo1","teste");
INSERT INTO cliente(id,nome,login,senha) VALUES (2,"Rodrigo","rrodrigoo2","teste");
INSERT INTO cliente(id,nome,login,senha) VALUES (3,"Rodrigo","rrodrigoo3","teste");

INSERT INTO pasta(id,nome,clienteid) VALUES (1,"fotos",1);
INSERT INTO pasta(id,nome,clienteid) VALUES (2,"videos",1);
INSERT INTO pasta(id,nome,clienteid) VALUES (3,"meus-arquivos/cafet",2);
INSERT INTO pasta(id,nome,clienteid) VALUES (4,"rrodrigoo3",3);

INSERT INTO arquivo(id,nome,size,pastaid) VALUES (1,"WhatsApp Image 2022-11-05 at 12.46.36 (1)","10kb",1);
INSERT INTO arquivo(id,nome,size,pastaid) VALUES (2,"WhatsApp Image 2022-11-05 at 12.46.36","10kb",1);

INSERT INTO arquivo(id,nome,size,pastaid) VALUES (4,"Fluxograma.pdf","10kb",3);

INSERT INTO arquivo(id,nome,size,pastaid) VALUES (5,"Alt Codes _ Reference Sheet","10kb",4);
INSERT INTO arquivo(id,nome,size,pastaid) VALUES (6,"big.jfif","10kb",4);