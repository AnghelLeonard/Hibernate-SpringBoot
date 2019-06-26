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

    List<Author> findFirst5ByAge(int age);
    
    List<Author> findFirst5ByAgeGreaterThanEqual(int age);
    
    List<Author> findFirst5ByAgeLessThan(int age);

    List<Author> findFirst5ByAgeOrderByNameDesc(int age);    
    
    List<Author> findFirst5ByGenreOrderByAgeAsc(String genre);
    
    List<Author> findFirst5ByAgeGreaterThanEqualOrderByNameAsc(int age);
    
    List<Author> findFirst5ByGenreAndAgeLessThanOrderByNameDesc(String genre, int age);
    
    List<AuthorDto> findFirst5ByOrderByAgeAsc();

}
