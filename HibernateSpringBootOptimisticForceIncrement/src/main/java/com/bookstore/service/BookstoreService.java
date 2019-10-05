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

@Service
public class BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());

    private final TransactionTemplate template;
    private final AuhorRepository authorRepository;

    public BookstoreService(AuhorRepository authorRepository, TransactionTemplate template) {
        this.authorRepository = authorRepository;
        this.template = template;
    }

    // running this application will
    // throw org.springframework.orm.ObjectOptimisticLockingFailureException
    public void addBooksViaTwoTransactions() {
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
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

        System.out.println("Done!");
    }
}
