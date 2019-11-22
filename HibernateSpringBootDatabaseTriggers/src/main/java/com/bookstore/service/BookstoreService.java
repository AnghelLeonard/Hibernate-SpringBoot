package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Chapter;
import org.springframework.stereotype.Service;
import com.bookstore.repository.BookRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // this will successfully pass the `check_book_pages` trigger check
    @Transactional
    public void addNewChapterSuccess() {
        Book book = bookRepository.findById(1L).orElseThrow();
        
        Chapter chapter6 = new Chapter();
        chapter6.setTitle("Chapter 6");
        chapter6.setPages(20);
        
        book.addChapter(chapter6);               
    }
    
    // this will cause database trigger `check_book_pages` to fail
    @Transactional
    public void addNewChapterFail() {
        Book book = bookRepository.findById(1L).orElseThrow();
        
        Chapter chapter7 = new Chapter();
        chapter7.setTitle("Chapter 7");
        chapter7.setPages(40);
        
        book.addChapter(chapter7);               
    }
}
