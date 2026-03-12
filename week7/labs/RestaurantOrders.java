/**
 * Lab 2: Restaurant Order System
 * 
 * This program demonstrates the use of a Queue data structure
 * to manage restaurant orders. Orders are processed in FIFO
 * (First In First Out) order, ensuring fairness in order preparation.
 * 
 * @author Student Name
 * @version 1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * RestaurantOrders class manages order processing using a Queue.
 * 
 * The queue ensures that orders are prepared in the order they
 * were received, which is essential for fair restaurant operations.
 */
class RestaurantOrders {
    private Queue<String> orders;
    
    /**
     * Constructor initializes an empty order queue.
     */
    public RestaurantOrders() {
        this.orders = new LinkedList<>();
    }
    
    /**
     * Adds a new order to the queue.
     * 
     * Orders are added to the back of the queue and will be
     * processed in the order they were received.
     * 
     * @param order The name/description of the order
     */
    public void addOrder(String order) {
        orders.add(order);
        System.out.println("New order: " + order);
    }
    
    /**
     * Processes the next order in the queue.
     * 
     * Removes and processes the order at the front of the queue.
     * If no orders are available, displays an appropriate message.
     */
    public void prepareOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders to prepare");
            return;
        }
        
        String order = orders.poll();
        System.out.println("Preparing: " + order);
    }
    
    /**
     * Displays the next order without removing it from the queue.
     * 
     * This allows the kitchen to see what's coming up next
     * without actually processing it yet.
     */
    public void showNextOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders waiting");
        } else {
            System.out.println("Next order: " + orders.peek());
        }
    }
    
    /**
     * Displays all pending orders in the queue.
     * 
     * Shows the order in which orders will be processed.
     */
    public void showAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No pending orders");
            return;
        }
        
        System.out.println("Pending orders (in processing order):");
        int position = 1;
        for (String order : orders) {
            System.out.println("  " + position + ". " + order);
            position++;
        }
    }
    
    /**
     * Returns the number of pending orders.
     * 
     * @return The size of the orders queue
     */
    public int getPendingOrderCount() {
        return orders.size();
    }
    
    /**
     * Checks if there are any pending orders.
     * 
     * @return true if orders are waiting, false otherwise
     */
    public boolean hasOrders() {
        return !orders.isEmpty();
    }
}

/**
 * Main class to test the RestaurantOrders system.
 */
public class RestaurantOrdersMain {
    public static void main(String[] args) {
        RestaurantOrders system = new RestaurantOrders();
        
        // Add orders
        System.out.println("=== Taking Orders ===");
        system.addOrder("Burger");
        system.addOrder("Pizza");
        system.addOrder("Salad");
        system.addOrder("Soup");
        
        System.out.println("\n=== Order Status ===");
        system.showAllOrders();
        System.out.println("Total pending: " + system.getPendingOrderCount());
        
        System.out.println("\n=== Processing Orders ===");
        system.showNextOrder();
        system.prepareOrder();
        
        system.showNextOrder();
        system.prepareOrder();
        
        System.out.println("\n=== Remaining Orders ===");
        system.showAllOrders();
        
        System.out.println("\n=== Continue Processing ===");
        system.prepareOrder();
        system.prepareOrder();
        
        // Test edge case: processing when no orders
        System.out.println("\n=== Edge Case Test ===");
        system.prepareOrder(); // Should show "No orders to prepare"
    }
}
