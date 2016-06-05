package com.webmarket.application.util;

import com.webmarket.application.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductUtil {
    private ProductUtil() {
    }

    public static List<Product> getFilteredByPrice(List<Product> products, double startPrice, double endPrice) {
        return products.stream().filter(
                product -> FilterUtil.isBetweenPrices(startPrice, endPrice, product.getPrice())).map(
                product -> new Product(
                        product.getId(),
                        product.getImageUrl(),
                        product.getReleaseYear(),
                        product.getBrand(),
                        product.getType(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getAmount()))
                .collect(Collectors.toList());
    }

    public static List<Product> getFilteredByYears(List<Product> products, int startYear, int endYear) {
        return products.stream().filter(
                product -> FilterUtil.isBetweenYears(startYear, endYear, product.getReleaseYear())).map(
                product -> new Product(
                        product.getId(),
                        product.getImageUrl(),
                        product.getReleaseYear(),
                        product.getBrand(),
                        product.getType(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getAmount()))
                .collect(Collectors.toList());
    }
}
