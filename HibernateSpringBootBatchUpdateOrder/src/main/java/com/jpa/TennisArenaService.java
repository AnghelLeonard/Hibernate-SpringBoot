package com.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisArenaService {

    private final TournamentRepository tournamentRepository;
    private final PlayerRepository playerRepository;

    public TennisArenaService(TournamentRepository tournamentRepository, PlayerRepository playerRepository) {
        this.tournamentRepository = tournamentRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void updatePlayers() {
        
        List<Tournament> tournaments = tournamentRepository.findAll();

        for (Tournament tournament : tournaments) {            
            tournament.setName("T: " + tournament.getId());
            for (TennisPlayer player : tournament.getTennisPlayers()) {
                player.setName("P: " + player.getId());
            }
        }
        
        // or, find all players and update
        /*
        List<TennisPlayer> players = playerRepository.findAll();
        
        for(TennisPlayer player: players) {
            player.setName("P: " + player.getId());
            
            Tournament tournament = player.getTournament();
            tournament.setName("T: " + tournament.getId());
        }
        */        
    }
}