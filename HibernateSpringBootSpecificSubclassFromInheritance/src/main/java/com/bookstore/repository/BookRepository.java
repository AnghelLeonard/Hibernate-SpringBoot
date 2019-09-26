package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT b FROM Author a JOIN a.books b WHERE a.name = ?1 AND TYPE(b) = 'Ebook'")
    public Book findByAuthorName(String name);
}
