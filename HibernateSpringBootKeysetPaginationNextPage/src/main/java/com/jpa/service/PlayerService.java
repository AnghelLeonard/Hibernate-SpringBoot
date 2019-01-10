package com.jpa.service;

import com.jpa.domain.Player;
import com.jpa.repository.PlayerRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
     
    private final PlayerRepository playerRepository;
    
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Map<List<Player>, Boolean> fetchNextSlice(long id, int limit) {
        List<Player> players = playerRepository.fetchAllPlayers(id, limit + 1);
        
        if(players.size() == (limit + 1)) {
            players.remove(players.size() -1);
            return Collections.singletonMap(players, true);
        }
        
        return Collections.singletonMap(players, false);
    }
}
