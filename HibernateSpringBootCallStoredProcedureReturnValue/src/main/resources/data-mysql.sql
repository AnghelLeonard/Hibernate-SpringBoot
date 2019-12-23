insert into author (age, name, genre, id) values (23, "Mark Janel", "Anthology", 1) ^;
insert into author (age, name, genre, id) values (43, "Olivia Goy", "Horror", 2) ^;
insert into author (age, name, genre, id) values (51, "Quartis Young", "Anthology", 3) ^;
insert into author (age, name, genre, id) values (34, "Joana Nimar", "History", 4) ^;
insert into author (age, name, genre, id) values (38, "Alicia Tom", "Anthology", 5) ^;
insert into author (age, name, genre, id) values (56, "Katy Loin", "Anthology", 6) ^;

DROP PROCEDURE IF EXISTS COUNT_AUTHOR_BY_GENRE ^; 

CREATE DEFINER=root@localhost PROCEDURE COUNT_AUTHOR_BY_GENRE(IN p_genre CHAR(20), OUT p_count INT)
BEGIN  
  SELECT COUNT(*) INTO p_count FROM author WHERE genre = p_genre;    
END ^;