package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());

    private final TransactionTemplate template;
    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository, TransactionTemplate template) {
        this.authorRepository = authorRepository;
        this.template = template;
    }

    public void pessimisticReadWrite() {

        template.setPropagationBehavior(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.setTimeout(3); // 3 seconds

        template.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(
                    TransactionStatus status) {

                log.info("Starting first transaction ...");

                Author author = authorRepository.findById(1L).orElseThrow();

                template.execute(new TransactionCallbackWithoutResult() {

                    @Override
                    protected void doInTransactionWithoutResult(
                            TransactionStatus status) {

                        log.info("Starting second transaction ...");

                        Author author = authorRepository.findById(1L).orElseThrow();
                        author.setGenre("Horror");                        

                        log.info("Commit second transaction ...");
                    }
                });

                log.info("Resuming first transaction ...");                
                log.info("Commit first transaction ...");
            }
        });

        log.info("Done!");
    }
}
