package com.bookstore.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BatchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    
    <S extends T> void saveInBatch(List<S> entites);    
}
