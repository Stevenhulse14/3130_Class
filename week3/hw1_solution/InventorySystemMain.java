import java.util.Scanner;
import java.util.Vector;

/**
 * Main application class for the Inventory Management System.
 */
public class InventorySystemMain {
    private ProductInventory inventory;
    private OrderManager orderManager;
    private Scanner scanner;

    public InventorySystemMain() {
        this.inventory = new ProductInventory();
        this.orderManager = new OrderManager();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        InventorySystemMain system = new InventorySystemMain();
        system.initializeSampleData();
        system.run();
    }

    /**
     * Initializes the system with sample data.
     */
    private void initializeSampleData() {
        System.out.println("Initializing system with sample data...\n");
        
        // Add sample products
        inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
        inventory.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
        inventory.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));
        inventory.addProduct(new Product("P004", "Jeans", "Clothing", 49.99, 30, "FashionInc"));
        inventory.addProduct(new Product("P005", "Keyboard", "Electronics", 79.99, 15, "TechCorp"));
        inventory.addProduct(new Product("P006", "Apple", "Food", 1.99, 100, "FreshFarm"));
        
        // Create sample orders
        Order order1 = new Order("O001", "Alice", "2024-01-15");
        order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
        order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
        order1.updateStatus("Delivered");
        orderManager.addOrder(order1);
        
        Order order2 = new Order("O002", "Bob", "2024-01-16");
        order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
        order2.updateStatus("Processing");
        orderManager.addOrder(order2);
        
        System.out.println("Sample data loaded successfully!\n");
    }

    /**
     * Main menu loop.
     */
    private void run() {
        boolean running = true;
        
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> findProduct();
                case 4 -> listAllProducts();
                case 5 -> createOrder();
                case 6 -> viewOrders();
                case 7 -> processOrder();
                case 8 -> generateReports();
                case 9 -> {
                    running = false;
                    System.out.println("Thank you for using the Inventory Management System!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    /**
     * Prints the main menu.
     */
    private void printMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    INVENTORY MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Find Product");
        System.out.println("4. List All Products");
        System.out.println("5. Create Order");
        System.out.println("6. View Orders");
        System.out.println("7. Process Order");
        System.out.println("8. Generate Reports");
        System.out.println("9. Exit");
        System.out.println("=".repeat(60));
    }

    /**
     * Adds a new product.
     */
    private void addProduct() {
        System.out.println("\n--- Add Product ---");
        String productId = getStringInput("Product ID: ");
        String name = getStringInput("Product Name: ");
        String category = getStringInput("Category: ");
        double price = getDoubleInput("Price: $");
        int quantity = getIntInput("Quantity in Stock: ");
        String supplier = getStringInput("Supplier: ");
        
        Product product = new Product(productId, name, category, price, quantity, supplier);
        inventory.addProduct(product);
        System.out.println("Product added successfully!");
    }

    /**
     * Removes a product.
     */
    private void removeProduct() {
        System.out.println("\n--- Remove Product ---");
        String productId = getStringInput("Enter Product ID to remove: ");
        
        if (inventory.removeProduct(productId)) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Finds a product.
     */
    private void findProduct() {
        System.out.println("\n--- Find Product ---");
        String productId = getStringInput("Enter Product ID: ");
        
        Product product = inventory.findProduct(productId);
        if (product != null) {
            System.out.println("\nProduct Found:");
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Lists all products.
     */
    private void listAllProducts() {
        System.out.println("\n--- All Products ---");
        inventory.printAllProducts();
        inventory.printCapacityInfo();
    }

    /**
     * Creates a new order.
     */
    private void createOrder() {
        System.out.println("\n--- Create Order ---");
        String orderId = getStringInput("Order ID: ");
        String customerName = getStringInput("Customer Name: ");
        String orderDate = getStringInput("Order Date (YYYY-MM-DD): ");
        
        Order order = new Order(orderId, customerName, orderDate);
        
        boolean addingItems = true;
        while (addingItems) {
            String productId = getStringInput("Product ID (or 'done' to finish): ");
            if ("done".equalsIgnoreCase(productId)) {
                break;
            }
            
            Product product = inventory.findProduct(productId);
            if (product == null) {
                System.out.println("Product not found. Skipping...");
                continue;
            }
            
            int quantity = getIntInput("Quantity: ");
            OrderItem item = new OrderItem(productId, product.getName(), quantity, product.getPrice());
            order.addItem(item);
        }
        
        orderManager.addOrder(order);
        System.out.println("Order created successfully!");
        order.printOrder();
    }

    /**
     * Views orders.
     */
    private void viewOrders() {
        System.out.println("\n--- View Orders ---");
        System.out.println("1. All Orders");
        System.out.println("2. Orders by Status");
        System.out.println("3. Orders by Customer");
        int choice = getIntInput("Choose option: ");
        
        switch (choice) {
            case 1 -> orderManager.printAllOrders();
            case 2 -> {
                String status = getStringInput("Enter status: ");
                Vector<Order> orders = orderManager.getOrdersByStatus(status);
                printOrders(orders);
            }
            case 3 -> {
                String customer = getStringInput("Enter customer name: ");
                Vector<Order> orders = orderManager.getOrdersByCustomer(customer);
                printOrders(orders);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    /**
     * Processes an order (updates status).
     */
    private void processOrder() {
        System.out.println("\n--- Process Order ---");
        String orderId = getStringInput("Enter Order ID: ");
        
        Order order = orderManager.findOrder(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        
        System.out.println("Current status: " + order.getOrderStatus());
        System.out.println("Status options: Pending, Processing, Shipped, Delivered, Cancelled");
        String newStatus = getStringInput("Enter new status: ");
        
        order.updateStatus(newStatus);
        System.out.println("Order status updated successfully!");
        order.printOrder();
    }

    /**
     * Generates various reports.
     */
    private void generateReports() {
        System.out.println("\n--- Generate Reports ---");
        System.out.println("1. Inventory Summary");
        System.out.println("2. Products by Category");
        System.out.println("3. Low Stock Products");
        System.out.println("4. Revenue Report");
        System.out.println("5. Capacity Report");
        System.out.println("6. Generic Methods Demo");
        int choice = getIntInput("Choose report: ");
        
        switch (choice) {
            case 1 -> {
                System.out.println("\n--- Inventory Summary ---");
                System.out.println("Total Products: " + inventory.getTotalProducts());
                System.out.printf("Total Inventory Value: $%.2f%n", inventory.getTotalInventoryValue());
                inventory.printCapacityInfo();
            }
            case 2 -> {
                String category = getStringInput("Enter category: ");
                Vector<Product> products = inventory.getProductsByCategory(category);
                System.out.println("\nProducts in category '" + category + "': " + products.size());
                for (Product p : products) {
                    System.out.println("  - " + p);
                }
            }
            case 3 -> {
                int threshold = getIntInput("Enter stock threshold: ");
                Vector<Product> lowStock = inventory.getLowStockProducts(threshold);
                System.out.println("\nLow Stock Products (threshold: " + threshold + "):");
                for (Product p : lowStock) {
                    System.out.println("  - " + p.getName() + " (Stock: " + p.getQuantityInStock() + ")");
                }
            }
            case 4 -> {
                System.out.println("\n--- Revenue Report ---");
                System.out.println("Total Orders: " + orderManager.getOrderCount());
                System.out.printf("Total Revenue (Delivered Orders): $%.2f%n", orderManager.getTotalRevenue());
                Vector<Order> pending = orderManager.getPendingOrders();
                System.out.println("Pending Orders: " + pending.size());
            }
            case 5 -> {
                inventory.printCapacityReport();
            }
            case 6 -> demonstrateGenericMethods();
            default -> System.out.println("Invalid choice.");
        }
    }

    /**
     * Demonstrates generic utility methods.
     */
    private void demonstrateGenericMethods() {
        System.out.println("\n--- Generic Methods Demonstration ---");
        
        // Get all products
        Vector<Product> allProducts = new Vector<>();
        for (int i = 0; i < inventory.getTotalProducts(); i++) {
            // We need to access products - let's use a workaround
        }
        
        // Create test vectors
        Vector<Integer> ints = new Vector<>();
        ints.add(10);
        ints.add(20);
        ints.add(30);
        ints.add(15);
        
        Vector<String> strings = new Vector<>();
        strings.add("apple");
        strings.add("banana");
        strings.add("cherry");
        strings.add("apple");
        
        System.out.println("\n1. Sum of numbers: " + VectorUtils.sumNumbers(ints));
        System.out.println("   Average: " + VectorUtils.averageNumbers(ints));
        
        System.out.println("\n2. Count matches:");
        System.out.println("   Count of 'apple' in strings: " + VectorUtils.countMatches(strings, "apple"));
        
        System.out.println("\n3. Find maximum:");
        System.out.println("   Max integer: " + VectorUtils.findMax(ints));
        System.out.println("   Max string: " + VectorUtils.findMax(strings));
        
        System.out.println("\n4. Filter demonstration:");
        Vector<String> filtered = VectorUtils.filter(strings, s -> s.length() > 5);
        System.out.println("   Strings with length > 5: " + filtered);
    }

    /**
     * Prints a list of orders.
     */
    private void printOrders(Vector<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        
        for (Order order : orders) {
            order.printOrder();
            System.out.println();
        }
    }

    /**
     * Helper method to get string input.
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Helper method to get integer input.
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    /**
     * Helper method to get double input.
     */
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}

