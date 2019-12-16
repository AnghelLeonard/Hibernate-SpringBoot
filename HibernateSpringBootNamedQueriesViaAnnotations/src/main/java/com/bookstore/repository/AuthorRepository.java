package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> fetchGtAgeDescName(int age);

    Author fetchByNameAndAge(String name, int age);

    List<Author> fetchGtAgeDescNameNative(int age);

    Author fetchByNameAndAgeNative(String name, int age);
}
