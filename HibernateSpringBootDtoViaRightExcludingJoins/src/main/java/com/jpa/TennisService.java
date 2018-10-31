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

    // Query all tournaments that don't have players (JPQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsRightExcludingJoinJpql() {
        return tournamentRepository.fetchAllTournamentsRightExcludingJoinJpql();
    }

    // Query all tournaments that don't have players (SQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsRightExcludingJoinSql() {
        return tournamentRepository.fetchAllTournamentsRightExcludingJoinSql();
    }

    // Query all players that are not in tournaments (JPQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersRightExcludingJoinJpql() {
        return playerRepository.fetchAllPlayersRightExcludingJoinJpql();
    }
    
    // Query all players that are not in tournaments (SQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersRightExcludingJoinSql() {
        return playerRepository.fetchAllPlayersRightExcludingJoinSql();
    }
}
