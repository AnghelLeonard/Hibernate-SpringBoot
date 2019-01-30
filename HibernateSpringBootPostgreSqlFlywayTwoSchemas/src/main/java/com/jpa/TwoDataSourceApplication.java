package com.jpa;

import com.jpa.ds1.PlayerService;
import com.jpa.ds2.CoachService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TwoDataSourceApplication {

    private static final Logger logger = Logger.getLogger(TwoDataSourceApplication.class.getName());

    private final PlayerService playerService;
    private final CoachService coachService;

    public TwoDataSourceApplication(PlayerService playerService, CoachService coachService) {
        this.playerService = playerService;
        this.coachService = coachService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwoDataSourceApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            logger.log(Level.INFO, "Saved player: {0}", playerService.saveOnePlayer().getName()); // ds1
            logger.log(Level.INFO, "Saved coach: {0}", coachService.saveOneCoach().getName());    // ds2
        };
    }
}