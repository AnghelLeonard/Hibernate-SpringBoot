package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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

    // deleting the authors will delete the books as well
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteAllInBatch() {
        authorRepository.deleteAllInBatch();
    }

    // deleting the authors will delete the books as well
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteInBatch() {
        List<Author> authors = authorRepository.findAll();

        authorRepository.deleteInBatch(authors);
    }

    // good if you need to delete in a classical batch approach
    // the authors will be deleted in batches; the books will be deleted as well
    @Transactional
    public void deleteAuthorsAndBooksViaDeleteAll() {
        authorRepository.deleteAll(); // for a collection of certain Authors use deleteAll(Iterable<? extends T> entities)       
    }

    // good if you need to delete in a classical batch approach
    // the authors will be deleted in batches; the books will be deleted as well
    @Transactional
    public void deleteAuthorsAndBooksViaDelete() {

        List<Author> authors = authorRepository.findAll();
        authors.forEach(authorRepository::delete);
    }
}
