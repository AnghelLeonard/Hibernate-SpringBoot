package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookNaturalRepository;
import com.bookstore.repository.BookRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final BookNaturalRepository bookNaturalRepository;
    private final BookRepository bookRepository;

    public BookstoreService(BookNaturalRepository bookNaturalRepository,
            BookRepository bookRepository) {
        this.bookNaturalRepository = bookNaturalRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void persistTwoBooks() {

        Book ar = new Book();
        ar.setIsbn("001-AR");
        ar.setTitle("Ancient Rome");
        ar.setPrice(25);
        // ar.setSku(1L);

        Book rh = new Book();
        rh.setIsbn("001-RH");
        rh.setTitle("Rush Hour");
        rh.setPrice(31);
        // rh.setSku(2L);

        bookRepository.save(ar);
        bookRepository.save(rh);
    }

    public Book fetchFirstBookByNaturalId() {

        // find the first book by a single natural id
        Optional<Book> foundArBook
                = bookNaturalRepository.findBySimpleNaturalId("001-AR");

        // find first book by two natural ids (for running this code simply        
        // uncomment the "sku" field in the Book entity and the below lines; comment lines 44 and 45)
        // Map<String, Object> ids = new HashMap<>();
        // ids.put("sku", 1L);
        // ids.put("isbn", "001-AR");            
        // Optional<Book> foundArBook = bookNaturalRepository.findByNaturalId(ids);
        
        return foundArBook.orElseThrow();
    }
}
