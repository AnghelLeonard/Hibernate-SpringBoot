package com.tennis.service;

import com.tennis.repository.TournamentRepository;
import com.tennis.entity.Tournament;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisService {
 
    private final TournamentRepository tournamentRepository;
    
    public TennisService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
    
    @Transactional(readOnly=true)
    public void displayTournamentsAndPlayers() {
        
        List<Tournament> tournaments = tournamentRepository.findAll();
        
        for(Tournament tournament: tournaments) {
            System.out.println("Tournament: " + tournament.getName());
            System.out.println("No of players: " + tournament.getTennisPlayers().size());
        }
    }
}
