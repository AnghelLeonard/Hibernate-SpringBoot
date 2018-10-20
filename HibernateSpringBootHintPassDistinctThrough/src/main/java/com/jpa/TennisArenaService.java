package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TennisArenaService {

    private static final Logger logger = 
            Logger.getLogger(TennisArenaService.class.getName());
        
    @Autowired
    private TournamentRepository tournamentRepository;

    public void fetchDistinctTournamentsWithDuplicates() {
        logger.info("Fetching tournaments with duplicates ...");
        
        List<Tournament> tournaments = 
                tournamentRepository.fetchTournamentsLeftJoinPlayersWithDuplicates();
        
        tournaments.forEach((t) -> {
            logger.info(() -> "Item: " + t.getId() + ": Name: " + t.getName());
        });
    }
    
    public void fetchDistinctTournamentsWithoutHint() {
        logger.info("Fetching tournaments without HINT_PASS_DISTINCT_THROUGH hint ...");
        
        List<Tournament> tournaments = 
                tournamentRepository.fetchTournamentsLeftJoinPlayersWithoutHint();
        
        tournaments.forEach((t) -> {
            logger.info(() -> "Item: " + t.getId() + ": Name: " + t.getName());
        });
    }

    public void fetchDistinctTournamentsWithHint() {
        logger.info("Fetching tournaments with HINT_PASS_DISTINCT_THROUGH hint ...");
        
        List<Tournament> tournaments = 
                tournamentRepository.fetchTournamentsLeftJoinPlayersWithHint();
        
        tournaments.forEach((t) -> {
            logger.info(() -> "Item: " + t.getId() + ": Name: " + t.getName());
        });
    }
}
