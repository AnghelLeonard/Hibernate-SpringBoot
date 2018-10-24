package com.jpa;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Entity
public class User implements Serializable {

    private static final Logger logger = 
            Logger.getLogger(User.class.getName());
        
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private int age;
    
    @PrePersist
    public void prePersist() {
        logger.info("@PrePersist callback ...");
    }       
    
    @PreUpdate
    public void preUpdate() {
        logger.info("@PreUpdate callback ...");
    }
    
    @PreRemove
    public void preRemove() {
        logger.info("@PreRemove callback ...");
    }
    
    @PostLoad
    public void postLoad() {
        logger.info("@PostLoad callback ...");
    }
    
    @PostPersist
    public void postPersist() {
        logger.info("@PostPersist callback ...");
    }
    
    @PostUpdate
    public void postUpdate() {
        logger.info("@PostUpdate callback ...");
    }
    
    @PostRemove
    public void postRemove() {
        logger.info("@PostRemove callback ...");
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", city=" + city + ", age=" + age + '}';
    }    
}
