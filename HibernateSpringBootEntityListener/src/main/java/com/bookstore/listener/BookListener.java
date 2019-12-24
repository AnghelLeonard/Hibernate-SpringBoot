package com.bookstore.listener;

import com.bookstore.entity.Book;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class BookListener {

    @PrePersist
    void onPrePersist(Book book) {
        System.out.println("BookListener.onPrePersist(): " + book);
    }

    @PostPersist
    void onPostPersist(Book book) {
        System.out.println("BookListener.onPostPersist(): " + book);
    }

    @PostLoad
    void onPostLoad(Book book) {
        System.out.println("BookListener.onPostLoad(): " + book);
    }

    @PreUpdate
    void onPreUpdate(Book book) {
        System.out.println("BookListener.onPreUpdate(): " + book);
    }

    @PostUpdate
    void onPostUpdate(Book book) {
        System.out.println("BookListener.onPostUpdate(): " + book);
    }

    @PreRemove
    void onPreRemove(Book book) {
        System.out.println("BookListener.onPreRemove(): " + book);
    }

    @PostRemove
    void onPostRemove(Book book) {
        System.out.println("BookListener.onPostRemove(): " + book);
    }
}
