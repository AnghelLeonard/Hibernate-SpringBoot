CREATE TABLE books.book (
  id bigserial NOT NULL,
  isbn character varying(50),
  title character varying(50),
  authors character varying(50),
  PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE books.book
    OWNER to postgres;