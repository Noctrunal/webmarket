package com.webmarket.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@SuppressWarnings("unused")
public class Product extends BaseEntity {
    private static final String JSON_DATE_PATTERN = "yyyy-MM-dd";

    private Integer releaseYear;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = JSON_DATE_PATTERN)
    private Date date = new Date();

    private String brand;

    private String type;

    private double price;

    private int amount;

    private String description;

    public Product() {
    }

    public Product(String id, int releaseYear, String brand, String type, double price, int amount, String description) {
        super(id);
        this.releaseYear = releaseYear;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.amount = amount;
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
