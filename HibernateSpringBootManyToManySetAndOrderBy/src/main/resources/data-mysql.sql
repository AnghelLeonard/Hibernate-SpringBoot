-- insert authors
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (23, "Mark Janel", "Anthology", 1);
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (51, "Quartis Young", "Anthology", 2);
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (38, "Alicia Tom", "Anthology", 3);
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (56, "Katy Loin", "Anthology", 4);
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (38, "Martin Leon", "Anthology", 5);
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (56, "Qart Pinkil", "Anthology", 6);

-- insert books
INSERT INTO book (`isbn`, `title`, `id`) VALUES ("001-all", "Encyclopedia", 1);

-- insert in the junction table
INSERT INTO author_book (`author_id`, `book_id`) VALUES (1, 1);
INSERT INTO author_book (`author_id`, `book_id`) VALUES (2, 1);
INSERT INTO author_book (`author_id`, `book_id`) VALUES (3, 1);
INSERT INTO author_book (`author_id`, `book_id`) VALUES (4, 1);
INSERT INTO author_book (`author_id`, `book_id`) VALUES (5, 1);
INSERT INTO author_book (`author_id`, `book_id`) VALUES (6, 1);
