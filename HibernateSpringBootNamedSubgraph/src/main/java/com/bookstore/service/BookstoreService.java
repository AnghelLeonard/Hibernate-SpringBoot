package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }
    
    public void displayAuthorsAndBooks() {

        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            System.out.println("\nAuthor: " + author);
            
            List<Book> books = author.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for(Book book : books) {
                System.out.println("Book: " + book.getTitle() + " published by: " + book.getPublisher());
            }
        }
    }
}
