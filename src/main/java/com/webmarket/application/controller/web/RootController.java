package com.webmarket.application.controller.web;

import com.webmarket.application.controller.AbstractProductsController;
import com.webmarket.application.dto.ProductDTO;
import com.webmarket.application.model.Product;
import com.webmarket.application.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RootController extends AbstractProductsController {
    @Autowired
    private ProductUtil productUtil;

    @RequestMapping(value = "/manage", method = GET)
    public String manage() {
        return "adminProductsList";
    }

    @RequestMapping(value = {"/", "/products"}, method = GET)
    public String products(Model model) {
        model.addAttribute("products", super.getAll());
        return "userProductsList";
    }

    @RequestMapping(value = "/manage/save/", method = POST)
    public String updateOrCreate(@Valid ProductDTO productDTO, @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            if (productDTO.isNew()) {
                Product product = super.save(ProductUtil.createFromTo(productDTO));
                product.setImageUrl(productUtil.getImageUrl(product.getId() + ".jpg", image));
                super.update(product);
            } else {
                Product product = ProductUtil.updateFromTo(productDTO);
                product.setImageUrl(productUtil.getImageUrl(product.getId() + ".jpg", image));
                super.update(product);
            }
        } else {
            if (productDTO.isNew()) {
                super.save(ProductUtil.createFromTo(productDTO));
            } else {
                productDTO.setImageUrl(super.get(productDTO.getId()).getImageUrl());
                super.update(ProductUtil.updateFromTo(productDTO));
            }
        }
        return "adminProductsList";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/manage").setViewName("adminProductsList");
        registry.addViewController("/products").setViewName("userProductsList");
        registry.addViewController("/").setViewName("userProductsList");
    }
}
