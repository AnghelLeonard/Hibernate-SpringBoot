package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Left Excluding Joins
    // Query all players that are not in tournaments (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Player a LEFT JOIN a.tournament b WHERE b.id IS NULL")
    List<TournamentPlayerNameDto> fetchAllPlayersLeftExcludingJoinJpql();

    // Query all players that are not in tournaments (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM players a LEFT JOIN tournaments b ON b.id = a.tournament_id WHERE b.id IS NULL",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchAllPlayersLeftExcludingJoinSql();
    
}
