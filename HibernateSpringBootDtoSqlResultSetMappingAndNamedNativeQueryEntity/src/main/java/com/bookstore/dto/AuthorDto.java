package com.bookstore.dto;

import java.io.Serializable;

public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final int age;

    public AuthorDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }    

    @Override
    public String toString() {
        return "AuthorDto{" + "name=" + name + ", age=" + age + '}';
    }        
}
