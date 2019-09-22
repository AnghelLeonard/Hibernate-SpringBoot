insert into author (age, name, genre, id) values (23, "Mark Janel", "Anthology", 1) ^;
insert into author (age, name, genre, id) values (43, "Olivia Goy", "Horror", 2) ^;
insert into author (age, name, genre, id) values (51, "Quartis Young", "Anthology", 3) ^;
insert into author (age, name, genre, id) values (34, "Joana Nimar", "History", 4) ^;
insert into author (age, name, genre, id) values (47, "Kakki Jou", "Anthology", 5) ^;
insert into author (age, name, genre, id) values (56, "Fair Pouille", "Anthology", 6) ^;

CREATE OR REPLACE VIEW AUTHOR_ANTHOLOGY_VIEW
AS 
SELECT    
    a.id,
    a.name,
    a.age,
    a.genre
FROM
    author a 
WHERE a.genre = "Anthology" ^;