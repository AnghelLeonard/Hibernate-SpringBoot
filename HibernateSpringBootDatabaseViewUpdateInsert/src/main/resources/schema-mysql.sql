DROP TABLE IF EXISTS author ^; 

CREATE TABLE author (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  age int(11) NOT NULL,
  genre varchar(255) DEFAULT NULL,
  promotion_flag varchar(255) DEFAULT "Low",
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ^;