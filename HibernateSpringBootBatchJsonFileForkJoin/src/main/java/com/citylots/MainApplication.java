package com.citylots;

import com.citylots.forkjoin.ForkJoinService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    // 'citylots.json' is available in the root of the app as a ZIP archive
    // before running the application, unzip this archive
    
    private static final String FILE_NAME = "citylots.json";

    private final ForkJoinService forkJoinService;

    public MainApplication(ForkJoinService forkJoinService) {
        this.forkJoinService = forkJoinService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            forkJoinService.fileToDatabase(FILE_NAME);
        };
    }
}
