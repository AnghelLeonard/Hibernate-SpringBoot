package com.jpa;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisService {

    private final TournamentRepository tournamentRepository;
    private final TennisPlayerRepository tennisPlayerRepository;

    public TennisService(TournamentRepository tournamentRepository,
            TennisPlayerRepository tennisPlayerRepository) {

        this.tournamentRepository = tournamentRepository;
        this.tennisPlayerRepository = tennisPlayerRepository;
    }

    public List<TennisPlayer> fetchPlayersOfTournament(String name) {

        return tennisPlayerRepository.playersOfTournament(name);
    }

    @Transactional
    public void updatePlayersOfTorunament(String name, List<TennisPlayer> players) {

        Tournament tournament = tournamentRepository.tournamentAndPlayers(name);
        System.out.println("-------------------------------------------------");

        // Remove the existing database rows that are no 
        // longer found in the incoming collection (players)
        tournament.getTennisPlayers().removeIf((t) -> !players.contains(t));

        // Update the existing database rows which can be found 
        // in the incoming collection (players)
        List<TennisPlayer> newPlayers = players.stream()
                .filter((t) -> !tournament.getTennisPlayers().contains(t))
                .collect(Collectors.toList());

        players.stream()
                .filter((t) -> !newPlayers.contains(t))                
                .forEach((t) -> {
                    t.setTournament(tournament);
                    TennisPlayer mergedPlayer = tennisPlayerRepository.save(t);
                    tournament.getTennisPlayers().set(
                            tournament.getTennisPlayers().indexOf(mergedPlayer),
                            mergedPlayer);
                });

        // Add the rows found in the incoming collection, 
        // which cannot be found in the current database snapshot
        newPlayers.forEach((t) -> tournament.addTennisPlayer(t));
    }

}