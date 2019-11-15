package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // adjust the Query Plan Cache size from application.properties
    public void fetchAuthorsbyGenre() {

        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 1; i <= 5000; i++) {
            List<Author> authorsByGenre = authorRepository.fetchByGenre("Anthology");            
            List<Author> authorsByAge = authorRepository.fetchByAge(40);
        }
        sw.stop();
        
        System.out.println("Elapsed time: " + sw.getTotalTimeMillis() + " ms");
    }
}
