package com.jpa;

import java.io.Serializable;
import java.time.Instant;

public interface GenericDao<T, ID extends Serializable> {

    String fetchNameAndAmount(String symbol, Instant instant);
    
}
