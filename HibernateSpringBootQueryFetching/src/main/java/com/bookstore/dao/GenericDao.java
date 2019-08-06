package com.bookstore.dao;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {

    T findByName(String name);                      
    
    T findByNameViaSession(String name);                      
}
