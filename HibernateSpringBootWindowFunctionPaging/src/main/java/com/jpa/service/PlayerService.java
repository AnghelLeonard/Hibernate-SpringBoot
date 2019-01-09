package com.jpa.service;

import com.jpa.repository.PlayerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
     
    private final PlayerRepository playerRepository;
    
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDto> fetchNextSlice(int page, int size) {
        return playerRepository.fetchAllPlayers(page, size);
    }
}
