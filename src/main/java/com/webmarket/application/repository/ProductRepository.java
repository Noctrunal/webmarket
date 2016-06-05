package com.webmarket.application.repository;

import com.webmarket.application.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Long deleteById(String id);
}
