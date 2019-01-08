package com.jpa.service;

import com.jpa.domain.Player;
import com.jpa.repository.PlayerRepository;
import static com.jpa.service.PlayerSpecs.isAgeGt21;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.LockModeType;
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
    
       // hint example
       // Map<String, Object> hints = new HashMap<>();
       // hints.put("org.hibernate.readOnly", true);
        
        return playerRepository.findAll(isAgeGt21(), 
                PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")), 
                LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }
}
