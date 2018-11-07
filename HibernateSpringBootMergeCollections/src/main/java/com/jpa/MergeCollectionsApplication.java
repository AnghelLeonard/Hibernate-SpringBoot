package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MergeCollectionsApplication {

    @Autowired
    private TennisService tennisService;

    public static void main(String[] args) {
        SpringApplication.run(MergeCollectionsApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("------------------- Players from US Open --------------------");
            List<TennisPlayer> players = tennisService.fetchPlayersOfTournament("US Open");            
            
            players.forEach((t) -> System.out.println("Us Open: " + t.getName() + " | id:(" + t.getId() + ")"));
            
            System.out.println("---------- Players from US Open Updated Detached ------------");
            
            // ,update first player name
            players.get(0).setName("Fernando Verdasco");
            
            // remove second player
            players.remove(1);
                        
            // add a new player
            TennisPlayer player = new TennisPlayer();
            player.setName("Alexander Zverev");
            players.add(player);
            
            players.forEach((t) -> System.out.println("Us Open: " + t.getName() + " | id:(" + t.getId() + ")"));
            
            System.out.println("----------------- Players from US Open Merged ----------------");
            tennisService.updatePlayersOfTorunament("Us Open", players);
            
            players.forEach((t) -> System.out.println("Us Open: " + t.getName() + " | id:(" + t.getId() + ")"));
        };
    }
}
