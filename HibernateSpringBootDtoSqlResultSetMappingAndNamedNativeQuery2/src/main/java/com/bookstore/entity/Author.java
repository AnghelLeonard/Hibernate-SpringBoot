package com.bookstore.entity;

import com.bookstore.dto.AuthorDto;
import java.io.Serializable;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

// Scalar Mapping
@SqlResultSetMapping(
        name = "AuthorsNameMapping",
        columns = {
            @ColumnResult(name = "name")
        }
)
@NamedNativeQuery(
        name = "Author.fetchName",
        query = "SELECT name FROM author",
        resultSetMapping = "AuthorsNameMapping"
)

// Constructor Mapping
@NamedNativeQuery(
        name = "Author.fetchNameAndAge",
        query = "SELECT name, age FROM author",
        resultSetMapping = "AuthorDtoMapping"
)
@SqlResultSetMapping(
        name = "AuthorDtoMapping",
        classes = @ConstructorResult(
                targetClass = AuthorDto.class,
                columns = {
                    @ColumnResult(name = "name"),
                    @ColumnResult(name = "age")
                }
        )
)
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
