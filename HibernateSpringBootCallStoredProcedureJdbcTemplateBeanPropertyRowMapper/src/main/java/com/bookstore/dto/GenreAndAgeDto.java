package com.bookstore.dto;

import java.io.Serializable;

public class GenreAndAgeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String genre;
    private int age;

    public GenreAndAgeDto() {
    }    

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "AuthorDto{" + "genre=" + genre + ", age=" + age + '}';
    }
    
}
