package controller;

import dto.ProductDTO;
import model.Product;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import service.ProductsService;
import util.ProductUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractProductsController {
    @Autowired
    private ProductsService service;

    private static final Logger LOG = getLogger(AbstractProductsController.class);

    protected void save(Product product) {
        LOG.debug("save {}", product);
        service.save(product);
    }

    protected void update(Product product) {
        LOG.debug("update {}", product);
        service.update(product);
    }

    public void delete(int id) {
        LOG.debug("delete {}", id);
        service.delete(id);
    }

    public Product get(int id) {
        LOG.debug("get {}", id);
        return service.get(id);
    }

    public List<Product> getAll() {
        LOG.debug("getAll");
        return (List<Product>) service.getAll();
    }

    public List<ProductDTO> getAllBetweenPrices(double start, double end) {
        LOG.debug("getAllBetweenPrices {} ", start, end);
        return ProductUtil.getFilteredByPrice(getAll(), start, end);
    }

    public List<ProductDTO> getAllBetweenYears(int start, int end) {
        LOG.debug("getAllBetweenYears {}", start, end);
        return ProductUtil.getFilteredByYears(getAll(), start, end);
    }
}
