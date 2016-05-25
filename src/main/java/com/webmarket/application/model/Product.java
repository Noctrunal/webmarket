package com.webmarket.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;

@Cache(usage = NONSTRICT_READ_WRITE)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "release_year", nullable = false)
    @NotNull
    private Integer releaseYear;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date", columnDefinition = "timestamp default now()")
    private Date date = new Date();

    @Column(name = "brand", nullable = false)
    @NotNull
    private String brand;

    @Column(name = "type", nullable = false)
    @NotNull
    private String type;

    @Column(name = "price", nullable = false)
    @NotNull
    private double price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    public Product() {
    }

    public Product(Integer id, int releaseYear, String brand, String type, double price, int amount, String description) {
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
