package com.jpa;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class C3P0Application {

    private static final Logger logger = Logger.getLogger(C3P0Application.class.getName());   
    
    private static final ExecutorService executor = Executors.newFixedThreadPool(25);

    @Autowired
    private ApplicationContext applicationContext;    
    
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(C3P0Application.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            logger.info("-------------------------------------------------------");
            logger.log(Level.INFO, "DataSource: {0}", dataSource);
            logger.info("-------------------------------------------------------");
            
            while(true) {
                SampleRepository sampleThread
                        = applicationContext.getBean(SampleRepository.class);
                executor.execute(sampleThread);
                
                Thread.sleep(new Random().nextInt(125));
            }

        };
    }
}
