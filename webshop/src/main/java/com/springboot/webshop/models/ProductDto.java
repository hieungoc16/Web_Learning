package com.springboot.webshop.models;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    @NotEmpty(message = "The name is required")
    private String name;

    @NotEmpty(message = "The brand is required")
    private Brand brand;

    @NotEmpty(message = "The category is required")
    private Category category;

    @Min(0)
    private double price;

    @Size(min = 10, message = "The description should be least at 10 characters")
    @Size(max = 2000, message = "The description can not exceed 2000 characters")
    private String description;

    private MultipartFile imageFile;

    public @NotEmpty(message = "The name is required") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "The name is required") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "The brand is required") Brand getBrand() {
        return

    public void setBrand(@NotEmpty(message = "The brand is required") Brand brand) {
        this.brand = brand;
    }

    public @NotEmpty(message = "The name is required") Category getCategory() {
        return category;
    }

    public void setCategory(@NotEmpty(message = "The name is required") Category category) {
        this.category = category;
    }

    @Min(0)
    public double getPrice() {
        return price;
    }

    public void setPrice(@Min(0) double price) {
        this.price = price;
    }

    public @Size(min = 10, message = "The description should be least at 10 characters") @Size(max = 2000, message = "The description can not exceed 2000 characters") String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 10, message = "The description should be least at 10 characters") @Size(max = 2000, message = "The description can not exceed 2000 characters") String description) {
        this.description = description;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }


}
