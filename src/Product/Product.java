package Product;

public class Product {

    static int numID = 0;
    private int productId;
    private String name;
    private String description;
    private Category category;

    public Product(String name, String description, Category category) {
        Product.numID += 1;
        this.productId = Product.numID;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
