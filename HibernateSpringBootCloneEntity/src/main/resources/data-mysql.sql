-- insert authors
INSERT INTO author (`age`, `name`, `genre`, `id`) VALUES (23, "Mark Janel", "Anthology", 1);

-- insert books
INSERT INTO book (`isbn`, `title`, `id`) VALUES ("001", "My Anthology", 1);
INSERT INTO book (`isbn`, `title`, `id`) VALUES ("002", "999 Anthology", 2);

-- insert in the junction table
INSERT INTO author_book (`author_id`, `book_id`) VALUES (1, 1);
INSERT INTO author_book (`author_id`, `book_id`) VALUES (1, 2);
