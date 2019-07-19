package com.app;

import com.app.repository.NumberRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private static final Logger logger = Logger.getLogger(MainApplication.class.getName());
        
    private static final ExecutorService executor = Executors.newFixedThreadPool(25);

    private final NumberRepository numberRepository;    
    private final DataSource dataSource;

    public MainApplication(NumberRepository numberRepository, DataSource dataSource) {
        this.numberRepository = numberRepository;
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            logger.info("-------------------------------------------------------");
            logger.log(Level.INFO, "DataSource: {0}", dataSource);
            logger.info("-------------------------------------------------------");
            
            while (true) {
                executor.execute(numberRepository);

                Thread.sleep((int) (Math.random() * 250));
            }

        };
    }
}
