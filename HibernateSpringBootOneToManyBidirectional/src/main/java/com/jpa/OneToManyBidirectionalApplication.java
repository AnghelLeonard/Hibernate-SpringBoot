package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OneToManyBidirectionalApplication {

    @Autowired
    private TennisArenaService tennisArenaService;

    public static void main(String[] args) {
        SpringApplication.run(OneToManyBidirectionalApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            tennisArenaService.displayAllTournaments();
            tennisArenaService.displayAllTennisPlayers();
            
            tennisArenaService.registerTennisPlayer();
            tennisArenaService.displayAllTennisPlayers();
            
            tennisArenaService.deregisterTennisPlayer();
            tennisArenaService.displayAllTennisPlayers();
        };
    }
}
