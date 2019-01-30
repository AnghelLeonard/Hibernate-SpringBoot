CREATE TABLE coaches_db.tennis_coaches
(
    id bigserial NOT NULL,
    name character varying(50),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE coaches_db.tennis_coaches
    OWNER to postgres;