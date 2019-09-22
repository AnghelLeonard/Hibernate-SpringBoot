DROP TABLE IF EXISTS author ^; 

CREATE TABLE author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  age int(11) NOT NULL,
  genre varchar(255) NOT NULL,  
  name varchar(255) NOT NULL,
  sellrank int(11) NOT NULL DEFAULT -1,
  royalties int(11) NOT NULL DEFAULT -1,
  rating int(11) NOT NULL DEFAULT -1,
  PRIMARY KEY (id)
) ^;