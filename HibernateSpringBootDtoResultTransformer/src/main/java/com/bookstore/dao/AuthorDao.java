package com.bookstore.dao;

import com.bookstore.dto.AuthorDtoNoSetters;
import com.bookstore.dto.AuthorDtoWithSetters;
import java.util.List;

public interface AuthorDao {

    public List<AuthorDtoNoSetters> fetchAuthorsNoSetters();

    public List<AuthorDtoWithSetters> fetchAuthorsWithSetters();
}
