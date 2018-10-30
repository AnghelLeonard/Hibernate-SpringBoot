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

    // Query the players from all tournaments (JPQL)
    public List<TournamentPlayerNameDto> fetchPlayersFromTournamentsNamesInnerJoinJpql() {
        return playerRepository.fetchPlayersFromTournamentsNamesInnerJoinJpql();
    }

    // Query the players from all tournaments (SQL)
    public List<TournamentPlayerNameDto> fetchPlayersFromTournamentsNamesInnerJoinSql() {
        return playerRepository.fetchPlayersFromTournamentsNamesInnerJoinSql();
    }

    // Query the tournaments of all players (JPQL)
    public List<TournamentPlayerNameDto> fetchTournamentsOfPlayersNamesInnerJoinJpql() {
        return tournamentRepository.fetchTournamentsOfPlayersNamesInnerJoinJpql();
    }

    // Query the tournaments of all players (SQL)
    public List<TournamentPlayerNameDto> fetchTournamentsOfPlayersNamesInnerJoinSql() {
        return tournamentRepository.fetchTournamentsOfPlayersNamesInnerJoinSql();
    }

    // Query all players that participate to a "tournament"
    // and have their rank smaller or equal with "rank" (JPQL)
    public List<PlayerRankNameDto> fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinJpql(String tournament, int rank) {
        return playerRepository.fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinJpql(tournament, rank);
    }
    
    // Query all players that participate to a "tournament"
    // and have their rank smaller or equal with "rank" (SQL)
    public List<PlayerRankNameDto> fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinSql(String tournament, int rank) {
        return playerRepository.fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinSql(tournament, rank);
    }

    // Query all tournaments that have players with rank smaller or equal to "rank" (JPQL)
    public List<TournamentIdNameDto> fetchTournamentsIdNameByRankInnerJoinJpql(int rank) {
        return tournamentRepository.fetchTournamentsIdNameByRankInnerJoinJpql(rank);
    }
    
    // Query all tournaments that have players with rank smaller or equal to "rank" (SQL)
    public List<TournamentIdNameDto> fetchTournamentsIdNameByRankInnerJoinSql(int rank) {
        return tournamentRepository.fetchTournamentsIdNameByRankInnerJoinSql(rank);
    }
}
