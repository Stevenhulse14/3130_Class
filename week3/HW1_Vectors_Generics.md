# Homework 1: Vector-Based Inventory Management System

## Due Date
**See course schedule**

## Learning Objectives

By completing this homework, you will:
- Master Vector operations and methods
- Understand Vector's thread-safety characteristics
- Work with Vector capacity management
- Apply generics to create type-safe collections
- Build a complete application using Vectors
- Compare Vector performance and behavior with other collections
- Use generics to create reusable, type-safe code

## Assignment Overview

You will build an **Inventory Management System** for a store that uses Vectors to manage products, orders, and customers. The system will demonstrate Vector capabilities and gradually introduce generics concepts.

---

## Part 1: Product Management (Vectors Basics)

### Task 1.1: Product Class

Create a `Product` class with the following:

**Fields:**
- `productId` (String) - unique identifier
- `name` (String) - product name
- `category` (String) - product category (e.g., "Electronics", "Clothing", "Food")
- `price` (double) - product price
- `quantityInStock` (int) - current stock quantity
- `supplier` (String) - supplier name

**Methods:**
- Constructor(s) - at least one that takes all parameters
- Getters and setters for all fields
- `toString()` - returns formatted string representation
- `equals(Object obj)` - compares products by productId
- `hashCode()` - consistent with equals

### Task 1.2: ProductInventory Class

Create a `ProductInventory` class that uses a `Vector<Product>` to manage products:

**Field:**
- `Vector<Product> products` - stores all products

**Methods:**
1. `void addProduct(Product product)` - adds a product (check for duplicates by productId)
2. `boolean removeProduct(String productId)` - removes product by ID, returns true if found
3. `Product findProduct(String productId)` - finds and returns product, or null if not found
4. `Vector<Product> getProductsByCategory(String category)` - returns Vector of products in category
5. `Vector<Product> getLowStockProducts(int threshold)` - returns products with quantity < threshold
6. `double getTotalInventoryValue()` - calculates total value (price × quantity for all products)
7. `void updateStock(String productId, int quantityChange)` - updates stock (can be positive or negative)
8. `void printAllProducts()` - prints all products in a formatted table
9. `int getTotalProducts()` - returns number of products
10. `void printCapacityInfo()` - prints current size and capacity of the Vector

**Requirements:**
- Use Vector methods (add, remove, get, size, capacity, etc.)
- Handle edge cases (empty inventory, product not found, etc.)
- Include proper error handling and validation

**Test your implementation:**
```java
ProductInventory inventory = new ProductInventory();

inventory.addProduct(new Product("P001", "Laptop", "Electronics", 999.99, 10, "TechCorp"));
inventory.addProduct(new Product("P002", "T-Shirt", "Clothing", 19.99, 50, "FashionInc"));
inventory.addProduct(new Product("P003", "Mouse", "Electronics", 29.99, 5, "TechCorp"));

inventory.printAllProducts();
inventory.printCapacityInfo();

Vector<Product> electronics = inventory.getProductsByCategory("Electronics");
System.out.println("Electronics: " + electronics.size());

Vector<Product> lowStock = inventory.getLowStockProducts(10);
System.out.println("Low stock items: " + lowStock.size());

System.out.println("Total inventory value: $" + inventory.getTotalInventoryValue());
```

---

## Part 2: Order Management (Vector Operations)

### Task 2.1: OrderItem Class

Create an `OrderItem` class:

**Fields:**
- `productId` (String)
- `productName` (String)
- `quantity` (int)
- `unitPrice` (double)
- `subtotal` (double) - calculated as quantity × unitPrice

**Methods:**
- Constructor(s)
- Getters and setters
- `toString()`
- `double calculateSubtotal()` - calculates and updates subtotal

### Task 2.2: Order Class

Create an `Order` class:

**Fields:**
- `orderId` (String) - unique order identifier
- `customerName` (String)
- `orderDate` (String) - simple string format "YYYY-MM-DD"
- `Vector<OrderItem> items` - items in the order
- `orderStatus` (String) - "Pending", "Processing", "Shipped", "Delivered", "Cancelled"

**Methods:**
1. `void addItem(OrderItem item)` - adds item to order
2. `boolean removeItem(String productId)` - removes item by productId
3. `OrderItem findItem(String productId)` - finds item in order
4. `double calculateTotal()` - calculates total order value
5. `int getTotalItems()` - returns total quantity of all items
6. `void updateStatus(String newStatus)` - updates order status
7. `void printOrder()` - prints formatted order details
8. `Vector<OrderItem> getItems()` - returns copy of items Vector

### Task 2.3: OrderManager Class

Create an `OrderManager` class using `Vector<Order>`:

**Field:**
- `Vector<Order> orders`

