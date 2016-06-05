package com.webmarket.application.controller.web;

import com.webmarket.application.controller.AbstractProductController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WebController extends AbstractProductController {
    @RequestMapping(value = "/manage", method = GET)
    public ModelAndView manage() {
        return new ModelAndView("adminProductList");
    }

    @RequestMapping(value = {"/", "/products"}, method = GET)
    public ModelAndView products() {
        return new ModelAndView("userProductList", "products", super.getAll());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/manage").setViewName("adminProductsList");
        registry.addViewController("/products").setViewName("userProductsList");
        registry.addViewController("/").setViewName("userProductsList");
    }
}
