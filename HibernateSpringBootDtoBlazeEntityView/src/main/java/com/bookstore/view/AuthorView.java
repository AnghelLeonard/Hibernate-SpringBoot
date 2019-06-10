package com.bookstore.view;

import com.blazebit.persistence.view.EntityView;
import com.bookstore.entity.Author;

@EntityView(Author.class)
public interface AuthorView {

    public String getName();
    public int getAge();
}
