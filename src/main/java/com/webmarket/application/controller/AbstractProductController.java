package com.webmarket.application.controller;

import com.webmarket.application.controller.manager.AmazonStorageManager;
import com.webmarket.application.model.Product;
import com.webmarket.application.service.ProductService;
import com.webmarket.application.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.UUID;

public abstract class AbstractProductController extends WebMvcConfigurerAdapter {
    @Autowired
    private ProductService service;

    @Autowired
    private AmazonStorageManager storage;

    protected void save(Product product, MultipartFile image) {
        if (!image.isEmpty()) {
            product.setImageUrl(storage.save(UUID.randomUUID() + ".jpg", image));
        }
        service.save(product);
    }

    protected void delete(String id) {
        service.delete(id);
    }

    protected Product get(String id) {
        return service.get(id);
    }

    protected List<Product> getAll() {
        return service.getAll();
    }

    protected List<Product> getAllBetweenPrices(double start, double end) {
        return ProductUtil.getFilteredByPrice(getAll(), start, end);
    }

    protected List<Product> getAllBetweenYears(int start, int end) {
        return ProductUtil.getFilteredByYears(getAll(), start, end);
    }
}
