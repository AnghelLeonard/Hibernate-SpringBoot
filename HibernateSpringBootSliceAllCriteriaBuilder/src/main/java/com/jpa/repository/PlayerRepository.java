package com.jpa.repository;

import com.jpa.repository.impl.SlicePagingRepositoryImplementation;
import com.jpa.domain.Player;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository extends SlicePagingRepositoryImplementation<Player> {            
    
    public PlayerRepository() {
        super(Player.class);
    }    
}
