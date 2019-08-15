package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import java.util.Collections;
import java.util.List;
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

    @Transactional
    public void deleteViaCascadeRemove() {
        Author author = authorRepository.findByName("Joana Nimar");

        authorRepository.delete(author);
    }

    @Transactional
    public void deleteViaOrphanRemoval() {
        Author author = authorRepository.findByNameWithBooks("Joana Nimar");

        author.removeBooks();
        authorRepository.delete(author);
    }

    @Transactional
    public void deleteViaIdentifiers() {
        Author author = authorRepository.findByName("Joana Nimar");

        bookRepository.deleteByAuthorIdentifier(author.getId());
        authorRepository.deleteByIdentifier(author.getId());
    }

    @Transactional
    public void deleteViaBulkIn() {
        List<Author> authors = authorRepository.findByAge(34);

        bookRepository.deleteBulkByAuthors(authors);
        authorRepository.deleteInBatch(authors);
    }

    @Transactional
    public void deleteViaDeleteInBatch() {
        Author author = authorRepository.findByNameWithBooks("Joana Nimar");

        bookRepository.deleteInBatch(author.getBooks());
        authorRepository.deleteByIdentifier(author.getId());
    }

    @Transactional
    public void deleteViaHardCodedIdentifiers() {
        bookRepository.deleteByAuthorIdentifier(4L);
        authorRepository.deleteByIdentifier(4L);
    }
}
