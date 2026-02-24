================================================================================
INVENTORY MANAGEMENT SYSTEM - HOMEWORK 1 SOLUTION
================================================================================

DESCRIPTION
-----------
This is a complete implementation of an Inventory Management System using Java
Vectors and Generics. The system manages products, orders, and demonstrates
various Vector operations, capacity management, and generic programming concepts.

CLASSES IMPLEMENTED
-------------------
1. Product.java
   - Represents a product with ID, name, category, price, stock, and supplier
   - Implements Comparable<Product> for use with generic methods
   - Includes equals(), hashCode(), and toString() methods

2. ProductInventory.java
   - Manages products using Vector<Product>
   - Provides methods for adding, removing, finding products
   - Supports filtering by category and low stock detection
   - Includes capacity management methods
   - Demonstrates Enumeration usage (legacy iteration)

3. OrderItem.java
   - Represents an item in an order
   - Contains product information and quantity
   - Calculates subtotal automatically

4. Order.java
   - Represents a customer order
   - Uses Vector<OrderItem> to store order items
   - Supports status management (Pending, Processing, Shipped, etc.)
   - Calculates order totals

5. OrderManager.java
   - Manages orders using Vector<Order>
   - Provides filtering by status and customer
   - Calculates total revenue from delivered orders
   - Supports order cancellation

6. VectorUtils.java
   - Generic utility methods for working with Vectors
   - Includes swap, findMax, countMatches, filter methods
   - Bounded generics for Number operations (sum, average)
   - Demonstrates both generic and non-generic approaches

7. GenericContainer.java
   - Generic wrapper class around Vector<T>
   - Works with any type (String, Integer, Product, etc.)
   - Provides utility methods for container operations

8. VectorComparisonDemo.java
   - Compares Vector and ArrayList performance
   - Measures add and access operations
   - Provides recommendations on when to use each

9. InventorySystemMain.java
   - Main application with interactive menu system
   - Demonstrates all features of the inventory system
   - Includes sample data initialization
   - Comprehensive testing interface

10. ThreadSafetyDemo.java (Bonus)
    - Demonstrates Vector's thread-safety vs ArrayList
    - Shows concurrent access scenarios
    - Illustrates why Vector is thread-safe

HOW TO COMPILE AND RUN
----------------------
1. Compile all Java files:
   javac *.java

2. Run the main application:
   java InventorySystemMain

3. Run the comparison demo:
   java VectorComparisonDemo

4. Run the thread safety demo (bonus):
   java ThreadSafetyDemo

ASSUMPTIONS
-----------
- Product IDs are unique identifiers
- Order IDs are unique identifiers
- Stock quantities cannot go negative (set to 0 if update would make it negative)
- Order dates are in "YYYY-MM-DD" format
- All prices are in USD

FEATURES IMPLEMENTED
--------------------
✓ Complete Product management with Vector operations
✓ Order management system
✓ Vector capacity management (ensureCapacity, trimToSize)
✓ Enumeration usage demonstration
✓ Vector vs ArrayList performance comparison
✓ Generic utility methods (swap, findMax, countMatches, filter)
✓ Generic container class
✓ Bounded generics (Number operations)
✓ Interactive menu system
✓ Comprehensive error handling
✓ Thread safety demonstration (bonus)

CHALLENGES ENCOUNTERED
----------------------
1. Generic method syntax - learning the <T> and <T extends Comparable<T>> syntax
2. Bounded generics - understanding when to use extends vs super
3. Vector capacity management - understanding when capacity vs size matters
4. Enumeration vs Iterator - understanding legacy vs modern approaches
5. Thread safety demonstration - ensuring proper thread synchronization testing

WHAT I LEARNED
--------------
1. Vector Operations:
   - Vector is synchronized (thread-safe) but slower than ArrayList
   - Capacity management is important for large datasets
   - Enumeration is legacy but still useful for Vector's elements() method

2. Generics:
   - Generic methods allow code reuse across different types
   - Bounded generics (<T extends Number>) restrict type parameters
   - Comparable interface enables generic comparison operations
   - Type safety prevents ClassCastException at runtime

3. Performance:
   - Vector's synchronization adds overhead
   - ArrayList is preferred for single-threaded applications
   - Vector is useful when thread-safety is needed without manual synchronization

4. Best Practices:
   - Use ArrayList for new code unless thread-safety is required
   - Use Vector when working with legacy code or need thread-safety
   - Generic methods make code more reusable and type-safe
   - Proper error handling is crucial for robust applications

TESTING
-------
The solution includes:
- Sample data initialization in InventorySystemMain
- Comprehensive menu system for interactive testing
- VectorComparisonDemo for performance testing
- ThreadSafetyDemo for concurrent access testing

All classes compile without errors and run successfully.

================================================================================
END OF README
================================================================================

