package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisArenaService {

    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Autowired
    private TennisPlayerRepository tennisPlayerRepository;

    @Transactional
    public void addPlayerToTournament() {
        // behind getOne() we have EntityManager#getReference()
        Tournament proxy = tournamentRepository.getOne(1L);
        
        TennisPlayer tennisPlayer = new TennisPlayer();
        tennisPlayer.setName("David Ferer");
        tennisPlayer.setTournament(proxy);
        
        tennisPlayerRepository.save(tennisPlayer);
    }
}