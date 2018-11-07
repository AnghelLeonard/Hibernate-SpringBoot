package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TennisPlayerRepository extends JpaRepository<TennisPlayer, Long> {

    @Query(value = "SELECT p FROM TennisPlayer p JOIN p.tournament t WHERE t.name = ?1")
    List<TennisPlayer> playersOfTournament(String name);
}
