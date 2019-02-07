package com.jpa;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface NaturalRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    // use this method when your entity has a single field annotated with @NaturalId
    Optional<T> findBySimpleNaturalId(ID naturalId);
     
    // use this method when your entity has more than one field annotated with @NaturalId
    Optional<T> findByNaturalId(Map<String, Object> naturalIds);        
}
