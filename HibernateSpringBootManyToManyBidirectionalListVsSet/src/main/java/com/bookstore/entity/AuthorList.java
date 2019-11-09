package com.bookstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AuthorList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private int age;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "author_book_list",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<BookList> books = new ArrayList<>();

    public void addBook(BookList book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(BookList book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }

    public void removeBooks() {
        Iterator<BookList> iterator = this.books.iterator();

        while (iterator.hasNext()) {
            BookList book = iterator.next();

            book.getAuthors().remove(this);
            iterator.remove();
        }
    }

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

    public List<BookList> getBooks() {
        return books;
    }

    public void setBooks(List<BookList> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object obj) {               

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }

        return id != null && id.equals(((AuthorList) obj).id);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name
                + ", genre=" + genre + ", age=" + age + '}';
    }
}
