package com.bookstore.view;

import com.blazebit.persistence.spring.data.repository.EntityViewRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorViewRepository extends EntityViewRepository<AuthorView, Long> {
}
