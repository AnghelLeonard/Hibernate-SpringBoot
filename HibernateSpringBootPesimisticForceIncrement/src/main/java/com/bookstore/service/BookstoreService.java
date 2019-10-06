package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.bookstore.repository.AuhorRepository;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

@Service
public class BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());

    private final TransactionTemplate template;
    private final AuhorRepository authorRepository;
    private final EntityManager entityManager;

    public BookstoreService(AuhorRepository authorRepository,
            TransactionTemplate template, EntityManager entityManager) {
        this.authorRepository = authorRepository;
        this.template = template;
        this.entityManager = entityManager;
    }

    // For all dialects (MySQL5Dialect, MySQL5InnoDBDialect, MySQL8Dialect)            
    // running the below method will throw: javax.persistence.OptimisticLockException
    // Caused by: org.hibernate.StaleObjectStateException:
    public void addBooksViaTwoTransactionsTestVersion() {
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        template.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                log.info("Starting first transaction (no physical or logical lock) ...");

                Author author = authorRepository.findByName("Mark Janel");

                template.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {

                        log.info("Starting second transaction ...");

                        Author author = authorRepository.findById(1L).orElseThrow();

                        Book book = new Book();
                        book.setTitle("The Beatles Anthology");
                        book.setIsbn("001-MJ");
                        author.addBook(book);

                        authorRepository.save(author);

                        log.info("Commit second transaction ...");
                    }
                });

                log.info("First transaction attempts to acquire a "
                        + "PESSIMISTIC_FORCE_INCREMENT on the existing `author` entity");
                entityManager.lock(author, LockModeType.PESSIMISTIC_FORCE_INCREMENT);

                Book book = new Book();
                book.setTitle("The Beatles Anthology");
                book.setIsbn("001-MJ");
                author.addBook(book);

                authorRepository.save(author);

                log.info("Commit first transaction ...");
            }
        });

        log.info("Done!");
    }

    // For MySQL5Dialect (MyISAM) stoare engine): row-level locking not supported
    // For MySQL5InnoDBDialect (InnoDB storage engine): row-level locking is aquired via FOR UPDATE
    // For MySQL8Dialect (InnoDB storage engine): row-level locking is aquired via FOR UPDATE NOWAIT
    // running the below method will throw: org.hibernate.QueryTimeoutException
    // Caused by: com.mysql.cj.jdbc.exceptions.MySQLTimeoutException
    public void addBooksViaTwoTransactionsTestLocking() {
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.setTimeout(3); // 3 seconds

        template.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                log.info("Starting first transaction ...");

                Author author = authorRepository.findById(1L).orElseThrow();

                Book book = new Book();
                book.setTitle("The Beatles Anthology");
                book.setIsbn("001-MJ");
                author.addBook(book);

                template.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {

                        log.info("Starting second transaction ...");

                        Author author = authorRepository.findById(1L).orElseThrow();

                        Book book = new Book();
                        book.setTitle("The Beatles Anthology");
                        book.setIsbn("001-MJ");
                        author.addBook(book);

                        authorRepository.save(author);

                        log.info("Commit second transaction ...");
                    }
                });

                authorRepository.save(author);

                log.info("Commit first transaction ...");
            }
        });

        log.info("Done!");
    }
}
