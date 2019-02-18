package com.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TennisPlayerRepository extends JpaRepository<TennisPlayer, Long> {

    Optional<TennisPlayer> findByName(String name);

    @Query(value = "SELECT * FROM tennis_player", nativeQuery = true)
    List<TennisPlayer> findAllIncludingDeleted();

    @Query(value = "SELECT * FROM tennis_player as p WHERE p.deleted = true", nativeQuery = true)
    List<TennisPlayer> findAllOnlyDeleted();
}
