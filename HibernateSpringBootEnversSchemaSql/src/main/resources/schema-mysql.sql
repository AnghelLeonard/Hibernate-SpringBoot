-- Drop
DROP TABLE IF EXISTS bookstoredb.book;
DROP TABLE IF EXISTS bookstoredb.author;
DROP TABLE IF EXISTS bookstoredb.author_audit;
DROP TABLE IF EXISTS bookstoredb.book_audit;
DROP TABLE IF EXISTS bookstoredb.revinfo;

-- Create
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
  KEY FKklnrv3weler2ftkweewlky958 (author_id)
);

CREATE TABLE author_audit (
  id bigint(20) NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  revend int(11) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  genre varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id,rev),
  KEY FKp4vbplw134mimnk3nlxfvmch0 (rev),
  KEY FKdtg6l7ccqhpsdnkltcoisi9l9 (revend)
);

CREATE TABLE book_audit (
  id bigint(20) NOT NULL,
  rev int(11) NOT NULL,
  revtype tinyint(4) DEFAULT NULL,
  revend int(11) DEFAULT NULL,
  isbn varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,
  author_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id,rev),
  KEY FKjx5fxkthrd6kxbxb3ukwb04mf (rev),
  KEY FKr9ed64q1nek7vjfbcxm04v8ic (revend)
); 

CREATE TABLE revinfo (
  rev int(11) NOT NULL AUTO_INCREMENT,
  revtstmp bigint(20) DEFAULT NULL,
  PRIMARY KEY (rev)
);