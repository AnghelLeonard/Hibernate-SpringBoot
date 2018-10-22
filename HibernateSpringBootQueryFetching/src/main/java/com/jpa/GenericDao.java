package com.jpa;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    List<T> findByName(String name);                      
    
    List<T> findByNameViaSession(String name);                      
}
