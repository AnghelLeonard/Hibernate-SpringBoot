package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Full Joins
    // Query all players and tournaments (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Player a FULL JOIN a.tournament b")
    List<TournamentPlayerNameDto> fetchAllPlayersAndTournamentsFullJoinJpql();
    
    // Query all players and tournaments (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM players a FULL JOIN tournaments b ON b.id = a.tournament_id",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchAllPlayersAndTournamentsFullJoinSql();

}
