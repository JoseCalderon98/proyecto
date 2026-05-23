package application.usecases;

import domain.models.product.Product;
import domain.ports.product.ProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageProductUseCase {

    @Autowired
    private ProductPort productPort;

    public Product createProduct(Product product) {
        // Enforce Rule 9.1: Unique code
        if (productPort.findByCode(product.getCode()).isPresent()) {
            throw new IllegalArgumentException("El código de producto ya existe en el catálogo");
        }
        return productPort.save(product);
    }

    public List<Product> getAllProducts() {
        return productPort.findAll();
    }
}
