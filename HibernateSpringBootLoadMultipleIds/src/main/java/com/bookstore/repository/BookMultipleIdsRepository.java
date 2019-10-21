package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.multipleids.MultipleIdsRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class BookMultipleIdsRepository extends MultipleIdsRepositoryImpl<Book, Long> {

    public BookMultipleIdsRepository() {
        super(Book.class);
    }
}
