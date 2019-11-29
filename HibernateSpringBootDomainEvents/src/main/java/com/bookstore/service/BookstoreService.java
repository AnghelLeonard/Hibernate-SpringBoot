package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookReview;
import com.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.BookReviewRepository;

@Service
public class BookstoreService {

    private final static String RESPONSE
            = "We check your review and get back to you with an e-mail ASAP :)";

    private final BookRepository bookRepository;
    private final BookReviewRepository bookReviewRepository;

    public BookstoreService(BookReviewRepository bookReviewRepository, BookRepository bookRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookRepository = bookRepository;
    }

    public void insertBook() {
        Book book = new Book();
        book.setAuthor("Mark Janel");
        book.setIsbn("001-LD");
        book.setTitle("Lucky Day");
        book.setId(1L);

        bookRepository.save(book);
    }

    @Transactional
    public String postReview(BookReview bookReview) {

        Book book = bookRepository.getOne(1L);
        bookReview.setBook(book);

        bookReview.registerReviewEvent();

        bookReviewRepository.save(bookReview);

        return RESPONSE;
    }
}
