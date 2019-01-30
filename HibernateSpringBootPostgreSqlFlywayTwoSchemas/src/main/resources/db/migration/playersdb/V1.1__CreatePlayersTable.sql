CREATE TABLE players_db.tennis_players
(
    id bigserial NOT NULL,
    name character varying(50),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE players_db.tennis_players
    OWNER to postgres;