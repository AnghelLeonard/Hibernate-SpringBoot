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
public class SingleTableInheritanceApplication {

    private static final Logger logger = Logger.getLogger(SingleTableInheritanceApplication.class.getName());
        
    @Autowired
    private TennisTournamentRepository tennisTournamentRepository;       

    public static void main(String[] args) {
        SpringApplication.run(SingleTableInheritanceApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            Master master = new Master();
            master.setName("Barcelona Tournament");            
            master.setAtpPoints(250);
            master.setSourface("Clay");
            master.setType("ATP");
            master.setMasterAtp("Yes");
            master.setPreGrandSlam("No");
            master.setNoOfPlayers(32);
                        
            tennisTournamentRepository.save(master);
        };
    }
}
