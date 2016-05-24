package dao.datajpa;

import model.Product;

import java.util.Collection;

public interface ProductsDAO {
    Product save(Product product);

    Product get(int id);

    void delete(int id);

    Collection<Product> getAll();
}
