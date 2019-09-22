insert into author (age, name, genre, royalties, sellrank, rating, id) values (23, "Mark Janel", "Anthology", 1200, 289, 3, 1) ^;
insert into author (age, name, genre, royalties, sellrank, rating, id) values (43, "Olivia Goy", "Horror", 4000, 490, 5, 2) ^;
insert into author (age, name, genre, royalties, sellrank, rating, id) values (51, "Quartis Young", "Anthology", 900, 122, 4, 3) ^;
insert into author (age, name, genre, royalties, sellrank, rating, id) values (34, "Joana Nimar", "History", 5600, 554, 4, 4) ^;
insert into author (age, name, genre, royalties, sellrank, rating, id) values (47, "Kakki Jou", "Anthology", 1000, 231, 5, 5) ^;
insert into author (age, name, genre, royalties, sellrank, rating, id) values (56, "Fair Pouille", "Anthology", 3400, 344, 5, 6) ^;

CREATE OR REPLACE VIEW AUTHOR_ANTHOLOGY_VIEW
AS 
SELECT    
    a.id,
    a.name,
    a.age,
    a.genre    
FROM
    author a 
WHERE a.genre = "Anthology" WITH CHECK OPTION ^; 