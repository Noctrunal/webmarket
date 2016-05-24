package controller.ajax;

import controller.AbstractProductsController;
import dto.ProductDTO;
import model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.ProductUtil;

import javax.validation.Valid;
import java.util.List;

import static controller.ajax.AjaxProductsController.AJAX_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(AJAX_URL)
public class AjaxProductsController extends AbstractProductsController {
    static final String AJAX_URL = "/ajax/profile/products";

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public Product get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = POST)
    public void createOrUpdate(@Valid ProductDTO productDTO) {
        if (productDTO.getId() == 0) {
            super.save(ProductUtil.createFromTo(productDTO));
        } else {
            super.update(ProductUtil.updateFromTo(productDTO));
        }
    }
}
