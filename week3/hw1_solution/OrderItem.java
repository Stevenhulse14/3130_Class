/**
 * OrderItem class representing an item in an order.
 */
public class OrderItem {
    private String productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;

    /**
     * Constructor that takes all parameters and calculates subtotal.
     */
    public OrderItem(String productId, String productName, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = calculateSubtotal();
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    // Setters
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = calculateSubtotal(); // Recalculate subtotal when quantity changes
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.subtotal = calculateSubtotal(); // Recalculate subtotal when price changes
    }

    /**
     * Calculates and updates the subtotal.
     * @return the calculated subtotal
     */
    public double calculateSubtotal() {
        this.subtotal = quantity * unitPrice;
        return this.subtotal;
    }

    @Override
    public String toString() {
        return String.format("OrderItem{ProductID: %s, Name: %s, Quantity: %d, " +
                           "UnitPrice: $%.2f, Subtotal: $%.2f}", 
                           productId, productName, quantity, unitPrice, subtotal);
    }
}

