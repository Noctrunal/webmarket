package com.webmarket.application.service;

import com.webmarket.application.model.Product;

import java.util.Collection;

public interface ProductsService {
    Product save(Product product);

    Product get(String id);

    void delete(String id);

    Collection<Product> getAll();
}
