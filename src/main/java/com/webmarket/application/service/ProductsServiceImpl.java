package com.webmarket.application.service;

import com.webmarket.application.dao.ProductsDAO;
import com.webmarket.application.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsDAO dao;

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Product save(Product product) {
        return dao.save(product);
    }

    @Override
    public Product get(int id) {
        return dao.get(id);
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public Product update(Product product) {
        return dao.save(product);
    }

    @Cacheable("products")
    @Override
    public Collection<Product> getAll() {
        return dao.getAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public void evictCache() {
    }
}
