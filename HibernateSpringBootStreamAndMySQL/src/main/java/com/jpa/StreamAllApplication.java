package com.jpa;

import java.util.logging.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamAllApplication {

    private static final Logger logger = Logger.getLogger(StreamAllApplication.class.getName());

    private final UserService userService;

    public StreamAllApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StreamAllApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            logger.info("Populating database with dummy data ...");
            userService.populateDatabase();
            
            logger.info("Start streaming ...");
            userService.streamDatabase();
        };
    }
}
