package dao.datajpa;

import model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProxyProductsDAO extends JpaRepository<Product, Integer> {

    @Transactional
    @Override
    Product save(Product product);

    @Override
    Product findOne(Integer id);

    @Override
    List<Product> findAll(Sort sort);

    @Transactional
    @Override
    void delete(Integer id);
}
