INSERT INTO author (age, name, genre, nickname, id) VALUES (23, "Mark Janel", "Anthology", "MJ", 1) ^;
INSERT INTO author (age, name, genre, nickname, id) VALUES (43, "Olivia Goy", "Horror", "OG", 2) ^;
INSERT INTO author (age, name, genre, nickname, id) VALUES (51, "Quartis Young", "Anthology", "QY", 3) ^;
INSERT INTO author (age, name, genre, nickname, id) VALUES (34, "Joana Nimar", "History", "JN", 4) ^;
INSERT INTO author (age, name, genre, nickname, id) VALUES (38, "Alicia Tom", "Anthology", "AT", 5) ^;
INSERT INTO author (age, name, genre, nickname, id) VALUES (56, "Katy Loin", "Anthology", "KL", 6) ^;

DROP PROCEDURE IF EXISTS FETCH_AUTHOR_BY_GENRE ^; 
DROP PROCEDURE IF EXISTS FETCH_NICKNAME_AND_AGE_BY_GENRE ^; 

CREATE DEFINER=root@localhost PROCEDURE FETCH_AUTHOR_BY_GENRE(IN p_genre CHAR(20))
BEGIN  
  SELECT * FROM AUTHOR WHERE GENRE = p_genre;    
END ^;

CREATE DEFINER=root@localhost PROCEDURE FETCH_NICKNAME_AND_AGE_BY_GENRE(IN p_genre CHAR(20))
BEGIN  
  SELECT NICKNAME, AGE FROM AUTHOR WHERE GENRE = p_genre;    
END ^;