package com.bookstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    private String title;
    
    @Column(name = "book_isbn")
    private String isbn;
    
    @Column(name = "book_pages")
    private int pages;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "book", orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
        chapter.setBook(this);
    }

    public void removeChapter(Chapter chapter) {
        chapter.setBook(null);
        this.chapters.remove(chapter);
    }

    public void removeChapters() {
        Iterator<Chapter> iterator = this.chapters.iterator();

        while (iterator.hasNext()) {
            Chapter chapter = iterator.next();

            chapter.setBook(null);
            iterator.remove();
        }
    }

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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

}
