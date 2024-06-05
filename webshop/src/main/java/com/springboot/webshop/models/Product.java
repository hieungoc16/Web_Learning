package com.springboot.webshop.models;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product {
    @Column(name = "product_id")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    private Brand brand;
    @ManyToOne

    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "product_price")
    private double price;

    @Column(name = "product_description")
    private String description;
    @Column(name = "product_date_at")
    private Date CreatedAt;
    @Column(name = "product_image_name")
    private String imageFileName;

    public Product() {
    }

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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}