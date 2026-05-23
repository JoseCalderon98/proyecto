package domain.ports.product;

import domain.models.product.Product;
import java.util.List;
import java.util.Optional;

public interface ProductPort {
    Product save(Product product);
    Optional<Product> findById(Integer id);
    Optional<Product> findByCode(String code);
    List<Product> findAll();
}
