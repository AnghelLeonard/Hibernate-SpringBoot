package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class BookstoreService {

    private static final Logger logger
            = Logger.getLogger(BookstoreService.class.getName());

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void updateAuthor() {

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                logger.info(() -> "Long running task right after commit ...");
                // Right after commit do other stuff but
                // keep in mind that the connection will not
                // return to pool connection until this code is done
                // So, avoid time-consuming tasks here                 
                try {
                    // This sleep() is just proof that the connection is not released
                    // Check HikariCP log ()
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    logger.severe(() -> "Exception: " + ex);
                }
                logger.info(() -> "Long running task done ...");
            }
        });

        logger.info(() -> "Update the author age and commit ...");
        Author author = authorRepository.findById(1L).get();

        author.setAge(40);
    }
}
