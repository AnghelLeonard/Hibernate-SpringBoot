package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    //Right Excluding Joins
    // Query all tournaments that don't have players (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Player a RIGHT JOIN a.tournament b WHERE a.id IS NULL")
    List<TournamentPlayerNameDto> fetchAllTournamentsRightExcludingJoinJpql();
    
    // Query all tournaments that don't have players (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM players a RIGHT JOIN tournaments b ON b.id = a.tournament_id WHERE a.id IS NULL",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchAllTournamentsRightExcludingJoinSql();
}
