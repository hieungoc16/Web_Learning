package com.springboot.webshop.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "brand")
public class Brand {
    @NotEmpty
    @Id
    @Column(name = "brand_id")
    private String brand_id;
    @Column(name = "brand_name")
    private String brand_name;

    @Column(name = "brand_address")
    private String brand_address;

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_address() {
        return brand_address;
    }

    public void setBrand_address(String brand_address) {
        this.brand_address = brand_address;
    }
}