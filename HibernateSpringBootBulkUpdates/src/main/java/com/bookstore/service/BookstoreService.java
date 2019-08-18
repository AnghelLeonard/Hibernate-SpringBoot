package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void persistAuthorsAndBooks() {

        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < 40; i++) {

            Author author = new Author();
            author.setName("Name_" + i);
            author.setGenre("Genre_" + i);
            author.setAge(18 + i);

            for (int j = 0; j < 5; j++) {
                Book book = new Book();
                book.setTitle("Title: " + j);
                book.setIsbn("Isbn: " + j);

                author.addBook(book);
            }

            authors.add(author);
        }

        authorRepository.saveAll(authors);
    }

    @Transactional
    public void updateAuthorsAndBooks() {

        authorRepository.updateInBulk();
        bookRepository.updateInBulk();
    }    
    
    @Transactional
    public void updateAuthorsGtAgeAndBooks() {

        List<Author> authors = authorRepository.findGtGivenAge(40);
        
        authorRepository.updateInBulk(authors);
        bookRepository.updateInBulk(authors);
    } 
}
