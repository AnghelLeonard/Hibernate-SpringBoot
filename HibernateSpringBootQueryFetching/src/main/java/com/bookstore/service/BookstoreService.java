package com.bookstore.service;

import com.bookstore.dao.Dao;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final Dao dao;

    public BookstoreService(AuthorRepository authorRepository, Dao dao) {
        this.authorRepository = authorRepository;
        this.dao = dao;
    }

    public void queries() {
        // Query via JpaRepository (Spring Data Query Creation)
        Author author1 = authorRepository.findByName("Joana Nimar");
        System.out.println(author1);
 
        // Query via JpaRepository (@Query)
        Author author2 = authorRepository.fetchByName("Joana Nimar");
        System.out.println(author2);

        // Query via EntityManager
        Author author3 = (Author) dao.findByName("Joana Nimar");
        System.out.println(author3);

        // Query via Hibernate Session
        Author author4 = (Author) dao.findByNameViaSession("Joana Nimar");
        System.out.println(author4);
    }
}
