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

    // Query all players that are not in tournaments (JPQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersLeftExcludingJoinJpql() {
        return playerRepository.fetchAllPlayersLeftExcludingJoinJpql();
    }
    
    // Query all players that are not in tournaments (SQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersLeftExcludingJoinSql() {
        return playerRepository.fetchAllPlayersLeftExcludingJoinSql();
    }
    
    // Query all tournaments that don't have players (JPQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsLeftExcludingJoinJpql() {
        return tournamentRepository.fetchAllTournamentsLeftExcludingJoinJpql();
    }
    
    // Query all tournaments that don't have players (SQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsLeftExcludingJoinSql() {
        return tournamentRepository.fetchAllTournamentsLeftExcludingJoinSql();
    }
}
