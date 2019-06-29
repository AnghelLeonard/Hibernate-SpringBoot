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

    // explicitly delete all records from each table (ignores orphanRemoval = true)
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteAllInBatch() {
        authorRepository.deleteAllInBatch();
        bookRepository.deleteAllInBatch();
    }

    // explicitly delete all records from each table (ignores orphanRemoval = true)
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteInBatch() {
        List<Author> authors = authorRepository.fetchAllAuthorsAndBooks();

        authorRepository.deleteInBatch(authors);
        authors.forEach(a -> bookRepository.deleteInBatch(a.getBooks()));
    }

    // good if you need to delete in a classical batch approach
    // (uses orphanRemoval = true, but generates 20 batches because DELETE statements are not sorted at all)
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteAll() {        
        authorRepository.deleteAll(); // for a collection of certain Authors use deleteAll(Iterable<? extends T> entities)       
    }

    // good if you need to delete in a classical batch approach
    // (uses orphanRemoval = true, and generates only 3 batches)
    @Transactional
    public void deleteAuthorsAndBooksViaDelete() {

        List<Author> authors = authorRepository.fetchAllAuthorsAndBooks();
        
        authors.forEach(Author::removeBooks);
        authorRepository.flush();

        authors.forEach(authorRepository::delete); // or, authorRepository.deleteAll();
    }
}
