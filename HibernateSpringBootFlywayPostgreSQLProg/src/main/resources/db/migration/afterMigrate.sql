delete from book;
delete from author;
insert into author (age, name, genre, id) values (23, 'Mark Janel', 'Anthology', 10);
insert into author (age, name, genre, id) values (43, 'Olivia Goy', 'Horror', 11);
insert into author (age, name, genre, id) values (51, 'Quartis Young', 'Anthology', 12);
insert into author (age, name, genre, id) values (34, 'Joana Nimar', 'History', 13);
insert into book (isbn, title, author_id, id) values ('001-JN', 'A History of Ancient Prague', 13, 10);
insert into book (isbn, title, author_id, id) values ('002-JN', 'A People''s History', 13, 11);
insert into book (isbn, title, author_id, id) values ('001-MJ', 'The Beatles Antholog', 10, 12);
insert into book (isbn, title, author_id, id) values ('001-OG', 'Carrie', 11, 13);

