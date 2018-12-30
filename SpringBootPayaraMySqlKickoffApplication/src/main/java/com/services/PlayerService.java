package com.services;

import com.jpa.queries.PlayerRepository;
import com.jpa.model.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
    
    private final PlayerRepository playerRepository;
    
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
       
    public Player newPlayerWithIdOne() {
        
        Player player = new Player();
        
        player.setId(1L);
        player.setName("Rafael Nadal");
        player.setAge(30);
        player.setCity("Mallorca");
        
        return playerRepository.save(player);
    }
    
    public Player findFirstPlayer() {
        return playerRepository.findById(1L).orElse(new Player());
    }
}
