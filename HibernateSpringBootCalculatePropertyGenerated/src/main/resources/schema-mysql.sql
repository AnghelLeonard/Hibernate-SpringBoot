-- Drop
DROP TABLE IF EXISTS bookstoredb.book;

-- Create
CREATE TABLE book (
  id BIGINT NOT NULL AUTO_INCREMENT,
  discounted DOUBLE GENERATED ALWAYS AS ((`price` - `price` * 0.25)) STORED,
  isbn VARCHAR(255),
  price DOUBLE PRECISION NOT NULL,
  title VARCHAR(255),
  PRIMARY KEY (id)
)