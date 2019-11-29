package com.bookstore.event;

import com.bookstore.entity.BookReview;
import com.bookstore.entity.ReviewStatus;
import com.bookstore.repository.BookReviewRepository;
import java.util.Random;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class CheckReviewEventHandler {

    private static final Logger logger = Logger.getLogger(CheckReviewEventHandler.class.getName());

    public final BookReviewRepository bookReviewRepository;

    public CheckReviewEventHandler(BookReviewRepository bookReviewRepository) {
        this.bookReviewRepository = bookReviewRepository;
    }

    @Async
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleCheckReviewEvent(CheckReviewEvent event) {

        BookReview bookReview = event.getBookReview();

        logger.info(() -> "Starting checking of review: " + bookReview.getId());

        try {
            // simulate a check out of review grammar, content, acceptance 
            // policies, reviewer email, etc via artificial delay of 5s for demonstration purposes
            String content = bookReview.getContent(); // check content           
            String email = bookReview.getEmail(); // validate email
            Thread.sleep(40000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            // log exception
        }

        if (new Random().nextBoolean()) {
            bookReview.setStatus(ReviewStatus.ACCEPT);
            logger.info(() -> "Book review " + bookReview.getId() + " was accepted ...");
        } else {
            bookReview.setStatus(ReviewStatus.REJECT);
            logger.info(() -> "Book review " + bookReview.getId() + " was rejected ...");
        }

        bookReviewRepository.save(bookReview);

        logger.info(() -> "Checking review " + bookReview.getId() + " done!");
    }
}
