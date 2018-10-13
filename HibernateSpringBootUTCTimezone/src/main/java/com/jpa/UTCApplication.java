package com.jpa;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UTCApplication {

    @Autowired
    private ScreenshotRepository screenshotRepository;
    
    private static final Logger logger
            = LogManager.getLogger(UTCApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UTCApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            long rnd = new Random().nextLong();
            TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));

            Screenshot screenshot = new Screenshot();

            screenshot.setName("Screenshot-" + rnd);
            screenshot.setCreateOn(new Timestamp(
                    ZonedDateTime.of(2018, 3, 30, 10, 15, 55, 0,
                            ZoneId.of("UTC")
                    ).toInstant().toEpochMilli()
            ));

            logger.info(() -> "Timestamp epoch milliseconds before insert: "
                    + screenshot.getCreateOn().getTime());

            screenshotRepository.save(screenshot);

            Screenshot fetchScreenshot = screenshotRepository.findByName("Screenshot-" + rnd);           
            logger.info(() -> "Timestamp epoch milliseconds after fetching: " 
                    + fetchScreenshot.getCreateOn().getTime());
        };
    }

}