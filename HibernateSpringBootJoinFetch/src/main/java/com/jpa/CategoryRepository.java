package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category c JOIN FETCH c.products WHERE c.cname = ?1")
    Category categoryIncludingProducts(String cname);
}
