package com.jpa;

import java.util.List;
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

    @Transactional
    public void registerTournamentAndTennisPlayers() {

        Tournament rolandGarros = new Tournament()
                .name("Roland Garros")
                .addTennisPlayer(new TennisPlayer().name("Roger Federer"))
                .addTennisPlayer(new TennisPlayer().name("Rafael Nadal"))
                .addTennisPlayer(new TennisPlayer().name("David Ferer"));

        tournamentRepository.save(rolandGarros);                
    }

    public void displayAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();

        tournaments.forEach((t) -> logger.info(() -> "Tournament name: " + t.getName()));
    }

    @Transactional(readOnly=true)
    public void displayAllTennisPlayers() {
        List<TennisPlayer> players = tennisPlayerRepository.findAll();

        players.forEach((p) -> logger.info(() -> "Player name: " + p.getName()
                + " Tournament:" + p.getTournament().getName()));
    }
}
