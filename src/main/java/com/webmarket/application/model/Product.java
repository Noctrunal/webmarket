package com.webmarket.application.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class Product {
    @Id
    private String id;

    private String imageUrl;

    @NotNull(message = "must not be null")
    private int releaseYear;

    @NotEmpty(message = "must not be empty")
    private String brand;

    @NotEmpty(message = "must not be empty")
    private String type;

    @NotEmpty(message = "must not be empty")
    private String description;

    @NotNull(message = "must not be null")
    private int price;

    @NotNull(message = "must not be null")
    private int amount;

    public Product() {
    }

    public Product(String id, String imageUrl, int releaseYear, String brand, String type, String description, int price, int amount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.releaseYear = releaseYear;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id.isEmpty() ? null : id);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
