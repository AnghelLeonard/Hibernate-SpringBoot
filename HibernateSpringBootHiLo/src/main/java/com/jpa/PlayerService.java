package com.jpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public void batch1000Players() {

        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            player.setName("Player_" + i);
            
            players.add(player);
        }
        
        playerRepository.saveAll(players);

    }
}
