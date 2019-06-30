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

    public void batchAuthorsAndBooks() {

        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Author author = new Author();
            author.setName("Name_" + i);
            author.setGenre("Genre_" + i);
            author.setAge((int) ((Math.random() + 0.1) * 100));

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

    // explicitly delete all records from each table
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteAllInBatch() {
        authorRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
    }

    // explicitly delete all records from each table
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteInBatch() {
        List<Author> authors = authorRepository.fetchAuthorsAndBooks(60);

        authorRepository.deleteInBatch(authors);
        authors.forEach(a -> bookRepository.deleteInBatch(a.getBooks()));
    }

    // good if you need to delete in a classical batch approach
    // deletes are cascaded by CascadeType.REMOVE and batched as well
    // the DELETE statements are not sorted at all and this causes more batches than needed for this job
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteAll() {
        List<Author> authors = authorRepository.fetchAuthorsAndBooks(60);
        
        authorRepository.deleteAll(authors); // for deleting all Authors use deleteAll()       
    }

    // good if you need to delete in a classical batch approach
    // (uses orphanRemoval = true, and optimize the number of batches)
    @Transactional
    public void deleteAuthorsAndBooksViaDelete() {

        List<Author> authors = authorRepository.fetchAuthorsAndBooks(60);

        authors.forEach(Author::removeBooks);
        authorRepository.flush();

        authors.forEach(authorRepository::delete); // or, authorRepository.deleteAll(authors);
    }
}
