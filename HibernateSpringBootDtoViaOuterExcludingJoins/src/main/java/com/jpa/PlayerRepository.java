package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Outer Excluding Joins
    // Query all players that don't participate in tournaments
    // and all tournaments that don't have players (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Tournament b FULL JOIN b.players a WHERE a.id IS NULL OR b.id IS NULL")
    List<TournamentPlayerNameDto> fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinJpql();

    // Query all players that don't participate in tournaments
    // and all tournaments that don't have players (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM tournaments b FULL JOIN players a ON b.id = a.tournament_id "
            + "WHERE a.id IS NULL OR b.id IS NULL",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinSql();

}
