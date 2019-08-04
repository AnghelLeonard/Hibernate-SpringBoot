package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
 
    @Column(name = "deleted")
    protected boolean deleted;     
}
