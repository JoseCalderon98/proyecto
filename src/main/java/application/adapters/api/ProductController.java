package application.adapters.api;

import application.adapters.api.requests.CreateProductRequest;
import application.usecases.ManageProductUseCase;
import domain.models.product.Product;
import domain.models.product.ProductCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ManageProductUseCase manageProductUseCase;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductRequest request) {
        Product p = new Product(
                request.getCode(),
                request.getName(),
                ProductCategory.valueOf(request.getCategory()),
                request.getRequiresApproval()
        );
        return ResponseEntity.ok(manageProductUseCase.createProduct(p));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(manageProductUseCase.getAllProducts());
    }
}
