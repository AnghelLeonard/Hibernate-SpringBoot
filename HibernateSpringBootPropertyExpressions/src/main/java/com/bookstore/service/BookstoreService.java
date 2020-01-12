package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.dto.BookDto;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Review;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.ReviewRepository;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository, ReviewRepository reviewRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void fetchAuthorByBook() {

        Book book = bookRepository.findById(4L).orElseThrow();
        Author author = authorRepository.findByBooks(book);

        System.out.println("Fetched author: " + author);
    }

    @Transactional
    public void fetchBooksByAuthor() {

        Author author = authorRepository.findById(4L).orElseThrow();
        List<Book> books = bookRepository.findByAuthor(author);

        System.out.println("Fetched books: " + books);
    }

    @Transactional
    public void fetchAuthorByBooksReviews() {

        Review review = reviewRepository.findById(1L).orElseThrow();
        Author author = authorRepository.findByBooksReviews(review);

        System.out.println("Fetched author: " + author);
    }        
    
    @Transactional(readOnly = true)
    public void fetchAuthorByBookDto() {

        Book book = bookRepository.findById(4L).orElseThrow();
        AuthorDto author = authorRepository.queryByBooks(book);

        System.out.println("Fetched author (DTO): " + author.getName());
    }
    
    @Transactional(readOnly = true)
    public void fetchBooksByAuthorDto() {

        Author author = authorRepository.findById(4L).orElseThrow();
        List<BookDto> books = bookRepository.queryByAuthor(author);

        System.out.println("Fetched books (DTO):");
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByBooksReviewsDto() {

        Review review = reviewRepository.findById(1L).orElseThrow();
        AuthorDto author = authorRepository.queryByBooksReviews(review);

        System.out.println("Fetched author (DTO): " + author.getName());
    }
}
