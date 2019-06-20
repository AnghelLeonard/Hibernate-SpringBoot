package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.Optional;
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

    public void fetchAuthorById() {
        Optional<Author> author = authorRepository.findById(1L);

        if (author.isPresent()) {
            getAuthorName(author.get());
        } else {
            System.out.println("Author with id 1 doesn't exist!");
        }
    }

    @Transactional(readOnly = true)
    public void fetchAuthorFromBook() {
        Optional<Book> book = bookRepository.findByTitle("Mastering JSF 2.2");

        if (book.isPresent()) {
            Optional<Author> author = book.get().getAuthor();

            if (author.isPresent()) {
                getAuthorName(author.get());
            } else {
                System.out.println("Author of this book doesn't exist ... hmmm!");
            }
        } else {
            System.out.println("This book 'Mastering JSF 2.2' doesn't exist!");
        }
    }

    private void getAuthorName(Author author) {
        Optional<String> name = author.getName();

        if (name.isPresent()) {
            System.out.println("Author name: " + name.get());
        } else {
            System.out.println("The author was found but he is anonymous!");
        }
    }

}
