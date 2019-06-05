package com.bookstore.projection;

import org.springframework.beans.factory.annotation.Value;

public interface AuthorNameAge {

    String getName();

    @Value("#{target.age}")
    String years();

    @Value("#{ T(java.lang.Math).random() * 10000 }")
    int rank();

    @Value("5")
    String books();
}
