package com.bookstore.dao;

import java.io.Serializable;
import java.time.Instant;

public interface GenericDao<T, ID extends Serializable> {

    String fetchTitleAndPrice(String symbol, Instant instant);
    
}
