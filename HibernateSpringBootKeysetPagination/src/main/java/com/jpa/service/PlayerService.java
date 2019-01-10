package com.jpa.service;

import com.jpa.domain.Player;
import com.jpa.repository.PlayerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
     
    private final PlayerRepository playerRepository;
    
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> fetchNextSlice(long id, int limit) {
        return playerRepository.fetchAllPlayers(id, limit);
    }
}
