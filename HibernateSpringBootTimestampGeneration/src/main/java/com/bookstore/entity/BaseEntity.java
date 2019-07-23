package com.bookstore.entity;

import com.bookstore.modifiedby.ModifiedBy;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntity<U> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date created;

    @UpdateTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    protected Date lastModified;

    @ModifiedBy
    protected U lastModifiedBy;        
}