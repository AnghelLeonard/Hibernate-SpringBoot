package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @Transactional(readOnly = true)
    Tournament findByName(String name);
}
