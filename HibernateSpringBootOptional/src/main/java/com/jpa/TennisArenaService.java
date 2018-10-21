package com.jpa;

import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisArenaService {

    private static final Logger logger
            = Logger.getLogger(TennisArenaService.class.getName());

    private final TournamentRepository tournamentRepository;
    private final TennisPlayerRepository tennisPlayerRepository;

    public TennisArenaService(TournamentRepository tournamentRepository,
            TennisPlayerRepository tennisPlayerRepository) {

        this.tournamentRepository = tournamentRepository;
        this.tennisPlayerRepository = tennisPlayerRepository;
    }

    public void fetchFirstTournamentNameById() {
        Optional<Tournament> tournament = tournamentRepository.findById(1L);

        if (tournament.isPresent()) {
            fetchTournamentName(tournament.get());
        } else {
            logger.info(() -> "This tournament doesn't exist!");
        }
    }

    @Transactional(readOnly = true)
    public void fetchTournamentNameByPlayerName() {
        Optional<TennisPlayer> tennisPlayer = tennisPlayerRepository.findByName("Rafael Nadal");

        if (tennisPlayer.isPresent()) {
            Optional<Tournament> tournament = tennisPlayer.get().getTournament();

            if (tournament.isPresent()) {
                fetchTournamentName(tournament.get());
            } else {
                logger.info(() -> "This tournament doesn't exist!");
            }
        } else {
            logger.info(() -> "This tennis player doesn't exist!");
        }
    }

    private void fetchTournamentName(Tournament tournament) {
        Optional<String> name = tournament.getName();

        if (name.isPresent()) {
            logger.info(() -> "Tournament name: " + name.get());
        } else {
            logger.info(() -> "This tournament has no name!");
        }
    }
}
