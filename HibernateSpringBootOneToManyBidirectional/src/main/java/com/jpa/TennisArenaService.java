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
    public void registerTennisPlayer() {
        Tournament tournament = tournamentRepository.findByName("US Open");
        
        TennisPlayer player = new TennisPlayer();
        player.setName("Verdasco F");
        
        tournament.addTennisPlayer(player);
    }
    
    @Transactional
    public void deregisterTennisPlayer() {
        Tournament tournament = tournamentRepository.findByName("Roland Garros"); 
        TennisPlayer player = tournament.getTennisPlayers().get(0);
        
        tournament.removeTennisPlayer(player);
    }

    public void displayAllTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        
        tournaments.forEach((t) -> logger.info(() -> "Tournament name: " +t.getName()));
    }
    
    public void displayAllTennisPlayers() {
        List<TennisPlayer> players = tennisPlayerRepository.findAll();
        
        players.forEach((p) -> logger.info(() -> "Player name: " +p.getName()));
    }
}
