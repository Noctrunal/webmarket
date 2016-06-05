package com.webmarket.application.controller.ajax;

import com.webmarket.application.controller.AbstractProductController;
import com.webmarket.application.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/ajax/admin/products/")
public class AjaxProductController extends AbstractProductController {
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Product get(@PathVariable("id") String id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") String id) {
        super.delete(id);
    }

    @RequestMapping(method = POST)
    public void createOrUpdate(@Valid Product product, @RequestParam(value = "image", required = false) MultipartFile image) {
        super.save(product, image);
    }
}