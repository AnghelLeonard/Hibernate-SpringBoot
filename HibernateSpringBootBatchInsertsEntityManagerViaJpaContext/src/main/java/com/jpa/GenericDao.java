package com.jpa;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {    

    <S extends T> S persist(S entity);
    
    <S extends T> Iterable<S> saveInBatch(Iterable<S> entites);
}
