package com.jpa;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TennisService {

    private final TournamentRepository tournamentRepository;
    private final PlayerRepository playerRepository;

    public TennisService(TournamentRepository tournamentRepository,
            PlayerRepository playerRepository) {
        this.tournamentRepository = tournamentRepository;
        this.playerRepository = playerRepository;
    }

    // Query all players and tournaments (JPQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersAndTournamentsFullJoinJpql() {
        return playerRepository.fetchAllPlayersAndTournamentsFullJoinJpql();
    }
   
    // Query all players and tournaments (SQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersAndTournamentsFullJoinSql() {
        return playerRepository.fetchAllPlayersAndTournamentsFullJoinSql();
    }
    
    // Query all tournaments and players (JPQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsAndPlayersFullJoinJpql() {
        return tournamentRepository.fetchAllTournamentsAndPlayersFullJoinJpql();
    }
    
    // Query all tournaments and players (SQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsAndPlayersFullJoinSql() {
        return tournamentRepository.fetchAllTournamentsAndPlayersFullJoinSql();
    }
}
