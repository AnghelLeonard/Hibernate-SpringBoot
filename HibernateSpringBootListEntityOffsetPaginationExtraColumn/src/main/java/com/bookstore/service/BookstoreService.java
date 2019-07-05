package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchNextPage(int page, int size) {

        return authorRepository.fetchAll(page, size);
    }

    /* // this relies in Author.toString() 
       // using this method requires the controller to return Map<List<Author>, Long> as well
    public Map<List<Author>, Long> fetchNextPage(int page, int size) {

        List<Author> authors = authorRepository.fetchAll(page, size);
        
        return Collections.singletonMap(authors, authors.isEmpty() ? 0 : authors.get(0).getTotal());
    }
     */
}
