CREATE DATABASE IF NOT EXISTS authorsdb;
CREATE TABLE IF NOT EXISTS authorsdb.author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  age int(11) NOT NULL,
  genre varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE DATABASE IF NOT EXISTS booksdb;
CREATE TABLE IF NOT EXISTS booksdb.book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  isbn varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,  
  PRIMARY KEY (id)
);