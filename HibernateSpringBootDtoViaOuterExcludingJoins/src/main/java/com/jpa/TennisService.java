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

    // Query all tournaments that don't have players 
    // and all players that don't participate in tournaments (JPQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsWithoutPlayersAndViceversaOuterExcludingJoinJpql() {
        return tournamentRepository.fetchAllTournamentsWithoutPlayersAndViceversaOuterExcludingJoinJpql();
    }
   
   // Query all tournaments that don't have players 
    // and all players that don't participate in tournaments (SQL)
    public List<TournamentPlayerNameDto> fetchAllTournamentsWithoutPlayersAndViceversaOuterExcludingJoinSql() {
        return tournamentRepository.fetchAllTournamentsWithoutPlayersAndViceversaOuterExcludingJoinSql();
    }
    
    // Query all players that don't participate in tournaments
    // and all tournaments that don't have players (JPQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinJpql() {
        return playerRepository.fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinJpql();
    }
    
    // Query all players that don't participate in tournaments
    // and all tournaments that don't have players (SQL)
    public List<TournamentPlayerNameDto> fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinSql() {
        return playerRepository.fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinSql();
    }
}
