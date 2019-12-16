package com.bookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "Author.fetchAllDesc",
            query = "SELECT a FROM Author a ORDER BY a.name DESC"),
    @NamedQuery(name = "Author.fetchAllSorted",
            query = "SELECT a FROM Author a"),
    @NamedQuery(name = "Author.fetchPageDesc",
            query = "SELECT a FROM Author a"),
    @NamedQuery(name = "Author.fetchByNameAndAge",
            query = "SELECT a FROM Author a WHERE a.name=?1 AND a.age=?2")
})

@NamedNativeQueries({
    @NamedNativeQuery(name = "Author.fetchAllDescNative",
            query = "SELECT * FROM author ORDER BY name DESC",
            resultClass = Author.class),
    @NamedNativeQuery(name = "Author.fetchByNameAndAgeNative",
            query = "SELECT * FROM author WHERE name=?1 AND age=?2",
            resultClass = Author.class)
})
@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String name;
    private String genre;

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

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", age=" + age
                + ", name=" + name + ", genre=" + genre + '}';
    }
}
