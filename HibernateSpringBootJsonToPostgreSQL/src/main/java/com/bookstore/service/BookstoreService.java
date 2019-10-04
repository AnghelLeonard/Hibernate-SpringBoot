package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.obj.Book;
import org.springframework.stereotype.Service;
import com.bookstore.repository.AuthorRepository;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void newAuthor() {

        Book book = new Book();
        book.setIsbn("001-JN");
        book.setTitle("A History of Ancient Prague");
        book.setPrice(45);

        Author author = new Author();
        author.setName("Joana Nimar");
        author.setAge(34);
        author.setGenre("History");
        author.setBook(book);

        authorRepository.save(author);
    }

    public void byName() {
        Author author = authorRepository.findByName("Joana Nimar");

        System.out.println(author);
    }
    
    public void byBookIsbnNativeQueryCast() {
        Author author = authorRepository.findByBookPriceNativeQueryCast(45);

        System.out.println(author);
    }
    
    public void byBookIsbnNativeQuery() {
        Author author = authorRepository.findByBookIsbnNativeQuery("001-JN");

        System.out.println(author);
    }
}
