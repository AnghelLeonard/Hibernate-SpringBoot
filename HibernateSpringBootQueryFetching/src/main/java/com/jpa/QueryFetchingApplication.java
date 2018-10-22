package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QueryFetchingApplication {

    private static final Logger logger 
            = Logger.getLogger(QueryFetchingApplication.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Dao dao;

    public static void main(String[] args) {
        SpringApplication.run(QueryFetchingApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            // Query via JpaRepository (Spring Data Query Creation)
            List<User> users1a = userRepository.findByName("Larry Q");
            users1a.forEach((u) -> logger.info(() -> "JpaRepository 1: User name: " + u.getName()));
            
            // Query via JpaRepository (@Query)
            List<User> users1b = userRepository.findByName("Larry Q");
            users1b.forEach((u) -> logger.info(() -> "JpaRepository 2: User name: " + u.getName()));
            
            // Query via EntityManager
            List<User> users2 = dao.findByName("Mark S");
            users2.forEach((u) -> logger.info(() -> "EntityManager: User name: " + u.getName()));
            
            // Query via Hibernate Session
            List<User> users3 = dao.findByNameViaSession("Tommy D");
            users3.forEach((u) -> logger.info(() -> "Session: User name: " + u.getName()));
        };
    }
}
