package com.bookstore.repository;

import com.bookstore.dto.BookstoreDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
 
    // Query all author names and their titles with the given price
    
    @Transactional(readOnly = true)    
    @Query(value = "SELECT a.name AS name, b.title AS title "
            + "FROM Author a INNER JOIN Book b ON a.name = b.name "
            + "WHERE b.price = ?1")
    List<BookstoreDto> fetchAuthorNameBookTitleWithPrice(int price);

}
