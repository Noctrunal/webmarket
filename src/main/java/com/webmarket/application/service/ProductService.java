package com.webmarket.application.service;

import com.webmarket.application.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product get(String id);

    void delete(String id);

    List<Product> getAll();
}
