package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.AuthorId;
import com.bookstore.entity.Book;
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
    public void addAuthorWithBooks() {

        Author author = new Author();
        author.setId(new AuthorId("Alicia Tom", 38));
        author.setGenre("Anthology");

        Book book1 = new Book();
        book1.setIsbn("001-AT");
        book1.setTitle("The book of swords");

        Book book2 = new Book();
        book2.setIsbn("002-AT");
        book2.setTitle("Anthology of a day");

        Book book3 = new Book();
        book3.setIsbn("003-AT");
        book3.setTitle("Anthology today");

        author.addBook(book1); // use addBook() helper
        author.addBook(book2);

        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByName() {
        Author author = authorRepository.fetchByName("Alicia Tom");
        
        System.out.println(author);
    }
    
    @Transactional
    public void removeBookOfAuthor() {

        Author author = authorRepository.fetchWithBooks(new AuthorId("Alicia Tom", 38));

        author.removeBook(author.getBooks().get(0));
    }

    @Transactional
    public void removeAuthor() {
        authorRepository.deleteById(new AuthorId("Alicia Tom", 38));
    }

}
