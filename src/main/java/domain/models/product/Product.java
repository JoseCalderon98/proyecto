package domain.models.product;

public class Product {
    private Integer id;
    private String code;
    private String name;
    private ProductCategory category;
    private boolean requiresApproval;

    public Product() {}

    public Product(String code, String name, ProductCategory category, boolean requiresApproval) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.requiresApproval = requiresApproval;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public boolean isRequiresApproval() {
        return requiresApproval;
    }

    public void setRequiresApproval(boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }
}
