import java.util.Enumeration;
import java.util.Vector;

/**
 * ProductInventory class that manages products using Vector.
 */
public class ProductInventory {
    private Vector<Product> products;

    /**
     * Constructor that initializes an empty Vector.
     */
    public ProductInventory() {
        this.products = new Vector<>();
    }

    /**
     * Constructor with initial capacity.
     */
    public ProductInventory(int initialCapacity) {
        this.products = new Vector<>(initialCapacity);
    }

    /**
     * Adds a product to the inventory, checking for duplicates by productId.
     * @param product the product to add
     */
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("Error: Cannot add null product.");
            return;
        }
        
        // Check for duplicates
        if (findProduct(product.getProductId()) != null) {
            System.out.println("Error: Product with ID " + product.getProductId() + " already exists.");
            return;
        }
        
        products.add(product);
    }

    /**
     * Removes a product by ID.
     * @param productId the product ID to remove
     * @return true if product was found and removed, false otherwise
     */
    public boolean removeProduct(String productId) {
        if (productId == null) {
            return false;
        }
        
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a product by ID.
     * @param productId the product ID to find
     * @return the Product if found, null otherwise
     */
    public Product findProduct(String productId) {
        if (productId == null) {
            return null;
        }
        
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Returns a Vector of products in the specified category.
     * @param category the category to filter by
     * @return Vector of products in the category
     */
    public Vector<Product> getProductsByCategory(String category) {
        Vector<Product> result = new Vector<>();
        if (category == null) {
            return result;
        }
        
        for (Product product : products) {
            if (category.equals(product.getCategory())) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Returns products with quantity below the threshold.
     * @param threshold the stock threshold
     * @return Vector of low stock products
     */
    public Vector<Product> getLowStockProducts(int threshold) {
        Vector<Product> result = new Vector<>();
        for (Product product : products) {
            if (product.getQuantityInStock() < threshold) {
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Calculates the total inventory value (price × quantity for all products).
     * @return total inventory value
     */
    public double getTotalInventoryValue() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantityInStock();
        }
        return total;
    }

    /**
     * Updates stock quantity for a product.
     * @param productId the product ID
     * @param quantityChange the change in quantity (can be positive or negative)
     */
    public void updateStock(String productId, int quantityChange) {
        Product product = findProduct(productId);
        if (product == null) {
            System.out.println("Error: Product with ID " + productId + " not found.");
            return;
        }
        
        int newQuantity = product.getQuantityInStock() + quantityChange;
        if (newQuantity < 0) {
            System.out.println("Warning: Stock cannot go below 0. Setting to 0.");
            newQuantity = 0;
        }
        
        product.setQuantityInStock(newQuantity);
    }

    /**
     * Prints all products in a formatted table.
     */
    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in inventory.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(100));
        System.out.printf("%-12s %-20s %-15s %-12s %-10s %-20s%n", 
                         "Product ID", "Name", "Category", "Price", "Stock", "Supplier");
        System.out.println("-".repeat(100));
        
        for (Product product : products) {
            System.out.printf("%-12s %-20s %-15s $%-11.2f %-10d %-20s%n",
                            product.getProductId(), product.getName(), product.getCategory(),
                            product.getPrice(), product.getQuantityInStock(), product.getSupplier());
        }
        
        System.out.println("=".repeat(100));
    }

    /**
     * Returns the total number of products.
     * @return number of products
     */
    public int getTotalProducts() {
        return products.size();
    }

    /**
     * Prints current size and capacity of the Vector.
     */
    public void printCapacityInfo() {
        System.out.println("\nVector Capacity Information:");
        System.out.println("Current Size: " + products.size());
        System.out.println("Current Capacity: " + products.capacity());
        System.out.println("Capacity Utilization: " + 
                         String.format("%.2f%%", (products.size() * 100.0 / products.capacity())));
    }

    /**
     * Optimizes capacity by trimming to size.
     */
    public void optimizeCapacity() {
        products.trimToSize();
        System.out.println("Capacity optimized. New capacity: " + products.capacity());
    }

    /**
     * Ensures Vector has at least the specified minimum capacity.
     * @param minCapacity the minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        products.ensureCapacity(minCapacity);
        System.out.println("Capacity ensured to be at least: " + minCapacity);
    }

    /**
     * Prints detailed capacity report.
     */
    public void printCapacityReport() {
        int size = products.size();
        int capacity = products.capacity();
        double utilization = capacity > 0 ? (size * 100.0 / capacity) : 0.0;
        int remaining = capacity - size;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Capacity Report");
        System.out.println("-".repeat(50));
        System.out.println("Current Size: " + size);
        System.out.println("Current Capacity: " + capacity);
        System.out.printf("Capacity Utilization: %.2f%%%n", utilization);
        System.out.println("Elements before resize: " + remaining);
        System.out.println("=".repeat(50));
    }

    /**
     * Prints products using Enumeration (legacy iteration method).
     * 
     * Note: Enumeration is a legacy interface from Java 1.0. It's similar to Iterator
     * but lacks the remove() method. Enumeration is still used by Vector's elements()
     * method for backward compatibility. In modern Java, Iterator is preferred because:
     * 1. It's part of the Collections framework
     * 2. It supports remove() operation
     * 3. It's more consistent with other collections
     * Use Enumeration only when working with legacy code or Vector's elements() method.
     */
    public void printProductsUsingEnumeration() {
        System.out.println("\nPrinting products using Enumeration (legacy method):");
        Enumeration<Product> enumeration = products.elements();
        
        int index = 1;
        while (enumeration.hasMoreElements()) {
            Product product = enumeration.nextElement();
            System.out.println(index + ". " + product);
            index++;
        }
    }
}

