package com.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisArenaService {

    private final PlayerRepository playerRepository;

    public TennisArenaService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void insertPlayers() {

        for (int i = 0; i < 20; i++) {
            TennisPlayer player = new TennisPlayer();
            player.setName("Player: " + i);

            playerRepository.save(player);
        }

    }

    @Transactional
    public void updatePlayers() {
        
        List<TennisPlayer> players = playerRepository.findAll();

        players.forEach((t) -> t.setName("P: " + t.getId()));        
    }
}
