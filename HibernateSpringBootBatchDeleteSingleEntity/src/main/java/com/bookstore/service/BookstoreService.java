package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void persistAuthors() {

        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Author author = new Author();
            author.setName("Name_" + i);
            author.setGenre("Genre_" + i);
            author.setAge((int) ((Math.random() + 0.1) * 100));

            authors.add(author);
        }

        authorRepository.saveAll(authors);
    }

    // good if you want to delete all records    
    public void deleteAuthorsViaDeleteAllInBatch() {
        authorRepository.deleteAllInBatch();
    }

    // good if the number of deletes can be converted into 
    // a DELETE of type, delete from author where id=? or id=? or id=? ...
    // without exceeding the maximum accepted size (as a workaround, 
    // split the number of deletes in multiple chunks to avoid this issue)
    @Transactional
    public void deleteAuthorsViaDeleteInBatch() {

        List<Author> authors = authorRepository.findByAgeLessThan(60);

        authorRepository.deleteInBatch(authors);
    }

    // good if you need to delete in a classical batch approach
    @Transactional
    public void deleteAuthorsViaDeleteAll() {

        List<Author> authors = authorRepository.findByAgeLessThan(60);

        authorRepository.deleteAll(authors); // for deleting all Authors use deleteAll() with no arguments
    }

    // good if you need to delete in a classical batch approach
    @Transactional
    public void deleteAuthorsViaDelete() {

        List<Author> authors = authorRepository.findByAgeLessThan(60);

        authors.forEach(authorRepository::delete);
    }
}
