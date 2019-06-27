package com.app.service;

import com.app.entity.Screenshot;
import com.app.repository.ScreenshotRepository;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import org.springframework.stereotype.Service;

@Service
public class ScreenshotService {

    private final ScreenshotRepository screenshotRepository;

    public ScreenshotService(ScreenshotRepository screenshotRepository) {
        this.screenshotRepository = screenshotRepository;
    }

    public void saveScreenshotInUTC() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));

        Screenshot screenshot = new Screenshot();

        screenshot.setName("Screenshot-1");
        screenshot.setCreateOn(new Timestamp(
                ZonedDateTime.of(2018, 3, 30, 10, 15, 55, 0,
                        ZoneId.of("UTC")
                ).toInstant().toEpochMilli()
        ));

        System.out.println("Timestamp epoch milliseconds before insert: "
                + screenshot.getCreateOn().getTime());

        screenshotRepository.save(screenshot);
    }

    public void displayScreenshotInUTC() {
        Screenshot fetchScreenshot = screenshotRepository.findByName("Screenshot-1");
        System.out.println("Timestamp epoch milliseconds after fetching: "
                + fetchScreenshot.getCreateOn().getTime());
    }
}
