package com.bookstore.dao;

import java.io.Serializable;
import java.util.Optional;

public interface GenericDao<T, ID extends Serializable> {

    Optional<T> find(Class<T> clazz, ID id);        
    
    Optional<T> findViaSession(Class<T> clazz, ID id);        
}
