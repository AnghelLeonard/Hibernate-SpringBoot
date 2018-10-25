package com.jpa;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    <S extends T> S persist(S entity);        
}
