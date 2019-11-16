package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    
    // cache List<Book> in "books"
    @Cacheable(cacheNames = "books")
    public List<Book> fetchBookByPrice() {
        List<Book> books = bookRepository.fetchByPrice(30);
        
        return books;
    }       

    // evict cache "books"
    @CacheEvict(cacheNames="books", allEntries=true)
    public void deleteBooks() {
        bookRepository.deleteAllInBatch();
    }
}
