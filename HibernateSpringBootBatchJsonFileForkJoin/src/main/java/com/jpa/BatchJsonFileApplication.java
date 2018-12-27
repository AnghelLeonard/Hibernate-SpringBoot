package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BatchJsonFileApplication {

    @Autowired
    private TransferService transferService;
    
    private static final String FILE_NAME = "citylots.json";   

    public static void main(String[] args) {
        SpringApplication.run(BatchJsonFileApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            transferService.transferFile(FILE_NAME);
        };
    }
}
