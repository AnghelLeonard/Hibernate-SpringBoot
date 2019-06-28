package com.app;

import com.app.service.BatchService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BatchService batchService;

    public MainApplication(BatchService batchService) {
        this.batchService = batchService;
    }

    private static final String FILE_NAME = "citylots.json";

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            batchService.batchFile(FILE_NAME);
        };
    }
}
