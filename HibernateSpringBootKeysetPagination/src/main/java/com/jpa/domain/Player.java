package com.jpa.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
 
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
       
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }    

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", name=" + name + ", age=" + age + '}';
    }
        
}