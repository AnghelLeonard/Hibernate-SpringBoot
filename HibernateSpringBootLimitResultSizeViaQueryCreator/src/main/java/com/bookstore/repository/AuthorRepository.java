package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findTop5ByAge(int age);

    List<Author> findFirst5ByAge(int age, Sort sort);

    List<Author> findTop3ByAgeGreaterThanEqual(int age);

    List<Author> findFirst3ByAgeGreaterThan(int age, Sort sort);

    List<Author> findFirst5ByOrderByAgeDesc();

    List<Author> findTop3ByOrderByAgeDesc();
    
    List<AuthorDto> findTop3ByOrderByAgeAsc();
}
