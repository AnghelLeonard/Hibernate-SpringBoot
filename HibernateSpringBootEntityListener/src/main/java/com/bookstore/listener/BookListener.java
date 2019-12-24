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
    void onPrePersist(Book myEntity) {
        System.out.println("MyEntityListener.onPrePersist(): " + myEntity);
    }

    @PostPersist
    void onPostPersist(Book myEntity) {
        System.out.println("MyEntityListener.onPostPersist(): " + myEntity);
    }

    @PostLoad
    void onPostLoad(Book myEntity) {
        System.out.println("MyEntityListener.onPostLoad(): " + myEntity);
    }

    @PreUpdate
    void onPreUpdate(Book myEntity) {
        System.out.println("MyEntityListener.onPreUpdate(): " + myEntity);
    }

    @PostUpdate
    void onPostUpdate(Book myEntity) {
        System.out.println("MyEntityListener.onPostUpdate(): " + myEntity);
    }

    @PreRemove
    void onPreRemove(Book myEntity) {
        System.out.println("MyEntityListener.onPreRemove(): " + myEntity);
    }

    @PostRemove
    void onPostRemove(Book myEntity) {
        System.out.println("MyEntityListener.onPostRemove(): " + myEntity);
    }
}
