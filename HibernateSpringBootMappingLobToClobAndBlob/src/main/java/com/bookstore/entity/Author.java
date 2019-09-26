package com.bookstore.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String name;
    private String genre;
    
    @Lob    
    private Blob avatar;
    
    @Lob
    private Clob biography;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public Clob getBiography() {
        return biography;
    }

    public void setBiography(Clob biography) {
        this.biography = biography;
    }
          
    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", age=" + age
                + ", name=" + name + ", genre=" + genre + '}';
    }
}
