--fetchFirstTournamentNameById(1) - Tournament name: RolandGaros
--fetchTournamentNameByPlayerName(Rafael Nadal) - Tournament name: RolandGaros
INSERT INTO tournament (id, name) VALUES (1, 'RolandGaros');
INSERT INTO tennis_player (id, name, tournament_id) VALUES (1, 'Rafael Nadal', 1);

--fetchFirstTournamentNameById(1) - This tournament has no name!
--fetchTournamentNameByPlayerName(Rafael Nadal) - This tennis player doesn't exist!
--INSERT INTO tournament (id, name) VALUES (1, null);
--INSERT INTO tennis_player (id, name, tournament_id) VALUES (1, null, null);

--fetchFirstTournamentNameById(1) - This tournament has no name!
--fetchTournamentNameByPlayerName(Rafael Nadal) - This tennis player doesn't exist!
--INSERT INTO tournament (id, name) VALUES (1, null);
--INSERT INTO tennis_player (id, name, tournament_id) VALUES (1, null, 1);

--fetchFirstTournamentNameById(1) - Tournament name: RolandGaros
--fetchTournamentNameByPlayerName(Rafael Nadal) - This tennis player doesn't exist!
--INSERT INTO tournament (id, name) VALUES (1, 'RolandGaros');
--INSERT INTO tennis_player (id, name, tournament_id) VALUES (1, null, 1);

--fetchFirstTournamentNameById(1) - This tournament has no name!
--fetchTournamentNameByPlayerName(Rafael Nadal) - This tournament has no name!
--INSERT INTO tournament (id, name) VALUES (1, null);
--INSERT INTO tennis_player (id, name, tournament_id) VALUES (1, 'Rafael Nadal', 1);

--fetchFirstTournamentNameById(1) - Tournament name: RolandGaros
--fetchTournamentNameByPlayerName(Rafael Nadal) - This tournament doesn't exist!
--INSERT INTO tournament (id, name) VALUES (1, 'RolandGaros');
--INSERT INTO tennis_player (id, name, tournament_id) VALUES (1, 'Rafael Nadal', null);