package com.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class LimitResultSizeApplication {

    private static final Logger logger = Logger.getLogger(LimitResultSizeApplication.class.getName());

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LimitResultSizeApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            logger.log(Level.INFO, "Find top 5 by age equal to 17: {0}",
                    userRepository.findTop5ByAge(17));

            logger.log(Level.INFO, "Find first 5 by age equal with 17 sorted by name: {0}",
                    userRepository.findFirst5ByAge(17, new Sort(Sort.Direction.ASC, "name")));

            logger.log(Level.INFO, "Find top 3 by age greater than or equal to 17: {0}",
                    userRepository.findTop3ByAgeGreaterThanEqual(17));

            logger.log(Level.INFO, "Find first 3 by age greater than 17 sorted by name: {0}",
                    userRepository.findFirst3ByAgeGreaterThan(17, new Sort(Sort.Direction.ASC, "name")));

            logger.log(Level.INFO, "Find first 5 by age desc: {0}",
                    userRepository.findFirst5ByOrderByAgeDesc());

            logger.log(Level.INFO, "Find top 3 by age desc: {0}",
                    userRepository.findTop3ByOrderByAgeDesc());
        };
    }
}
