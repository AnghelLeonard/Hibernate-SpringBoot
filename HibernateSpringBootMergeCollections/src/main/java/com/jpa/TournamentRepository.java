package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TournamentRepository extends JpaRepository<Tournament, Long> { 
    
    @Query(value="SELECT t FROM Tournament t JOIN FETCH t.tennisPlayers WHERE t.name = ?1")
    Tournament tournamentAndPlayers(String name);
}

