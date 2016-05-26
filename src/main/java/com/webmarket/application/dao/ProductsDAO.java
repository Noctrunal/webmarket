package com.webmarket.application.dao;

import com.webmarket.application.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsDAO extends MongoRepository<Product, String> {
}
