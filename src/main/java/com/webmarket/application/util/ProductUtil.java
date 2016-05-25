package com.webmarket.application.util;

import com.webmarket.application.dto.ProductDTO;
import com.webmarket.application.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductUtil {

    public static List<ProductDTO> getFilteredByPrice(List<Product> products, double startPrice, double endPrice) {
        return products.stream().filter(
                product -> (FilterUtil.isBetweenPrices(startPrice, endPrice, product.getPrice()))).map(
                product -> new ProductDTO(
                        product.getId(),
                        product.getReleaseYear(),
                        product.getBrand(),
                        product.getType(),
                        product.getDescription(),
                        product.getAmount(),
                        product.getPrice()))
                .collect(Collectors.toList()
        );
    }

    public static List<ProductDTO> getFilteredByYears(List<Product> products, int start, int end) {
        return products.stream().filter(
                product -> FilterUtil.isBetweenYears(
                        start, end, product.getReleaseYear())).map(
                product -> new ProductDTO(
                        product.getId(),
                        product.getReleaseYear(),
                        product.getBrand(),
                        product.getType(),
                        product.getDescription(),
                        product.getAmount(),
                        product.getPrice()))
                .collect(Collectors.toList()
        );
    }

    public static Product createFromTo(ProductDTO productDTO) {
        return new Product(
                null,
                productDTO.getReleaseYear(),
                productDTO.getBrand(),
                productDTO.getType(),
                productDTO.getPrice(),
                productDTO.getAmount(),
                productDTO.getDescription()
        );
    }

    public static Product updateFromTo(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getReleaseYear(),
                productDTO.getBrand(),
                productDTO.getType(),
                productDTO.getPrice(),
                productDTO.getAmount(),
                productDTO.getDescription()
        );
    }
}
