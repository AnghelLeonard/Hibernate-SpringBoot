package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.Book;
import com.bookstore.wrapper.Books;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.BookRepository;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<BookDto> updateBookPrice() {        
        
        Books books = bookRepository.findBy();
        
        int sumPricesBefore = books.sumPrices();
        System.out.println("Total prices before update: " + sumPricesBefore);
        
        Map<Boolean, List<Book>> booksMap = books.groupByPrice(25);

        booksMap.get(Boolean.TRUE).forEach(
                a -> a.setPrice(a.getPrice() + 3));

        booksMap.get(Boolean.FALSE).forEach(
                a -> a.setPrice(a.getPrice() + 5));
        
        int sumPricesAfter = books.sumPrices();
        System.out.println("Total prices after update: " + sumPricesAfter);
        
        return books.toBookDto();
    }

}
