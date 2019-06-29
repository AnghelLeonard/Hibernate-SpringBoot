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

        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            author.setAge(author.getAge() + 1);
            for (Book book : author.getBooks()) {
                book.setIsbn(book.getIsbn() + "-2020");
            }
        }
    }

    @Transactional
    public void updateBooksAndAuthors() {
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            book.setIsbn(book.getIsbn() + "-2021");

            Author author = book.getAuthor();
            author.setAge(author.getAge() + 1);
        }
    }
}
