package com.webmarket.application.controller;

import com.webmarket.application.dto.ProductDTO;
import com.webmarket.application.model.Product;
import com.webmarket.application.service.ProductsService;
import com.webmarket.application.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

public abstract class AbstractProductsController extends WebMvcConfigurerAdapter {
    @Autowired
    private ProductsService service;

    protected Product save(Product product) {
        return service.save(product);
    }

    protected Product update(Product product) {
        return service.update(product);
    }

    protected void delete(String id) {
        service.delete(id);
    }

    protected Product get(String id) {
        return service.get(id);
    }

    protected List<Product> getAll() {
        return (List<Product>) service.getAll();
    }

    protected List<ProductDTO> getAllBetweenPrices(double start, double end) {
        return ProductUtil.getFilteredByPrice(getAll(), start, end);
    }

    protected List<ProductDTO> getAllBetweenYears(int start, int end) {
        return ProductUtil.getFilteredByYears(getAll(), start, end);
    }
}