**Methods:**
1. `void addOrder(Order order)` - adds order
2. `Order findOrder(String orderId)` - finds order by ID
3. `Vector<Order> getOrdersByStatus(String status)` - returns orders with specific status
4. `Vector<Order> getOrdersByCustomer(String customerName)` - returns customer's orders
5. `double getTotalRevenue()` - calculates total revenue from all delivered orders
6. `void cancelOrder(String orderId)` - cancels order (updates status)
7. `void printAllOrders()` - prints all orders
8. `Vector<Order> getPendingOrders()` - returns pending orders
9. `int getOrderCount()` - returns number of orders

**Test:**
```java
OrderManager orderManager = new OrderManager();

Order order1 = new Order("O001", "Alice", "2024-01-15");
order1.addItem(new OrderItem("P001", "Laptop", 1, 999.99));
order1.addItem(new OrderItem("P003", "Mouse", 2, 29.99));
orderManager.addOrder(order1);

Order order2 = new Order("O002", "Bob", "2024-01-16");
order2.addItem(new OrderItem("P002", "T-Shirt", 3, 19.99));
orderManager.addOrder(order2);

orderManager.printAllOrders();
System.out.println("Total revenue: $" + orderManager.getTotalRevenue());
```

---

## Part 3: Vector-Specific Features

### Task 3.1: Capacity Management

In your `ProductInventory` class, add methods:

1. `void optimizeCapacity()` - uses `trimToSize()` to reduce capacity to match size
2. `void ensureCapacity(int minCapacity)` - ensures Vector has at least minCapacity
3. `void printCapacityReport()` - prints detailed capacity information:
   - Current size
   - Current capacity
   - Capacity utilization percentage
   - How many elements can be added before resize

### Task 3.2: Enumeration Usage

Add a method to `ProductInventory`:

```java
public void printProductsUsingEnumeration()
```

- Uses Vector's `elements()` method to get `Enumeration<Product>`
- Iterates and prints all products
- Include a comment explaining why Enumeration is legacy and when to use it vs Iterator

### Task 3.3: Vector Comparison

Create a `VectorComparisonDemo` class:

1. Create both a `Vector<Product>` and `ArrayList<Product>` with the same products
2. Perform the same operations on both (add, remove, get, etc.)
3. Measure and compare:
   - Time to add 10,000 products
   - Time to access 1,000 random elements
   - Memory usage (approximate)
4. Print a comparison report

**Write a summary** explaining:
- Performance differences observed
- When you would choose Vector over ArrayList
- When you would choose ArrayList over Vector

---

## Part 4: Introduction to Generics (Hints)

### Task 4.1: Generic Utility Methods

Create a `VectorUtils` class with generic methods:

#### Method 1: Generic Swap
```java
public static <T> void swap(Vector<T> vec, int index1, int index2)
```
- Swaps two elements in any Vector
- Works with `Vector<Product>`, `Vector<Order>`, `Vector<String>`, etc.

