package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> fetchAllDesc();

    Page<Author> fetchPageDesc(Pageable pageable);

    Author fetchByNameAndAge(String name, int age);

    @Query(nativeQuery = true)
    List<AuthorDto> fetchNameAndAge();
}
