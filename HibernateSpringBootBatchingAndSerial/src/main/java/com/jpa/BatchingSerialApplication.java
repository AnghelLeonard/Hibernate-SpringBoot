package com.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BatchingSerialApplication {

    private static final Logger logger = Logger.getLogger(BatchingSerialApplication.class.getName());

    @Autowired
    private TennisPlayerRepository tennisPlayerRepository;

    @Autowired
    private Dao dao;

    public static void main(String[] args) {
        SpringApplication.run(BatchingSerialApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            TennisPlayer player_1 = new TennisPlayer();           
            player_1.setName("Jacky Francisco");
            player_1.setCity("Banesti");
            player_1.setAge(24);

            TennisPlayer player_2 = new TennisPlayer();            
            player_2.setName("Caludiu George");
            player_2.setCity("Brasov");
            player_2.setAge(31);

            TennisPlayer player_3 = new TennisPlayer();            
            player_3.setName("Marius Botocoala");
            player_3.setCity("Sibiu");
            player_3.setAge(22);

            TennisPlayer player_4 = new TennisPlayer();            
            player_4.setName("Serban D");
            player_4.setCity("Constanta");
            player_4.setAge(52);

            TennisPlayer player_5 = new TennisPlayer();            
            player_5.setName("Marina Acoolea");
            player_5.setCity("Brasov");
            player_5.setAge(22);

            TennisPlayer player_6 = new TennisPlayer();            
            player_6.setName("Tayler Durden");
            player_6.setCity("Dallas");
            player_6.setAge(20);

            TennisPlayer player_7 = new TennisPlayer();            
            player_7.setName("Andy T");
            player_7.setCity("Mures");
            player_7.setAge(54);

            List<TennisPlayer> players
                    = Arrays.asList(player_1, player_2, player_3, player_4, player_5, player_6, player_7);
            
            tennisPlayerRepository.saveAll(players);
        };
    }
}
