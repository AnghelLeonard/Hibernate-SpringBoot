package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import java.util.logging.Logger;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private static final Logger logger
            = Logger.getLogger(BookstoreService.class.getName());

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void registerAuthor() {

        Author a1 = new Author();
        a1.setName("Quartis Young");
        a1.setGenre("Anthology");
        a1.setAge(34);

        Author a2 = new Author();
        a2.setName("Mark Janel");
        a2.setGenre("Anthology");
        a2.setAge(23);

        Book b1 = new Book();
        b1.setIsbn("001");
        b1.setTitle("The Beatles Anthology");        

        Book b2 = new Book();
        b2.setIsbn("002");
        b2.setTitle("A People's Anthology");

        Book b3 = new Book();
        b3.setIsbn("003");
        b3.setTitle("Anthology Myths");

        a1.addBook(b1);
        a1.addBook(b2);
        a2.addBook(b3);

        authorRepository.save(a1);
        authorRepository.save(a2);
    }

    @Transactional
    public void updateAuthor() {
        Author author = authorRepository.findByName("Mark Janel");
        
        author.setAge(45);
    }

    @Transactional
    public void updateBooks() {
        Author author = authorRepository.findByName("Quartis Young");
        List<Book> books = author.getBooks();
        
        for(Book book: books) {
            book.setIsbn("not available");
        }
    }
}
