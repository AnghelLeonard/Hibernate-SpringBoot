package com.bookstore.entity;

import com.bookstore.generator.id.StringPrefixedSequenceIdGenerator;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilopooledlo")
    @GenericGenerator(name = "hilopooledlo", 
            strategy = "com.bookstore.generator.id.StringPrefixedSequenceIdGenerator",
            parameters = {
                @Parameter(name = StringPrefixedSequenceIdGenerator.SEQUENCE_PARAM, value = "hilo_sequence"),
                @Parameter(name = StringPrefixedSequenceIdGenerator.INITIAL_PARAM, value = "1"),                
                @Parameter(name = StringPrefixedSequenceIdGenerator.OPT_PARAM, value = "pooled-lo"),
                @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
                @Parameter(name = StringPrefixedSequenceIdGenerator.PREFIX_PARAM, value = "A-"),
                @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAM, value = "%010d")
            }
    )
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
