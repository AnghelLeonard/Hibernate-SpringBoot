CREATE TABLE authorsdb.author (
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

ALTER TABLE authorsdb.author
    OWNER to postgres;