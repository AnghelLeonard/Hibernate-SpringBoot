package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public Author fetchAuthorWithBooks() {
        Author author = authorRepository.findByName("Joana Nimar");
        List<Book> books = author.getBooks();
        
        System.out.println(books);

        return author;
    }
    
    public Author fetchAuthorWithoutBooks() {
        Author author = authorRepository.findByName("Joana Nimar");

        return author;
    }
}
