package com.jpa.repository;

import com.jpa.domain.Player;
import com.jpa.service.PlayerDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT name, age, COUNT(*) OVER() AS total FROM player ORDER BY age LIMIT ?1, ?2",
            nativeQuery = true)
    List<PlayerDto> fetchAllPlayers(int page, int size);
}
