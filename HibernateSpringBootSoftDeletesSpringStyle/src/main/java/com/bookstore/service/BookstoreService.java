package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void testSoftDeletes() {
        
        System.out.println("Authors: " + authorRepository.count());
        System.out.println("Books: " + bookRepository.count());
        System.out.println("Deleted Authors: " + authorRepository.countDeleted());
        System.out.println("Deleted Books: " + bookRepository.countDeleted());
        
        Author author = authorRepository.fetchByName("Mark Janel"); // id 1
        authorRepository.delete(author);
        
        Book book = bookRepository.fetchByTitle("Carrie"); // id 4
        bookRepository.delete(book);
        
        System.out.println("Authors: " + authorRepository.count());
        System.out.println("Books: " + bookRepository.count());
        System.out.println("Deleted Authors: " + authorRepository.countDeleted());
        System.out.println("Deleted Books: " + bookRepository.countDeleted()); 
        
        authorRepository.undeleteById(1L);
        bookRepository.undeleteById(4L);
        
        System.out.println("Authors: " + authorRepository.count());
        System.out.println("Books: " + bookRepository.count());
        System.out.println("Deleted Authors: " + authorRepository.countDeleted());
        System.out.println("Deleted Books: " + bookRepository.countDeleted()); 
    }

}
