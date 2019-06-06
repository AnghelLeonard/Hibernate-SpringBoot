package com.app.dao;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {

    <S extends T> S persist(S entity);        
}
