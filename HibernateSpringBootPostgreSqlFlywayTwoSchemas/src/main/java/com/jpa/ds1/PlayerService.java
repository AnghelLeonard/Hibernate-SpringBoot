package com.jpa.ds1;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player saveOnePlayer() {
        Player player = new Player();
        player.setName("Rafael Nadal");

        return playerRepository.save(player);        
    }

}
