package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
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

    public void pessimisticWriteUpdate() throws InterruptedException {
        Thread tA = new Thread(() -> {
            template.setPropagationBehavior(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            
            template.execute(new TransactionCallbackWithoutResult() {

                @Override
                protected void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    log.info("Starting first transaction ...");

                    Author author = authorRepository.findById(1L).orElseThrow();

                    try {
                        log.info("Locking for 10s ...");
                        Thread.sleep(10000);
                        log.info("Releasing lock ...");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            log.info("First transaction committed!");
        });

        Thread tB = new Thread(() -> {
            template.setPropagationBehavior(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            template.setTimeout(15); // 15 seconds

            template.execute(new TransactionCallbackWithoutResult() {

                @Override
                protected void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    log.info("Starting second transaction ...");

                    authorRepository.updateGenre("Horror", 1L);
                }
            });

            log.info("Second transaction committed!");
        });
        
        tA.start();
        Thread.sleep(2000);
        tB.start();
        
        tA.join();
        tB.join();
    }
    
    public void pessimisticWriteInsert(int isolationLevel) throws InterruptedException {
        Thread tA = new Thread(() -> {
            template.setPropagationBehavior(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            template.setIsolationLevel(isolationLevel);
            
            template.execute(new TransactionCallbackWithoutResult() {

                @Override
                protected void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    log.info("Starting first transaction ...");

                    List<Author> authors = authorRepository.findByAgeBetween(40, 50);

                    try {
                        log.info("Locking for 10s ...");
                        Thread.sleep(10000);
                        log.info("Releasing lock ...");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            log.info("First transaction committed!");
        });

        Thread tB = new Thread(() -> {
            template.setPropagationBehavior(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            template.setTimeout(15); // 15 seconds

            template.execute(new TransactionCallbackWithoutResult() {

                @Override
                protected void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    log.info("Starting second transaction ...");

                    Author author = new Author();
                    author.setAge(43);
                    author.setName("Joel Bornis");
                    author.setGenre("Anthology");
                    
                    authorRepository.saveAndFlush(author);            
                }
            });

            log.info("Second transaction committed!");
        });
        
        tA.start();
        Thread.sleep(2000);
        tB.start();
        
        tA.join();
        tB.join();
    }
    
    public void pessimisticWriteDelete() throws InterruptedException {
        Thread tA = new Thread(() -> {
            template.setPropagationBehavior(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            
            template.execute(new TransactionCallbackWithoutResult() {

                @Override
                protected void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    log.info("Starting first transaction ...");

                    Author author = authorRepository.findById(1L).orElseThrow();

                    try {
                        log.info("Locking for 10s ...");
                        Thread.sleep(10000);
                        log.info("Releasing lock ...");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }                  
                }
            });

            log.info("First transaction comitted!");
        });

        Thread tB = new Thread(() -> {
            template.setPropagationBehavior(
                    TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            template.setTimeout(15); // 15 seconds

            template.execute(new TransactionCallbackWithoutResult() {

                @Override
                protected void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    log.info("Starting second transaction ...");

                    authorRepository.deleteById(1L);                    
                }
            });

            log.info("Second transaction comitted!");
        });
        
        tA.start();
        Thread.sleep(2000);
        tB.start();
        
        tA.join();
        tB.join();
    }
}
