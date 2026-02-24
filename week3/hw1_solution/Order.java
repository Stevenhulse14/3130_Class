import java.util.Vector;

/**
 * Order class representing a customer order.
 */
public class Order {
    private String orderId;
    private String customerName;
    private String orderDate;
    private Vector<OrderItem> items;
    private String orderStatus;

    /**
     * Constructor that initializes order with basic information.
     */
    public Order(String orderId, String customerName, String orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.items = new Vector<>();
        this.orderStatus = "Pending";
    }

    /**
     * Adds an item to the order.
     */
    public void addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
        }
    }

    /**
     * Removes an item from the order by productId.
     * @param productId the product ID to remove
     * @return true if item was found and removed, false otherwise
     */
    public boolean removeItem(String productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProductId().equals(productId)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds an item in the order by productId.
     * @param productId the product ID to find
     * @return the OrderItem if found, null otherwise
     */
    public OrderItem findItem(String productId) {
        for (OrderItem item : items) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Calculates the total value of the order.
     * @return the total order value
     */
    public double calculateTotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Returns the total quantity of all items in the order.
     * @return total quantity
     */
    public int getTotalItems() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    /**
     * Updates the order status.
     * @param newStatus the new status
     */
    public void updateStatus(String newStatus) {
        if (newStatus != null && !newStatus.trim().isEmpty()) {
            this.orderStatus = newStatus;
        }
    }

    /**
     * Returns a copy of the items Vector.
     * @return a new Vector containing all items
     */
    public Vector<OrderItem> getItems() {
        Vector<OrderItem> copy = new Vector<>();
        copy.addAll(items);
        return copy;
    }

    /**
     * Prints formatted order details.
     */
    public void printOrder() {
        System.out.println("=".repeat(60));
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + orderDate);
        System.out.println("Status: " + orderStatus);
        System.out.println("-".repeat(60));
        System.out.printf("%-15s %-20s %-10s %-12s %-12s%n", 
                         "Product ID", "Product Name", "Quantity", "Unit Price", "Subtotal");
        System.out.println("-".repeat(60));
        
        for (OrderItem item : items) {
            System.out.printf("%-15s %-20s %-10d $%-11.2f $%-11.2f%n",
                            item.getProductId(), item.getProductName(), 
                            item.getQuantity(), item.getUnitPrice(), item.getSubtotal());
        }
        
        System.out.println("-".repeat(60));
        System.out.printf("Total Items: %d%n", getTotalItems());
        System.out.printf("Order Total: $%.2f%n", calculateTotal());
        System.out.println("=".repeat(60));
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}

