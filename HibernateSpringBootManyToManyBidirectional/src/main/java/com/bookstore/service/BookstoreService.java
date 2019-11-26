package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    @Transactional
    public void insertAuthorsWithBooks() {

        Author alicia = new Author();
        alicia.setName("Alicia Tom");
        alicia.setAge(38);
        alicia.setGenre("Anthology");

        Author mark = new Author();
        mark.setName("Mark Janel");
        mark.setAge(23);
        mark.setGenre("Anthology");

        Book bookOfSwords = new Book();
        bookOfSwords.setIsbn("001-AT-MJ");
        bookOfSwords.setTitle("The book of swords");

        Book oneDay = new Book();
        oneDay.setIsbn("002-AT-MJ");
        oneDay.setTitle("One Day");

        alicia.addBook(bookOfSwords); // use addBook() helper
        mark.addBook(oneDay);

        authorRepository.save(alicia);
        authorRepository.save(mark);
    }
}
