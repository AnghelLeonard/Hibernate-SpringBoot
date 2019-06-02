package com.tennis;

import com.tennis.service.TennisService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// Use this practice only if you simply cannot use JOIN FETCH 
// with WHERE clause or @NamedEntityGraph

@SpringBootApplication
public class MainApplication {

    private final TennisService tennisService;

    public MainApplication(TennisService tennisService) {
        this.tennisService = tennisService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            tennisService.displayTournamentsAndPlayers();
        };
    }
}
