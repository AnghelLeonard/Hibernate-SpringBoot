package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Query(value = "SELECT b FROM Book b LEFT JOIN FETCH b.author WHERE b.isbn LIKE ?1%",
            countQuery = "SELECT COUNT(b) FROM Book b WHERE b.isbn LIKE ?1%")
    Page<Book> fetchWithAuthorsByIsbnCQ(String isbn, Pageable pageable);

    @Transactional
    @EntityGraph(attributePaths = {"author"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "SELECT b FROM Book b WHERE b.isbn LIKE ?1%")
    Page<Book> fetchWithAuthorsByIsbnEG(String isbn, Pageable pageable);
}
