package com.app;

import com.app.service.ScreenshotService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final ScreenshotService screenshotService;

    public MainApplication(ScreenshotService screenshotService) {
        this.screenshotService = screenshotService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            screenshotService.saveScreenshotInUTC();
            screenshotService.displayScreenshotInUTC();
        };
    }

}
