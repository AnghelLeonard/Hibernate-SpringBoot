package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private Author author;

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    public void authorEqualsProxy() {

        // behind findById() we have EntityManager#find()        
        author = authorRepository.findById(1L).orElseThrow();

        // behind getOne() we have EntityManager#getReference()        
        Author proxy = authorRepository.getOne(1L);

        System.out.println("Author class: " + author.getClass().getName());
        System.out.println("Proxy class: " + proxy.getClass().getName());
        System.out.println("'author' equals 'proxy'? " + author.equals(proxy));
    }

    @Transactional(readOnly = true)
    public void authorEqualsUnproxy() {
        
        // behind getOne() we have EntityManager#getReference()        
        Author proxy = authorRepository.getOne(1L);
        
        Object unproxy = Hibernate.unproxy(proxy);
        
        System.out.println("Author class: " + author.getClass().getName());
        System.out.println("Unproxy class: " + unproxy.getClass().getName());
        System.out.println("'author' equals 'unproxy'? " + author.equals(unproxy));          
    }
}
