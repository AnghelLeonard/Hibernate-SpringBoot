package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorDtoRepository extends JpaRepository<AuthorDto, Long> {
}
