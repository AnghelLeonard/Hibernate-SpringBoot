package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import java.util.List;
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

    // cache entities in SLC    
    public void fetchBook() {
        Book book = bookRepository.findById(1L).orElseThrow();
    }
    
    // cache query results in SLC    
    public void fetchBookByPrice() {
        List<Book> books = bookRepository.fetchByPrice(30);
    }

}
