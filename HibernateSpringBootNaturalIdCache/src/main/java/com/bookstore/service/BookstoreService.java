package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void persistTwoBooks() {

        Book ar = new Book();
        ar.setIsbn("001-AR");
        ar.setTitle("Ancient Rome");
        ar.setPrice(25);        

        Book rh = new Book();
        rh.setIsbn("001-RH");
        rh.setTitle("Rush Hour");
        rh.setPrice(31);        

        bookRepository.save(ar);
        bookRepository.save(rh);
    }

    public Book fetchFirstBookByNaturalId() {

        // find the first book by a single natural id, transaction 1
        // - the natural id, 001-AR, is fetched from Second Level Cache
        // - the Book entity is fetched from database and place in Second Level Cache
        Optional<Book> foundArBook
                = bookRepository.findBySimpleNaturalId("001-AR");

        return foundArBook.orElseThrow();
    }

    public Book fetchFirstBookByNaturalIdAgain() {
        // find the first book by a single natural id, transaction 2
        // - the natural id, 001-AR, is fetched from Second Level Cache
        // - the Book entity is fetched from the Second Level Cache as well
        Optional<Book> foundArBook
                = bookRepository.findBySimpleNaturalId("001-AR");

        return foundArBook.orElseThrow();
    }
}
