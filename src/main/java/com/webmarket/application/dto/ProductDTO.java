package com.webmarket.application.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@SuppressWarnings("unused")
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String JSON_VALIDATION_MESSAGE = "must not be empty";

    private String id;

    @NotNull(message = JSON_VALIDATION_MESSAGE)
    private int releaseYear;

    @NotNull(message = JSON_VALIDATION_MESSAGE)
    private String brand;

    @NotNull(message = JSON_VALIDATION_MESSAGE)
    private String type;

    @NotNull(message = JSON_VALIDATION_MESSAGE)
    private String description;

    @NotNull(message = JSON_VALIDATION_MESSAGE)
    private int amount;

    @NotNull(message = JSON_VALIDATION_MESSAGE)
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(String id, int releaseYear, String brand, String type, String description, int amount, double price) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.price = price;
    }

    public boolean isNew() {
        return (id.equals("0"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id == null ? "0" : id);
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
