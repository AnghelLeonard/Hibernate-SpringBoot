-- insert authors
INSERT INTO `author` (`age`, `name`, `genre`, `version`, `id`) VALUES (23, "Mark Janel", "Anthology", 0, 1);
INSERT INTO `author` (`age`, `name`, `genre`, `version`, `id`) VALUES (43, "Olivia Goy", "Horror", 0, 2);
INSERT INTO `author` (`age`, `name`, `genre`, `version`, `id`) VALUES (51, "Quartis Young", "Anthology", 0, 3);
INSERT INTO `author` (`age`, `name`, `genre`, `version`, `id`) VALUES (34, "Joana Nimar", "History", 0, 4);

-- insert books
INSERT INTO `book` (`isbn`, `title`, `author_id`, `version`, `id`) VALUES ("001-JN", "A History of Ancient Prague", 4, 0, 1);
INSERT INTO `book` (`isbn`, `title`, `author_id`, `version`, `id`) VALUES ("002-JN", "A People's History", 4, 0, 2);
INSERT INTO `book` (`isbn`, `title`, `author_id`, `version`, `id`) VALUES ("001-MJ", "The Beatles Anthology", 1, 0, 3);
INSERT INTO `book` (`isbn`, `title`, `author_id`, `version`, `id`) VALUES ("001-OG", "Carrie", 2, 0, 4);

