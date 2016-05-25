package com.webmarket.application.service;

import com.webmarket.application.model.Product;

import java.util.Collection;

public interface ProductsService {
    Product save(Product product);

    Product get(int id);

    void delete(int id);

    Product update(Product product);

    Collection<Product> getAll();

    void evictCache();
}
