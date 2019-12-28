insert into publisher (company, id) values ("AnthologyPublisher", 1);
insert into publisher (company, id) values ("HorrorPublisher", 2);
insert into publisher (company, id) values ("HistoryPublisher", 3);

insert into author (age, name, genre, publisher_id, id) values (23, "Mark Janel", "Anthology", 1, 1); 
insert into author (age, name, genre, publisher_id, id) values (43, "Olivia Goy", "Horror", 2, 2);
insert into author (age, name, genre, publisher_id, id) values (51, "Quartis Young", "Anthology", 1, 3);
insert into author (age, name, genre, publisher_id, id) values (34, "Joana Nimar", "History", 3, 4);
insert into author (age, name, genre, publisher_id, id) values (38, "Alicia Tom", "Anthology", 1, 5);
insert into author (age, name, genre, publisher_id, id) values (56, "Katy Loin", "Horror", 1, 6);
insert into author (age, name, genre, publisher_id, id) values (23, "Wuth Troll", "Anthology", 1, 7);

insert into book (isbn, title, price, author_id, id) values ("001-JN", "A History of Ancient Prague", 34, 4, 1);
insert into book (isbn, title, price, author_id, id) values ("002-JN", "A People's History", 36, 4, 2);
insert into book (isbn, title, price, author_id, id) values ("003-JN", "History Day", 25, 4, 3);
insert into book (isbn, title, price, author_id, id) values ("001-MJ", "The Beatles Anthology", 20, 1, 4);
insert into book (isbn, title, price, author_id, id) values ("001-OG", "Carrie", 44, 2, 5);
insert into book (isbn, title, price, author_id, id) values ("001-KL", "House Of Pain", 41, 6, 6);
insert into book (isbn, title, price, author_id, id) values ("001-AT", "Anthology 2000", 16, 5, 7);
