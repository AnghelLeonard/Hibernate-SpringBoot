package com.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clinic")
public class Clinic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_name")
    private String pname;

    @Column(name = "medical_history")
    private String mhistory;
    
    @Column(name = "patient_treatment")
    private String ptreatment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMhistory() {
        return mhistory;
    }

    public void setMhistory(String mhistory) {
        this.mhistory = mhistory;
    }

    public String getPtreatment() {
        return ptreatment;
    }

    public void setPtreatment(String ptreatment) {
        this.ptreatment = ptreatment;
    }    
}
