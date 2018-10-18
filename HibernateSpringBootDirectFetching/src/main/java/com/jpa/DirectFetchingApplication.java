package com.jpa;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DirectFetchingApplication {

    private static final Logger logger = Logger.getLogger(DirectFetchingApplication.class.getName());
        
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Dao dao;

    public static void main(String[] args) {
        SpringApplication.run(DirectFetchingApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            User user = new User();
            user.setId(1L);
            user.setName("Jacky Francisco");
            user.setCity("Banesti");
            user.setAge(24);
            
            userRepository.save(user);
            
            // direct fetching via Spring Data
            Optional<User> resultSD = userRepository.findById(1L);
            logger.log(Level.INFO, "Direct fetching via Spring Data result: {0}", resultSD.get());
            
            // direct fetching via EntityManager
            Optional<User> resultEM = dao.find(User.class, 1L);
            logger.log(Level.INFO, "Direct fetching via EntityManager result: {0}", resultEM.get());
            
            // direct fetching via Session
            Optional<User> resultHS = dao.findViaSession(User.class, 1L);
            logger.log(Level.INFO, "Direct fetching via Session result: {0}", resultHS.get());
        };
    }
}
