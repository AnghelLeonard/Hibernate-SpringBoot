insert into author (age, name, genre, id) values (23, "Mark Janel", "Anthology", 1) ^;
insert into author (age, name, genre, id) values (43, "Olivia Goy", "Horror", 2) ^;
insert into author (age, name, genre, id) values (51, "Quartis Young", "Anthology", 3) ^;
insert into author (age, name, genre, id) values (34, "Joana Nimar", "History", 4) ^;

CREATE OR REPLACE VIEW NAME_AND_GENRE_VIEW
AS 
SELECT 
    a.id,   
    a.name, 
    a.genre    
FROM
    author a 
WHERE a.genre = 'Anthology' WITH CHECK OPTION ^;