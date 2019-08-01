package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Publisher;
import com.bookstore.repository.PublisherRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookstoreService(AuthorRepository authorRepository, PublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public void displayAuthorsWithBooksAndPublishers() {

        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            System.out.println("\nAuthor: " + author);

            List<Book> books = author.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " published by: " + book.getPublisher());
            }
        }
    }
    
    public void displayAuthorsWithBooksAndPublishersFetchAll() {

        List<Author> authors = authorRepository.fetchAll();

        for (Author author : authors) {
            System.out.println("\nAuthor: " + author);

            List<Book> books = author.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " published by: " + book.getPublisher());
            }
        }
    }
    
    public void displayPublishersWithBooksAndAuthors() {

        List<Publisher> publishers = publisherRepository.findAll();

        for (Publisher publisher : publishers) {
            System.out.println("\nPublisher: " + publisher);

            List<Book> books = publisher.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " written by: " + book.getAuthor());
            }
        }
    }
}
