package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Right Excluding Joins
    // Query all players that are not in tournaments (JPQL)
    @Query(value = "SELECT a.pname as pname, b.tname as tname "
            + "FROM Tournament b RIGHT JOIN b.players a WHERE b.id IS NULL")
    List<TournamentPlayerNameDto> fetchAllPlayersRightExcludingJoinJpql();

    // Query all players that are not in tournaments (SQL)
    @Query(value = "SELECT a.player_name as pname, b.tournament_name as tname "
            + "FROM tournaments b RIGHT JOIN players a ON b.id = a.tournament_id WHERE b.id IS NULL",
            nativeQuery = true)
    List<TournamentPlayerNameDto> fetchAllPlayersRightExcludingJoinSql();
}
