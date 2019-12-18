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
    public void longRunningServiceMethod() throws InterruptedException {

        System.out.println("Service-method start ...");
        System.out.println("Sleeping before triggering SQL to simulate a long running code ...");
        Thread.sleep(40000);

        Author author = authorRepository.fetchByName("Joana Nimar");
        authorRepository.deleteByNeGenre(author.getGenre());

        System.out.println("Service-method done ...");
    }

    @Transactional
    public void newAuthors() {

        Author a1 = new Author();
        a1.setName("Mark Janel");
        a1.setGenre("Anthology");
        a1.setId(1L);
        a1.setAge(23);

        Author a2 = new Author();
        a2.setName("Olivia Goy");
        a2.setGenre("Anthology");
        a2.setId(2L);
        a2.setAge(43);

        Author a3 = new Author();
        a3.setName("Quartis Young");
        a3.setGenre("Anthology");
        a3.setId(3L);
        a3.setAge(51);

        Author a4 = new Author();
        a4.setName("Joana Nimar");
        a4.setGenre("History");
        a4.setId(4L);
        a4.setAge(34);

        authorRepository.save(a1);
        authorRepository.save(a2);
        authorRepository.save(a3);
        authorRepository.save(a4);
    }
}
