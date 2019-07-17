package com.bookstore.entity;

import com.vladmihalcea.hibernate.type.basic.YearMonthIntegerType;
import java.io.Serializable;
import java.time.YearMonth;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(
        typeClass = YearMonthIntegerType.class, // or, YearMonthDateType
        defaultForType = YearMonth.class
)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;    
    private YearMonth releaseDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public YearMonth getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(YearMonth releaseDate) {
        this.releaseDate = releaseDate;
    }        

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title 
                + ", isbn=" + isbn + ", releaseDate=" + releaseDate + '}';
    }        
}