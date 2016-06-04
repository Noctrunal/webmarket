package com.webmarket.application.controller.web;

import com.webmarket.application.controller.AbstractProductsController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class RootController extends AbstractProductsController {
    @RequestMapping(value = "/manage", method = GET)
    public String manage() {
        return "adminProductsList";
    }

    @RequestMapping(value = {"/", "/products"}, method = GET)
    public ModelAndView products() {
        return new ModelAndView("userProductsList", "products", super.getAll());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/manage").setViewName("adminProductsList");
        registry.addViewController("/products").setViewName("userProductsList");
        registry.addViewController("/").setViewName("userProductsList");
    }
}
