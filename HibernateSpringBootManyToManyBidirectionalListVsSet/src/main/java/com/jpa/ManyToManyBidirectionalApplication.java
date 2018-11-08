package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManyToManyBidirectionalApplication {

    @Autowired
    private TennisService tennisService;

    public static void main(String[] args) {
        SpringApplication.run(ManyToManyBidirectionalApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("================================================");
            System.out.println("Populate database and remove a player (List) ...");            
            System.out.println("================================================");
            tennisService.addTournamentsAndPlayersRemoveOnePlayerList();
            
            System.out.println("\n");
            System.out.println("================================================");
            System.out.println("Populate database and remove a player (Set) ...");
            System.out.println("================================================");
            tennisService.addTournamentsAndPlayersRemoveOnePlayerSet();
        };
    }
}
