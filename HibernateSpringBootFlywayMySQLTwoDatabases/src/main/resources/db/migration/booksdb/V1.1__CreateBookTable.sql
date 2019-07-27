CREATE TABLE book (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  isbn varchar(255) DEFAULT NULL,
  title varchar(255) DEFAULT NULL,  
  authors varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);