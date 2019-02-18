package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    
    Tournament findByName(String name);

    @Query(value = "SELECT * FROM tournament", nativeQuery = true)
    List<Tournament> findAllIncludingDeleted();

    @Query(value = "SELECT * FROM tournament as t WHERE t.deleted = true", nativeQuery = true)
    List<Tournament> findAllOnlyDeleted();
}
