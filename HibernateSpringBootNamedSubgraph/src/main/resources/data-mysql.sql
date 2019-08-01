insert into author (age, name, genre, id) values (23, "Mark Janel", "Anthology", 1); 
insert into author (age, name, genre, id) values (43, "Olivia Goy", "Horror", 2);
insert into author (age, name, genre, id) values (51, "Quartis Young", "Anthology", 3);
insert into author (age, name, genre, id) values (34, "Joana Nimar", "History", 4);
insert into author (age, name, genre, id) values (38, "Alicia Tom", "Anthology", 5);
insert into author (age, name, genre, id) values (56, "Katy Loin", "Anthology", 6);
insert into author (age, name, genre, id) values (23, "Wuth Troll", "Anthology", 7);

insert into publisher (company, id) values ("AnthologyPublisher", 1);
insert into publisher (company, id) values ("HorrorPublisher", 2);
insert into publisher (company, id) values ("HistoryPublisher", 3);

insert into book (isbn, title, author_id, publisher_id, id) values ("001-JN", "A History of Ancient Prague", 4, 3, 1);
insert into book (isbn, title, author_id, publisher_id, id) values ("002-JN", "A People's History", 4, 3, 2);
insert into book (isbn, title, author_id, publisher_id, id) values ("003-JN", "History Day", 4, 3, 3);
insert into book (isbn, title, author_id, publisher_id, id) values ("001-MJ", "The Beatles Anthology", 1, 1, 4);
insert into book (isbn, title, author_id, publisher_id, id) values ("001-OG", "Carrie", 2, 2, 5);
insert into book (isbn, title, author_id, publisher_id, id) values ("002-OG", "House Of Pain", 2, 2, 6);
insert into book (isbn, title, author_id, publisher_id, id) values ("001-AT", "Anthology 2000", 5, 1, 7);
