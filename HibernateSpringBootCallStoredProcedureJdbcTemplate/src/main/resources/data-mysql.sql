INSERT INTO author (age, name, genre, id) VALUES (23, "Mark Janel", "Anthology", 1) ^;
INSERT INTO author (age, name, genre, id) VALUES (43, "Olivia Goy", "Horror", 2) ^;
INSERT INTO author (age, name, genre, id) VALUES (51, "Quartis Young", "Anthology", 3) ^;
INSERT INTO author (age, name, genre, id) VALUES (34, "Joana Nimar", "History", 4) ^;
INSERT INTO author (age, name, genre, id) VALUES (38, "Alicia Tom", "Anthology", 5) ^;
INSERT INTO author (age, name, genre, id) VALUES (56, "Katy Loin", "Anthology", 6) ^;

DROP PROCEDURE IF EXISTS FETCH_AUTHOR_BY_GENRE ^; 

CREATE DEFINER=root@localhost PROCEDURE FETCH_AUTHOR_BY_GENRE(IN p_genre CHAR(20))
BEGIN  
  SELECT * FROM AUTHOR WHERE GENRE = p_genre;    
END ^;
