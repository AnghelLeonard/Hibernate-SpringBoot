package com.jpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisArenaService {

    private final TournamentRepository tournamentRepository;

    public TennisArenaService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public void deletePlayers() {

        List<Tournament> tournaments = tournamentRepository.fetchAllTournamentsAndPlayers();

        tournaments.forEach(Tournament::removeAllPlayers);
        tournamentRepository.flush();

        tournaments.forEach(tournamentRepository::delete);
    }
}
