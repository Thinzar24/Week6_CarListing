package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue

    private long Id;

    private String type;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
