package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final TransactionTemplate template;

    public BookstoreService(AuthorRepository authorRepository, TransactionTemplate template) {
        this.authorRepository = authorRepository;
        this.template = template;
    }

    public void process() {

        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        // We use MySQL 8 InnoDB.
        // The default isolation level for InnoDB is REPEATABLE READ, therefore
        // we need to switch to READ_COMMITTED to reveal how Hibernate session-level
        // repeatable reads works
        template.setIsolationLevel(Isolation.READ_COMMITTED.value());
        
        // Transaction A
        template.execute(new TransactionCallbackWithoutResult() {                                   
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                
                Author authorA1 = authorRepository.findById(1L).orElseThrow();
                System.out.println("Author A1: " + authorA1.getName() + "\n");
                
                // Transaction B
                template.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {

                        Author authorB = authorRepository.findById(1L).orElseThrow();
                        authorB.setName("Alicia Tom");
                        System.out.println("Author B: " + authorB.getName() + "\n");
                    }
                });
                
                // Direct fetching via findById(), find() and get() doesn't trigger a SELECT
                // It loads the author directly from Persistence Context
                Author authorA2 = authorRepository.findById(1L).orElseThrow();
                System.out.println("\nAuthor A2: " + authorA2.getName() + "\n");
                
                // JPQL entity queries take advantage of session-level repeatable reads
                // The data snapshot returned by the triggered SELECT is ignored
                Author authorViaJpql = authorRepository.fetchByIdJpql(1L);
                System.out.println("Author via JPQL: " + authorViaJpql.getName() + "\n");
                
                // SQL entity queries take advantage of session-level repeatable reads
                // The data snapshot returned by the triggered SELECT is ignored
                Author authorViaSql = authorRepository.fetchByIdSql(1L);
                System.out.println("Author via SQL: " + authorViaSql.getName() + "\n");
                
                // JPQL query projections always load the latest database state
                String nameViaJpql = authorRepository.fetchNameByIdJpql(1L);
                System.out.println("Author name via JPQL: " + nameViaJpql + "\n");
                
                // SQL query projections always load the latest database state
                String nameViaSql = authorRepository.fetchNameByIdSql(1L);
                System.out.println("Author name via SQL: " + nameViaSql + "\n");
            }
        });
    }
}
