insert into author (age, name, genre, promotion_flag, id) values (23, "Mark Janel", "Anthology", "Low", 1) ^;
insert into author (age, name, genre, promotion_flag, id) values (43, "Olivia Goy", "Horror", "High", 2) ^;
insert into author (age, name, genre, promotion_flag, id) values (51, "Quartis Young", "Anthology", "Low", 3) ^;
insert into author (age, name, genre, promotion_flag, id) values (34, "Joana Nimar", "History", "Medium", 4) ^;

CREATE OR REPLACE VIEW AUTHOR_GENRE_PROMO_VIEW
AS 
SELECT 
    a.id,
    a.genre,
    a.promotion_flag   
FROM
    author a ^;

CREATE OR REPLACE VIEW AUTHOR_NAME_AGE_GENRE_VIEW
AS 
SELECT    
    a.name,
    a.age,
    a.genre
FROM
    author a ^;