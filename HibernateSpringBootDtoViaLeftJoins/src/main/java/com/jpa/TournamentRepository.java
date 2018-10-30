package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    //Left Joins
    // Query all tournaments even if they don't have players (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Tournament b LEFT JOIN b.players a")
    List<TournamentPlayerNameDto> fetchAllTournamentsLeftJoinJpql();

    // Query all tournaments even if they don't have players (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM tournaments b LEFT JOIN players a ON b.id = a.tournament_id",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchAllTournamentsLeftJoinSql();

}
