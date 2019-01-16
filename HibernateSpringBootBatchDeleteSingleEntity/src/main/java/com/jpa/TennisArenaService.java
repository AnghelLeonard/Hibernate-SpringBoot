package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    public void deletePlayers() {
        
        List<TennisPlayer> players = playerRepository.findAll();
        
        // FIRST APPROACH: deleteAllInBatch()        
        // Output sample - no batching:
        // Name:DATA_SOURCE_PROXY, Connection:25, Time:38, Success:True
        // Type:Prepared, Batch:False, QuerySize:1, BatchSize:0
        // Query:["delete from tennis_player"]
        
        // playerRepository.deleteAllInBatch();
        
        // SECOND APPROACH: deleteInBatch()
        // Output sample - no batching:
        // Name:DATA_SOURCE_PROXY, Connection:25, Time:24, Success:True
        // Type:Prepared, Batch:False, QuerySize:1, BatchSize:0
        // Query:["delete from tennis_player where id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=? or id=?"]
        // Params:[(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)]
        
        // playerRepository.deleteInBatch(players);
        
        // THIRD APPROACH: use delete()
        // Output sample: batching is happening
        // Name:DATA_SOURCE_PROXY, Connection:25, Time:467, Success:True
        // Type:Prepared, Batch:True, QuerySize:1, BatchSize:7
        // Query:["delete from tennis_player where id=? and version=?"]
        // Params:[(1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0)]        
        
        players.forEach(playerRepository::delete);
                
    }
}
