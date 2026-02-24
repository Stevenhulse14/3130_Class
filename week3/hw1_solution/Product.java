/**
 * Product class representing a product in the inventory system.
 * Implements Comparable for use with generic methods.
 */
public class Product implements Comparable<Product> {
    private String productId;
    private String name;
    private String category;
    private double price;
    private int quantityInStock;
    private String supplier;

    /**
     * Constructor that takes all parameters.
     */
    public Product(String productId, String name, String category, double price, 
                   int quantityInStock, String supplier) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.supplier = supplier;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getSupplier() {
        return supplier;
    }

    // Setters
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * Returns formatted string representation of the product.
     */
    @Override
    public String toString() {
        return String.format("Product{ID: %s, Name: %s, Category: %s, Price: $%.2f, " +
                           "Stock: %d, Supplier: %s}", 
                           productId, name, category, price, quantityInStock, supplier);
    }

    /**
     * Compares products by productId.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId != null ? productId.equals(product.productId) : product.productId == null;
    }

    /**
     * Hash code consistent with equals.
     */
    @Override
    public int hashCode() {
        return productId != null ? productId.hashCode() : 0;
    }

    /**
     * Compares products by price for Comparable interface.
     */
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }
}

