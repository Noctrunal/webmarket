package dao.datajpa;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ProductsDAOImpl implements ProductsDAO {
    @Autowired
    private ProxyProductsDAO proxy;

    @Override
    public Product save(Product product) {
        return proxy.save(product);
    }

    @Override
    public Product get(int id) {
        return proxy.findOne(id);
    }

    @Override
    public void delete(int id) {
        proxy.delete(id);
    }

    @Override
    public Collection<Product> getAll() {
        return proxy.findAll();
    }
}
