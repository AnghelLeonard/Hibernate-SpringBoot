CREATE TABLE authors.author (
  id bigserial NOT NULL,
  name character varying(50),
  genre character varying(50),
  age integer,
  books character varying(50),
  PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE authors.author
    OWNER to postgres;