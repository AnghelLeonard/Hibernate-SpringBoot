package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> fetchAll();

    Author fetchByNameAndAge(String name, int age);

    List<Author> fetchViaSort(Sort sort);

    List<Author> fetchViaSortWhere(int age, Sort sort);

    Page<Author> fetchPageSort(Pageable pageable);

    Page<Author> fetchPageSortWhere(int age, Pageable pageable);

    Slice<Author> fetchSliceSort(Pageable pageable);

    Slice<Author> fetchSliceSortWhere(int age, Pageable pageable);

    @Query(nativeQuery = true)
    List<Author> fetchAllNative();

    @Query(nativeQuery = true)
    Author fetchByNameAndAgeNative(String name, int age);

    // causes exception: InvalidJpaQueryMethodException: Cannot use native queries with dynamic sorting
    // @Query(nativeQuery = true)
    // List<Author> fetchViaSortNative(Sort sort);
    
    // causes exception: InvalidJpaQueryMethodException: Cannot use native queries with dynamic sorting
    // @Query(nativeQuery = true)
    // List<Author> fetchViaSortWhereNative(int age, Sort sort);
    
    // without this explicit 'countQuery' Spring will trigger a 'SELECT * FROM author'
    @Query(countQuery = "SELECT COUNT(*) FROM author", nativeQuery = true)
    Page<Author> fetchPageSortNative(Pageable pageable);

    @Query(nativeQuery = true)
    Page<Author> fetchPageSortWhereNative(int age, Pageable pageable);

    @Query(nativeQuery = true)
    Slice<Author> fetchSliceSortNative(Pageable pageable);

    @Query(nativeQuery = true)
    Slice<Author> fetchSliceSortWhereNative(int age, Pageable pageable);
}
