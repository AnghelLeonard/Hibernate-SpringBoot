DROP TABLE IF EXISTS bookstoredb.book;
DROP TABLE IF EXISTS bookstoredb.author;

CREATE TABLE author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  age int(11) NOT NULL,
  genre varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  isbn varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  author_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKklnrv3weler2ftkweewlky958 (author_id),
  CONSTRAINT FKklnrv3weler2ftkweewlky958 FOREIGN KEY (author_id) REFERENCES author (id)
);