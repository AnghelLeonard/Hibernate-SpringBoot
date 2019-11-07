package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Transactional
    @Query(value = "SELECT t.total, id, name, age, genre FROM author, "
            + "(SELECT count(*) as total FROM author) AS t",            
            nativeQuery = true)
    List<Author> fetchAll(Pageable pageable);        
}
