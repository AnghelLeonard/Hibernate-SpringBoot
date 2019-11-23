package com.bookstore.service;

import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import com.bookstore.repository.BookRepository;
import com.bookstore.specs.InIdsSpecification;
import java.util.List;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void fetchByMultipleIdsFindAllById() {

        List<Book> books = bookRepository.findAllById(List.of(1L, 2L, 5L));
        System.out.println(books);
    }

    public void fetchByMultipleIdsJpql() {

        List<Book> books = bookRepository.fetchByMultipleIds(List.of(1L, 2L, 5L));
        System.out.println(books);
    }

    public void fetchByMultipleIdsSpec() {

        List<Book> books = bookRepository.findAll(
                new InIdsSpecification(List.of(1L, 2L, 5L)));
        System.out.println(books);
    }
}
