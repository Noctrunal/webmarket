package com.webmarket.application.dao;

import com.webmarket.application.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductsDAO extends JpaRepository<Product, Integer> {
}
