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

    // Query all tournaments even if they don't have players (JPQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsRightJoinJpql() {
        return tournamentRepository.fetchAllTournamentsRightJoinJpql();
    }
    
    // Query all tournaments even if they don't have players (SQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsRightJoinSql() {
        return tournamentRepository.fetchAllTournamentsRightJoinSql();
    }
    
   // Query all players even if they are not in tournaments (JPQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersRightJoinJpql() {
        return playerRepository.fetchAllPlayersRightJoinJpql();
    }
    
    // Query all players even if they are not in tournaments (SQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersRightJoinSql() {
        return playerRepository.fetchAllPlayersRightJoinSql();
    }
}
