package com.webmarket.application.service;

import com.webmarket.application.model.Product;
import com.webmarket.application.repository.ProductRepository;
import com.webmarket.application.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product get(String id) {
        return ExceptionUtil.checkEntity(repository.findOne(id), id);
    }

    @Override
    public void delete(String id) {
        ExceptionUtil.checkEntity(repository.deleteById(id), id);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
}
