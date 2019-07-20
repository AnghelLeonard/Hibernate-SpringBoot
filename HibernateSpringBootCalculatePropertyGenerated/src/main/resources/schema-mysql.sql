CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discount` double GENERATED ALWAYS AS ((`price` - (`price` * 0.25))) VIRTUAL,
  `isbn` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM bookstoredb.book;