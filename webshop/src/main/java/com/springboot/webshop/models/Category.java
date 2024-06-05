package com.springboot.webshop.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "category")
public class Category {
    @NotEmpty
    @Id
    @Column(name = "category_id")
    private String category_id;
    @Column(name = "category_name")
    private String category_name;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
