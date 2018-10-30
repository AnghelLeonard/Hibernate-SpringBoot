package com.jpa;

import java.util.List;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    //Inner Joins
    // Query the tournaments of all players (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Player a JOIN a.tournament b")
    List<TournamentPlayerNameDto> fetchTournamentsOfPlayersNamesInnerJoinJpql();

    // Query the tournaments of all players (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM tournaments b INNER JOIN players a ON b.id = a.tournament_id",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchTournamentsOfPlayersNamesInnerJoinSql();

    // Query all tournaments that have players with rank smaller or equal to "rank" (JPQL)
    // Note: HINT_PASS_DISTINCT_THROUGH - doesn't work with Spring Projections
    @Query(value = "SELECT DISTINCT b.id as id, b.tname as tname "
            + "FROM Player a JOIN a.tournament b WHERE a.prank <= ?1")    
    List<TournamentIdNameDto> fetchTournamentsIdNameByRankInnerJoinJpql(int rank);
    
        // Query all tournaments that have players with rank smaller or equal to "rank" (SQL)
    // Note: HINT_PASS_DISTINCT_THROUGH - doesn't work with Spring Projections
    @Query(value = "SELECT DISTINCT b.id as id, b.tournament_name as tname "
            + "FROM tournaments b INNER JOIN players a ON b.id = a.tournament_id "
            + "WHERE a.player_rank <= ?1",
            nativeQuery = true)    
    List<TournamentIdNameDto> fetchTournamentsIdNameByRankInnerJoinSql(int rank);
}
