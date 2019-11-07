package com.bookstore.repository;
 
import com.bookstore.entity.Author;
import com.bookstore.projection.AuthorInfo;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Transactional
    @Query(value = "SELECT a AS author, (SELECT count(a) FROM Author a) AS total FROM Author a")
    List<AuthorInfo> fetchAll(Pageable pageable);        
}
