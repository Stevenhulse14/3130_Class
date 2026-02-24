import java.util.Vector;

/**
 * OrderManager class that manages orders using Vector.
 */
public class OrderManager {
    private Vector<Order> orders;

    /**
     * Constructor that initializes an empty Vector.
     */
    public OrderManager() {
        this.orders = new Vector<>();
    }

    /**
     * Adds an order to the manager.
     * @param order the order to add
     */
    public void addOrder(Order order) {
        if (order == null) {
            System.out.println("Error: Cannot add null order.");
            return;
        }
        
        // Check for duplicates
        if (findOrder(order.getOrderId()) != null) {
            System.out.println("Error: Order with ID " + order.getOrderId() + " already exists.");
            return;
        }
        
        orders.add(order);
    }

    /**
     * Finds an order by ID.
     * @param orderId the order ID to find
     * @return the Order if found, null otherwise
     */
    public Order findOrder(String orderId) {
        if (orderId == null) {
            return null;
        }
        
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    /**
     * Returns orders with the specified status.
     * @param status the status to filter by
     * @return Vector of orders with the status
     */
    public Vector<Order> getOrdersByStatus(String status) {
        Vector<Order> result = new Vector<>();
        if (status == null) {
            return result;
        }
        
        for (Order order : orders) {
            if (status.equals(order.getOrderStatus())) {
                result.add(order);
            }
        }
        return result;
    }

    /**
     * Returns orders for a specific customer.
     * @param customerName the customer name
     * @return Vector of orders for the customer
     */
    public Vector<Order> getOrdersByCustomer(String customerName) {
        Vector<Order> result = new Vector<>();
        if (customerName == null) {
            return result;
        }
        
        for (Order order : orders) {
            if (customerName.equals(order.getCustomerName())) {
                result.add(order);
            }
        }
        return result;
    }

    /**
     * Calculates total revenue from all delivered orders.
     * @return total revenue
     */
    public double getTotalRevenue() {
        double total = 0.0;
        for (Order order : orders) {
            if ("Delivered".equals(order.getOrderStatus())) {
                total += order.calculateTotal();
            }
        }
        return total;
    }

    /**
     * Cancels an order by updating its status.
     * @param orderId the order ID to cancel
     */
    public void cancelOrder(String orderId) {
        Order order = findOrder(orderId);
        if (order == null) {
            System.out.println("Error: Order with ID " + orderId + " not found.");
            return;
        }
        
        order.updateStatus("Cancelled");
        System.out.println("Order " + orderId + " has been cancelled.");
    }

    /**
     * Prints all orders.
     */
    public void printAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALL ORDERS");
        System.out.println("=".repeat(80));
        
        for (Order order : orders) {
            order.printOrder();
            System.out.println();
        }
    }

    /**
     * Returns pending orders.
     * @return Vector of pending orders
     */
    public Vector<Order> getPendingOrders() {
        return getOrdersByStatus("Pending");
    }

    /**
     * Returns the number of orders.
     * @return order count
     */
    public int getOrderCount() {
        return orders.size();
    }
}

