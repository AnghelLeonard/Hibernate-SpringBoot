package com.jpa;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisService {
    
    private static final Logger logger
            = Logger.getLogger(TennisService.class.getName());
    
    private final TournamentRepository tournamentRepository;
    private final TennisPlayerRepository tennisPlayerRepository;
    
    public TennisService(TournamentRepository tournamentRepository,
            TennisPlayerRepository tennisPlayerRepository) {
        
        this.tournamentRepository = tournamentRepository;
        this.tennisPlayerRepository = tennisPlayerRepository;
    }
    
    @Transactional
    public void registerTennisPlayer() {
        Tournament tournament = tournamentRepository.findById(2L)
                .orElseThrow(() -> new NoSuchElementException("Tournament with id 2 was not found"));
        // or 
        // Tournament tournament = tournamentRepository.findByName("US Open");
        
        TennisPlayer player = new TennisPlayer();
        player.setName("Verdasco F");        
        
        tournament.addTennisPlayer(player);
    }
    
    @Transactional
    public void unregisterTennisPlayer() {
        Tournament tournament = tournamentRepository.findByName("Roland Garros");        
        TennisPlayer player = tournament.getTennisPlayers().get(0);
        
        tournament.removeTennisPlayer(player);
    }
    
    @Transactional
    public void unregisterTournament() {
        Tournament tournament = tournamentRepository.findByName("US Open"); 
        
        tournamentRepository.delete(tournament);
    }
    
    public void displayAllExceptDeletedTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAll();
        
        logger.info("All tournaments except deleted:");
        tournaments.forEach((t) -> logger.info(() -> "Tournament name: " + t.getName()));
    }        
    
    public void displayAllIncludeDeletedTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAllIncludingDeleted();
        
        logger.info("All tournaments including deleted:");
        tournaments.forEach((t) -> logger.info(() -> "Tournament name: " + t.getName()));
    }
    
    public void displayAllOnlyDeletedTournaments() {
        List<Tournament> tournaments = tournamentRepository.findAllOnlyDeleted();
        
        logger.info("All deleted tournaments:");
        tournaments.forEach((t) -> logger.info(() -> "Tournament name: " + t.getName()));
    }
    
    public void displayAllExceptDeletedTennisPlayers() {
        List<TennisPlayer> players = tennisPlayerRepository.findAll();
        
        logger.info("All tennis players except deleted:");
        players.forEach((t) -> logger.info(() -> "Player name: " + t.getName()));
    }
    
    public void displayAllIncludeDeletedTennisPlayers() {
        List<TennisPlayer> players = tennisPlayerRepository.findAllIncludingDeleted();
        
        logger.info("All tennis players including deleted:");
        players.forEach((t) -> logger.info(() -> "Player name: " + t.getName()));
    }        
    
    public void displayAllOnlyDeletedTennisPlayers() {
        List<TennisPlayer> players = tennisPlayerRepository.findAllOnlyDeleted();
        
        logger.info("All deleted tennis players:");
        players.forEach((t) -> logger.info(() -> "Player name: " + t.getName()));
    }
}
