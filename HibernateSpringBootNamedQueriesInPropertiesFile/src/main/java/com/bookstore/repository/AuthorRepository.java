package com.bookstore.repository;

import com.bookstore.dto.AuthorNameAge;
import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> fetchAllDesc();

    List<Author> fetchAllSorted(int age, Sort sort);

    Page<Author> fetchPageDesc(int age, Pageable pageable);

    Author fetchByNameAndAge(String name, int age);

    @Query(countQuery = "SELECT COUNT(*) FROM author", nativeQuery = true)
    List<Author> fetchAllSortedNative(int age, Pageable pageable);
    
    @Query(nativeQuery = true)
    List<AuthorNameAge> fetchNameAndAge();
}
