package com.webmarket.application.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "must not be null")
    private int releaseYear;

    @NotNull(message = "must not be null")
    private String brand;

    @NotNull(message = "must not be null")
    private String type;

    @NotNull(message = "must not be null")
    private String description;

    @NotNull(message = "must not be null")
    private int amount;

    @NotNull(message = "must not be null")
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, int releaseYear, String brand, String type, String description, int amount, double price) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.brand = brand;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = (id == null ? 0 : id);
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
