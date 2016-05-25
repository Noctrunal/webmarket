package com.webmarket.application.controller.web;

import com.webmarket.application.controller.AbstractProductsController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class RootController extends AbstractProductsController {
    @RequestMapping(value = "/", method = GET)
    public String root(Model model) {
        model.addAttribute("products", super.getAll());
        return "productsList";
    }
}
