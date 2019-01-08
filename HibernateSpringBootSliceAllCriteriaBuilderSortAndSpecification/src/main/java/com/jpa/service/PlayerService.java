package com.jpa.service;

import com.jpa.domain.Player;
import com.jpa.repository.PlayerRepository;
import static com.jpa.service.PlayerSpecs.isAgeGt21;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
     
    private final PlayerRepository playerRepository;
    
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
            
    public Slice<Player> fetchNextSlice(int page, int size) {
               
        return playerRepository.findAll(isAgeGt21(), 
                PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")));
    }
}
