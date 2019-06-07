package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void doTimeConsumingTask() throws InterruptedException {

        System.out.println("Waiting for a time-consuming task that doesn't need a database connection ...");

        // we use a sleep of 40 seconds just to capture HikariCP logging status
        // which take place at every 30 seconds - this will reveal if the connection
        // was opened (acquired from the connection pool) or not
        Thread.sleep(40000);

        System.out.println("Done, now query the database ...");
        System.out.println("The database connection should be acquired now ...");

        Author author = authorRepository.findById(1L).get();
        
        // at this point, the connection should be open
        Thread.sleep(40000);
        
        author.setAge(44);
    }

}
