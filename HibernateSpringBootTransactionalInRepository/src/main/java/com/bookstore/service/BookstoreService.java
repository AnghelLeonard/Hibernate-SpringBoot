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
    public void newAuthor() {
        Author a1 = new Author();
        a1.setName("Joana Nimar");
        a1.setGenre("History");
        a1.setId(4L);
        a1.setAge(34);

        authorRepository.save(a1);
    }

    @Transactional
    public void longRunningServiceMethod() throws InterruptedException {

        System.out.println("Service-method start ...");
        System.out.println("Sleeping before triggering SQL to simulate a long running code ...");
        Thread.sleep(40000);

        Author author = authorRepository.fetchById(4L);
        authorRepository.deleteByName(author.getName());

        System.out.println("Service-method done ...");
    }
}
