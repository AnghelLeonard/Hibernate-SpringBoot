package com.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisService {

    private final TournamentRepository tournamentRepository;

    public TennisService(TournamentRepository tournamentRepository) {

        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public void addTournamentsAndPlayers() {

        Tournament t1 = new Tournament();
        t1.setName("Wimbledon");
        Tournament t2 = new Tournament();
        t2.setName("Roland Garros");

        Player p1 = new Player();
        p1.setName("Roger Federer");
        Player p2 = new Player();
        p2.setName("Rafael Nadal");
        Player p3 = new Player();
        p3.setName("David Ferer");

        t1.addPlayer(p1);
        t1.addPlayer(p2);
        t2.addPlayer(p1);
        t2.addPlayer(p2);
        t2.addPlayer(p3);

        tournamentRepository.save(t1);
        tournamentRepository.save(t2);
    }
}
