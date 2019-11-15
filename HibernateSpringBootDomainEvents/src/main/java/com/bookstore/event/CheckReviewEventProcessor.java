package com.bookstore.event;

import java.util.Random;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class CheckReviewEventProcessor {

    private static final Logger logger = Logger.getLogger(CheckReviewEventProcessor.class.getName());

    @Async
    @TransactionalEventListener
    public void handleCheckReviewEvent(CheckReviewEvent event) {

        try {
            // simulate a check out of review grammar, content, acceptance policies, etc
            // via artificial delay of 5s for demonstration purposes
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            // log exception
        }

        if (new Random().nextBoolean()) {
            logger.info(() -> "Email sent to " + event.getReviewerEmail()
                    + ": Your review is accepted!");
        } else {
            logger.info(() -> "Email sent to:" + event.getReviewerEmail()
                    + ": Your review is rejected! Consider adding more details.");
        }
    }
}
