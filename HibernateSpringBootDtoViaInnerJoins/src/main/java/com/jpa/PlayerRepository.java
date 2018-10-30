package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Inner Joins
    // Query the players from all tournaments (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Tournament b INNER JOIN b.players a")
    List<TournamentPlayerNameDto> fetchPlayersFromTournamentsNamesInnerJoinJpql();

    // Query the players from all tournaments (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM players a INNER JOIN tournaments b ON b.id = a.tournament_id",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchPlayersFromTournamentsNamesInnerJoinSql();

    // Query all players that participate to a "tournament"
    // and have their rank smaller or equal with "rank" (JPQL)
    @Query(value = "SELECT a.pname as pname, a.prank as prank "
            + "FROM Tournament b INNER JOIN b.players a "
            + "WHERE b.tname = ?1 AND a.prank <= ?2")
    List<PlayerRankNameDto> fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinJpql(String tournament, int rank);

    // Query all players that participate to a "tournament"
    // and have their rank smaller or equal with "rank" (SQL)
    @Query(value = "SELECT a.player_name as pname, a.player_rank as prank "
            + "FROM players a INNER JOIN tournaments b ON b.id = a.tournament_id "
            + "WHERE b.tournament_name = ?1 AND a.player_rank <= ?2",
            nativeQuery = true)
    List<PlayerRankNameDto> fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinSql(String tournament, int rank);
    
    
    
}
