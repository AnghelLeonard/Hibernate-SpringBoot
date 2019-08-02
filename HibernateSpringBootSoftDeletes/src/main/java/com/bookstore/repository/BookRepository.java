package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> findAllIncludingDeleted();

    @Query(value = "SELECT * FROM book AS b WHERE b.deleted = true", nativeQuery = true)
    List<Book> findAllOnlyDeleted();
}
