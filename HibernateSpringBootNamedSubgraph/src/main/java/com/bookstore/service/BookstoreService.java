package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Publisher;
import com.bookstore.repository.PublisherRepository;
import static com.bookstore.specs.AuthorSpecs.isAgeGt45;
import static com.bookstore.specs.PublisherSpecs.isIdGt2;
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

    public void displayAuthorsByAgeWithBooksAndPublishers(int age) {

        List<Author> authors = authorRepository.findByAgeLessThanOrderByNameDesc(age);

        for (Author author : authors) {
            System.out.println("\nAuthor: " + author);

            List<Book> books = author.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " published by: " + book.getPublisher());
            }
        }
    }

    public void displayAuthorsByAgeBetween20And40WithBooksAndPublishers() {

        List<Author> authors = authorRepository.fetchAllAgeBetween20And40();

        for (Author author : authors) {
            System.out.println("\nAuthor: " + author);

            List<Book> books = author.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " published by: " + book.getPublisher());
            }
        }
    }

    public void displayAuthorsWithBooksAndPublishersWithSpec() {

        List<Author> authors = authorRepository.findAll(isAgeGt45());

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

    public void displayPublishersByIdWithBooksAndAuthors() {

        List<Publisher> publishers = publisherRepository.findByIdLessThanOrderByCompanyDesc(2L);

        for (Publisher publisher : publishers) {
            System.out.println("\nPublisher: " + publisher);

            List<Book> books = publisher.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " written by: " + book.getAuthor());
            }
        }
    }

    public void displayPublishersWithBooksAndAuthorsWithSpec() {

        List<Publisher> publishers = publisherRepository.findAll(isIdGt2());

        for (Publisher publisher : publishers) {
            System.out.println("\nPublisher: " + publisher);

            List<Book> books = publisher.getBooks();
            System.out.println("No of books: " + books.size() + ", " + books);
            for (Book book : books) {
                System.out.println("Book: " + book.getTitle() + " written by: " + book.getAuthor());
            }
        }
    }

    public void displayPublishersByIdBetween1And3WithBooksAndAuthors() {

        List<Publisher> publishers = publisherRepository.fetchAllIdBetween1And3();

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
