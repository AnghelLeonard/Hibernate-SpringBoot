package com.jpa;

import org.springframework.stereotype.Service;

@Service
public class TennisService {

    private final TournamentRepository tournamentRepository;

    public TennisService(TournamentRepository tournamentRepository) {

        this.tournamentRepository = tournamentRepository;
    }

    public Tournament fetchTournament() {
        Tournament tournament = tournamentRepository.findByName("Us Open");

        return tournament;
    }

}
