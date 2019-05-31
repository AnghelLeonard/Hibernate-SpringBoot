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
public class MainApplication {

    private static final Logger logger = Logger.getLogger(MainApplication.class.getName());
        
    @Autowired
    private UserGoodRepository userGoodRepository;
    
    @Autowired
    private UserBadRepository userBadRepository;
        
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            UserGood userGood = new UserGood();
            userGood.setName("Good User");
            userGood.setCity("Good City");
            userGood.setAge(20);
            
            userGoodRepository.save(userGood);
            
            UserBad userBad = new UserBad();
            userBad.setName("Bad User");
            userBad.setCity("Bad City");
            userBad.setAge(20);
            
            userBadRepository.save(userBad);
        };
    }
}
