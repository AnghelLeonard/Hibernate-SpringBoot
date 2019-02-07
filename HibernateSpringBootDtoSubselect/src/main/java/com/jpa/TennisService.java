package com.jpa;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisService {

    private final TournamentDtoRepository tournamentDtoRepository;

    public TennisService(TournamentDtoRepository tournamentDtoRepository) {
        this.tournamentDtoRepository = tournamentDtoRepository;
    }

    @Transactional(readOnly = true)
    public void fetchTournamentsWithPlayers(String court) {

        List<TournamentDto> tournaments = tournamentDtoRepository.findAll();
        for (TournamentDto tournament : tournaments) {
            System.out.println("Tournament name: " + tournament.getTname());

            if (tournament.getTcourt().equals(court)) {
                // lazy loading of players for this type of court
                Set<Player> players = tournament.getPlayers();
                players.forEach((p) -> System.out.println(p.getPname() + "(" + p.getPrank() + ")"));
            }
        }
    }
}