#### Method 2: Generic Find Maximum
```java
public static <T extends Comparable<T>> T findMax(Vector<T> vec)
```
- Finds maximum element in Vector
- Works with any type that implements `Comparable`
- Test with `Vector<Integer>`, `Vector<String>`, `Vector<Product>` (you'll need to make Product implement `Comparable<Product>`)

#### Method 3: Generic Count
```java
public static <T> int countMatches(Vector<T> vec, T target)
```
- Counts occurrences of target in Vector
- Uses `equals()` for comparison

#### Method 4: Generic Filter
```java
public static <T> Vector<T> filter(Vector<T> vec, Predicate<T> condition)
```
- Returns new Vector containing elements that match condition
- **Note**: You'll need to create a simple `Predicate` interface or use a lambda (if you know lambdas)

**Alternative simpler version:**
```java
public static Vector<Product> filterProducts(Vector<Product> vec, String category)
```
- Filters products by category
- This is the non-generic version - try to make it generic!

### Task 4.2: Generic Container Class

Create a `GenericContainer<T>` class:

**Purpose**: A wrapper around `Vector<T>` that adds some utility methods

**Field:**
- `Vector<T> items`

**Methods:**
1. `void add(T item)` - adds item
2. `T get(int index)` - gets item
3. `boolean remove(T item)` - removes item
4. `int size()` - returns size
5. `Vector<T> getAll()` - returns copy of all items
6. `void clear()` - clears container
7. `boolean contains(T item)` - checks if contains item
8. `void addAll(Vector<T> other)` - adds all from another Vector

**Test with different types:**
```java
GenericContainer<String> stringContainer = new GenericContainer<>();
stringContainer.add("Hello");
stringContainer.add("World");

GenericContainer<Integer> intContainer = new GenericContainer<>();
intContainer.add(10);
intContainer.add(20);

GenericContainer<Product> productContainer = new GenericContainer<>();
productContainer.add(new Product(...));
```

### Task 4.3: Bounded Generics

Add methods to `VectorUtils` that work only with Numbers:

#### Method: Sum Numbers
```java
public static <T extends Number> double sumNumbers(Vector<T> numbers)
```
- Takes `Vector<Integer>`, `Vector<Double>`, etc.
- Returns sum as double
- Uses `number.doubleValue()` to convert

#### Method: Average Numbers
```java
public static <T extends Number> double averageNumbers(Vector<T> numbers)
```
- Calculates average
- Returns 0.0 if empty

**Test:**
```java
Vector<Integer> ints = new Vector<>();
ints.add(10);
ints.add(20);
ints.add(30);
System.out.println("Sum: " + VectorUtils.sumNumbers(ints));  // 60.0
System.out.println("Average: " + VectorUtils.averageNumbers(ints));  // 20.0
```

---

## Part 5: Integration and Main Application

### Task 5.1: Main Application Class

Create `InventorySystemMain.java` that demonstrates your complete system:

**Features:**
1. Create a `ProductInventory` and add sample products
2. Create an `OrderManager` and add sample orders
3. Demonstrate all major operations:
   - Adding/removing products
   - Finding products
   - Creating orders
   - Processing orders
   - Generating reports
4. Show Vector capacity management
5. Demonstrate generic utility methods
6. Print comprehensive reports

**Menu System (Optional but Recommended):**
Create an interactive menu:
```
1. Add Product
2. Remove Product
3. Find Product
4. List All Products
5. Create Order
6. View Orders
7. Process Order
8. Generate Reports
9. Exit
```

### Task 5.2: Comprehensive Testing

Create test cases that verify:
- All Vector operations work correctly
- Edge cases are handled (empty Vectors, invalid indices, etc.)
- Generic methods work with different types
- Capacity management functions properly
- Thread-safety considerations (if you attempted Part 6)

---

## Part 6: Thread Safety Demonstration (Bonus)

### Task 6.1: Multi-threaded Test

Create `ThreadSafetyDemo.java`:

1. Create a shared `Vector<Product>` and `ArrayList<Product>`
2. Create multiple threads that:
   - Add products simultaneously
   - Remove products simultaneously
   - Read products simultaneously
3. Run threads and observe:
   - Vector should handle concurrent access safely
   - ArrayList may produce errors or incorrect results
4. Measure performance difference
5. Write a report explaining findings

**Note**: This is bonus - only attempt if you understand basic threading concepts.

---

## Submission Requirements

### Files to Submit

1. `Product.java`
2. `ProductInventory.java`
3. `OrderItem.java`
4. `Order.java`
5. `OrderManager.java`
6. `VectorUtils.java` (generic utility methods)
7. `GenericContainer.java`
8. `VectorComparisonDemo.java`
9. `InventorySystemMain.java` (main application)
10. `ThreadSafetyDemo.java` (bonus, if completed)
11. `README.txt` - brief explanation of your implementation

### Code Requirements

- All code must compile without errors
- All code must run and produce expected output
- Code must be properly commented
- Use meaningful variable and method names
- Follow Java naming conventions
- Handle edge cases appropriately
- Include proper error handling

### Documentation

Your `README.txt` should include:
- Brief description of each class
- How to compile and run your program
- Any assumptions you made
- Challenges you encountered
- What you learned about Vectors and generics

---

---

## Hints and Tips

1. **Start Early**: This is a comprehensive assignment. Don't wait until the last minute.

2. **Test Incrementally**: Test each class and method as you write it. Don't write everything then test.

3. **Vector Methods**: Review Vector API documentation. Methods are similar to ArrayList but Vector has additional features.

4. **Generics**: Start with simple generics (`Vector<String>`) before attempting bounded generics (`<T extends Comparable<T>>`).

5. **Capacity**: Don't worry too much about capacity optimization unless you're working with very large datasets.

6. **Thread Safety**: The bonus thread safety part is optional. Focus on the main requirements first.

7. **Error Handling**: Think about what happens if:
   - Vector is empty
   - Index is out of bounds
   - Product/Order not found
   - Invalid input

8. **Testing**: Create comprehensive test cases. Test with:
   - Empty Vectors
   - Single element
   - Multiple elements
   - Edge cases

---

## Learning Outcomes

After completing this homework, you should be able to:

✅ Use Vector methods confidently  
✅ Understand Vector's thread-safety characteristics  
✅ Manage Vector capacity effectively  
✅ Use generics to create type-safe code  
✅ Create generic methods and classes  
✅ Understand when to use Vector vs ArrayList  
✅ Build a complete application using collections  

---

## Questions?

If you have questions about this assignment, please:
1. Review the notes and examples from class
2. Check the Java API documentation for Vector
3. Ask your instructor during office hours or via email

**Good luck!**

