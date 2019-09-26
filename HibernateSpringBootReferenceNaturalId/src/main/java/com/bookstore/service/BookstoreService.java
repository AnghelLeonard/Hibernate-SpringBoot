package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.BookRepository;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookstoreService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void persistAuthorsAndBooks() {

        Author at = new Author();
        at.setName("Alicia Tom");
        at.setGenre("Anthology");
        at.setAge(38);
        at.setEmail("alicia.tom@gmail.com");
        
        Author jn = new Author();
        jn.setName("Joana Nimar");
        jn.setGenre("History");
        jn.setAge(34);
        jn.setEmail("joana.nimar@gmail.com");

        Book atb1 = new Book();
        atb1.setTitle("Anthology of a day");
        atb1.setIsbn("AT-001");

        Book atb2 = new Book();
        atb2.setTitle("Anthology gaps");
        atb2.setIsbn("AT-002");
        
        Book jnb1 = new Book();
        jnb1.setTitle("History of Prague");
        jnb1.setIsbn("JN-001");

        atb1.setAuthor(at);
        atb2.setAuthor(at);
        jnb1.setAuthor(jn);

        authorRepository.save(at);
        authorRepository.save(jn);
        bookRepository.save(atb1);
        bookRepository.save(atb2);
        bookRepository.save(jnb1);
    }

    @Transactional(readOnly = true)
    public void fetchBookWithAuthor() {

        Book book = bookRepository.findByTitle("Anthology gaps");
        Author author = book.getAuthor();
        
        System.out.println(book);
        System.out.println(author);
    }
}
