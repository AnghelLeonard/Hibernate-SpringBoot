package com.jpa;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TennisPlayerRepository extends JpaRepository<TennisPlayer, Long> {  
    
    @Transactional(readOnly=true)
    Optional<TennisPlayer> findByName(String name);
}
