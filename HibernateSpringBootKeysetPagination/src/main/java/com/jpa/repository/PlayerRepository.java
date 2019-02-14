package com.jpa.repository;

import com.jpa.domain.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT * FROM player AS p WHERE p.id > ?1 ORDER BY p.id ASC LIMIT ?2",
           nativeQuery = true)
    List<Player> fetchAllPlayers(long id, int limit);
}
