package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BookRepositoryJoinFetch extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {

    @Override
    // @Query("SELECT b, b.author, b.author.publisher FROM Book b") - INNER JOIN
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author a LEFT JOIN FETCH a.publisher p")
    public List<Book> findAll();
}
