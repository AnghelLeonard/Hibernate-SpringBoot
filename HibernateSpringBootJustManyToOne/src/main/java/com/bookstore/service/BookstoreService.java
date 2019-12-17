package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void insertNewBook() {
        Author author = authorRepository.getOne(4L);
        // or, less efficient since a SELECT is triggered
        // Author author = authorRepository.findByName("Joana Nimar");        

        Book book = new Book();
        book.setIsbn("003-JN");
        book.setTitle("History Of Present");
        book.setAuthor(author);

        bookRepository.save(book);
    }
    
    public void fetchBooksOfAuthorById() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        System.out.println(books);
    }

    public void fetchPageBooksOfAuthorById() {
        Page<Book> books = bookRepository.fetchPageBooksOfAuthorById(4L,
                PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "title")));

        books.get().forEach(System.out::println);
    }

    @Transactional
    public void fetchBooksOfAuthorByIdAndAddNewBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);

        Book book = new Book();
        book.setIsbn("004-JN");
        book.setTitle("History Facts");
        book.setAuthor(books.get(0).getAuthor());          

        books.add(bookRepository.save(book));
        
        System.out.println(books);
    }
    
    @Transactional
    public void fetchBooksOfAuthorByIdAndDeleteFirstBook() {
        List<Book> books = bookRepository.fetchBooksOfAuthorById(4L);
                
        bookRepository.delete(books.remove(0));                
        
        System.out.println(books);
    }
}
