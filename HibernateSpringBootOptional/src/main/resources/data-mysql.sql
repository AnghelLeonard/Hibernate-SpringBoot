--fetchAuthorById(1) - Author name: Leonard Anghel
--fetchAuthorFromBook(Mastering JSF 2.2) - Author name: Leonard Anghel
INSERT INTO `author` (`id`, `age`, `name`, `genre`) VALUES (1,40,'Leonard Anghel','Software');
INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`) VALUES (1,'Isbn: 3443-dd323-234','Mastering JSF 2.2',1);

--fetchAuthorById(1) - The author was found but he is anonymous!
--fetchAuthorFromBook(Mastering JSF 2.2) - This book 'Mastering JSF 2.2' doesn't exist!
--INSERT INTO `author` (`id`, `age`, `name`, `genre`) VALUES (1,0,null,'Software');
--INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`) VALUES (1,null,null,null);

--fetchAuthorById(1) - The author was found but he is anonymous!
--fetchAuthorFromBook(Mastering JSF 2.2) - This book 'Mastering JSF 2.2' doesn't exist!
--INSERT INTO `author` (`id`, `age`, `name`, `genre`) VALUES (1,0,null,'Software');
--INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`) VALUES (1,null,null,1);

--fetchAuthorById(1) - Author name: Leonard Anghel
--fetchAuthorFromBook(Mastering JSF 2.2) - This book 'Mastering JSF 2.2' doesn't exist!
--INSERT INTO `author` (`id`, `age`, `name`, `genre`) VALUES (1,40,'Leonard Anghel','Software');
--INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`) VALUES (1,null,null,1);

--fetchAuthorById(1) - The author was found but he is anonymous!
--fetchAuthorFromBook(Mastering JSF 2.2) - The author was found but he is anonymous!
--INSERT INTO `author` (`id`, `age`, `name`, `genre`) VALUES (1,0,null,'Software');
--INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`) VALUES (1,'Isbn: 3443-dd323-234','Mastering JSF 2.2',1);

--fetchAuthorById(1) - Author name: Leonard Anghel
--fetchAuthorFromBook(Mastering JSF 2.2) - Author of this book doesn't exist ... hmmm!
--INSERT INTO `author` (`id`, `age`, `name`, `genre`) VALUES (1,40,'Leonard Anghel','Software');
--INSERT INTO `book` (`id`, `isbn`, `title`, `author_id`) VALUES (1,'Isbn: 3443-dd323-234','Mastering JSF 2.2',null);