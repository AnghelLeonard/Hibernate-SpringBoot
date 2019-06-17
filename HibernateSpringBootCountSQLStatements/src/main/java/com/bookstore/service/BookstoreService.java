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

    public void authorOperationsWithoutTransactional() {
        Author author = new Author();

        author.setName("Mark Janel");
        author.setGenre("Anthology");
        author.setAge(23);

        authorRepository.save(author);   // 1 insert
        author.setGenre("History");
        authorRepository.save(author);   // 1 select, 1 update
        authorRepository.delete(author); // 1 select, 1 delete        
    }

    @Transactional
    public void authorOperationsWithTransactional() {
        Author author = new Author();

        author.setName("Mark Janel");
        author.setGenre("Anthology");
        author.setAge(23);

        authorRepository.save(author);   // 1 insert
        author.setGenre("History");
        authorRepository.save(author);   // update not triggered since a delete follows
        authorRepository.delete(author); // 1 delete        
    }

}
