package application.adapters.persistence.jpa;

import application.adapters.persistence.jpa.entities.ProductEntity;
import application.adapters.persistence.jpa.repositories.SpringProductRepository;
import domain.models.product.Product;
import domain.ports.product.ProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductPersistenceAdapter implements ProductPort {

    @Autowired
    private SpringProductRepository repository;

    private Product mapToDomain(ProductEntity entity) {
        Product p = new Product(entity.getCode(), entity.getName(), entity.getCategory(), entity.isRequiresApproval());
        p.setId(entity.getId());
        return p;
    }

    private ProductEntity mapToEntity(Product p) {
        ProductEntity e = new ProductEntity();
        e.setId(p.getId());
        e.setCode(p.getCode());
        e.setName(p.getName());
        e.setCategory(p.getCategory());
        e.setRequiresApproval(p.isRequiresApproval());
        return e;
    }

    @Override
    public Product save(Product product) {
        ProductEntity e = repository.save(mapToEntity(product));
        return mapToDomain(e);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<Product> findByCode(String code) {
        return repository.findByCode(code).map(this::mapToDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream().map(this::mapToDomain).collect(Collectors.toList());
    }
}
