# Basic Vectors Assignment

## Learning Objectives

By completing this assignment, you will:
- Understand how to create and manipulate Vectors
- Practice using Vector methods (add, remove, get, set, size, capacity)
- Learn about thread-safety and synchronization
- Compare Vectors with ArrayLists
- Understand when to use Vectors vs ArrayLists
- Work with Vector-specific features (capacity, Enumeration)

## Prerequisites

- Completion of Basic ArrayLists Assignment
- Understanding of ArrayLists
- Basic knowledge of threads (conceptual understanding)

---

## Exercise 1: Vector Basics

Create a file named `VectorBasics.java` and complete the following:

### Part A: Basic Vector Operations

1. Create an empty `Vector<String>` called `items`
2. Add the following items: "Sword", "Shield", "Potion", "Map", "Key"
3. Print the Vector (should display nicely like ArrayList!)
4. Print both the **size** and **capacity** of the Vector
5. Get and print the element at index 2
6. Change the element at index 1 to "Magic Shield"
7. Remove "Potion" from the Vector
8. Print the Vector again along with size and capacity

**Expected Output:**
```
Items: [Sword, Shield, Potion, Map, Key]
Size: 5
Capacity: 10
Element at index 2: Potion
Items after changes: [Sword, Magic Shield, Map, Key]
Size: 4
Capacity: 10
```

**Note**: Notice that capacity doesn't decrease when you remove elements. Use `trimToSize()` if you want to reduce capacity.

### Part B: Capacity Management

1. Create a `Vector<Integer>` with initial capacity of 5
2. Add 10 numbers (0 through 9)
3. Print size and capacity after each addition (or at key points)
4. Observe how capacity grows automatically
5. Use `trimToSize()` to reduce capacity to match size
6. Print size and capacity again

**Discussion**: Why does Vector have a capacity that's different from size? How does this differ from ArrayList?

---

## Exercise 2: Vector vs ArrayList Comparison

Create a file named `VectorVsArrayList.java` to demonstrate similarities and differences:

### Task

Create side-by-side comparisons:

1. **Basic Operations**: Show that Vector and ArrayList have nearly identical APIs
   - Create both with same initial values
   - Perform same operations (add, get, set, remove)
   - Show they produce same results

2. **Capacity Feature**: 
   - Show Vector's `capacity()` method
   - Show that ArrayList doesn't have this (or has it hidden)
   - Demonstrate `ensureCapacity()` and `trimToSize()` on Vector

3. **Enumeration (Legacy Feature)**:
   - Show Vector's `elements()` method returning `Enumeration`
   - Compare with modern `Iterator` approach
   - Show both work, but Enumeration is legacy

**Code Example:**
```java
Vector<String> vec = new Vector<>();
vec.add("A");
vec.add("B");

// Modern way
for (String s : vec) {
    System.out.println(s);
}

// Legacy way
Enumeration<String> e = vec.elements();
while (e.hasMoreElements()) {
    System.out.println(e.nextElement());
}
```

**Write comments explaining**: Why Vector has these legacy features, and why ArrayList is generally preferred.

---

## Exercise 3: Vector Operations

Create a file named `VectorOperations.java` and implement the following methods:

### Method 1: Remove All Occurrences
```java
public static void removeAll(Vector<String> vec, String target)
```
- Takes a Vector and a target string
- Removes ALL occurrences of the target (not just the first one)
- Modifies the Vector in-place
- Example: `["a", "b", "a", "c", "a"]` with target "a" → `["b", "c"]`

**Hint**: Be careful when removing during iteration! Consider iterating backwards or using `removeAll(Collections.singleton(target))`.

### Method 2: Insert at Multiple Positions
```java
public static void insertAtPositions(Vector<Integer> vec, int value, int... positions)
```
- Takes a Vector, a value, and variable number of positions
- Inserts the value at each specified position
- Example: Vector `[10, 20, 30]`, value `99`, positions `0, 2`
  - Result: `[99, 10, 20, 99, 30]`

**Note**: Inserting at multiple positions requires careful index management (insert from highest index to lowest).

### Method 3: Get Capacity Info
```java
public static void printCapacityInfo(Vector<?> vec)
```
- Takes any Vector
- Prints: current size, current capacity, and how many more elements can be added before capacity increases
- Example output: `Size: 5, Capacity: 10, Can add 5 more before resize`

### Method 4: Copy Vector Range
```java
public static Vector<String> copyRange(Vector<String> vec, int start, int end)
```
- Takes a Vector and two indices
- Returns a new Vector containing elements from start (inclusive) to end (exclusive)
- Example: `["a", "b", "c", "d", "e"]`, start=1, end=4 → `["b", "c", "d"]`

**Test your methods in main()** with various test cases.

---

## Exercise 4: Thread Safety Demonstration (Conceptual)

Create a file named `ThreadSafetyDemo.java`:

### Task

**Important**: This exercise demonstrates the concept. You don't need to fully understand threading yet, but you should see the difference.

1. Create a `Vector<Integer>` called `sharedVector`
2. Create an `ArrayList<Integer>` called `sharedArrayList`
3. Create two threads that both try to add 1000 elements to each collection
4. Run the threads and observe:
   - Vector should successfully have 2000 elements (or close, depending on timing)
   - ArrayList may have unpredictable results, exceptions, or incorrect counts

**Code Template:**
```java
Vector<Integer> sharedVector = new Vector<>();
ArrayList<Integer> sharedArrayList = new ArrayList<>();

// Thread 1
Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        sharedVector.add(i);
        sharedArrayList.add(i);
    }
});

// Thread 2
Thread t2 = new Thread(() -> {
    for (int i = 1000; i < 2000; i++) {
        sharedVector.add(i);
        sharedArrayList.add(i);
    }
});

t1.start();
t2.start();
t1.join();
t2.join();

System.out.println("Vector size: " + sharedVector.size());      // Should be 2000
System.out.println("ArrayList size: " + sharedArrayList.size()); // May be unpredictable
```

**Write comments explaining**:
- Why Vector is thread-safe
- Why ArrayList is not thread-safe
- What "synchronized" means
- Why this matters (even if you don't fully understand threading yet)

---

## Exercise 5: Vector of Custom Objects

Create a file named `ProductInventory.java`:

### Part A: Product Class

Create a `Product` class with:
- `name` (String)
- `price` (double)
- `quantity` (int)
- Constructor, getters, setters, and `toString()` method

### Part B: Inventory Management with Vector

Create an `Inventory` class that uses a `Vector<Product>`:

1. **Add Product**: Method to add a product to inventory
2. **Remove Product**: Method to remove a product by name
3. **Update Quantity**: Method to update quantity of a product by name
4. **Find Product**: Method to find a product by name
5. **Total Value**: Method to calculate total inventory value (price × quantity for all products)
6. **List All**: Method to print all products
7. **Sort by Price**: Method to sort products by price (use `Collections.sort()` with a Comparator)

**Test in main():**
```java
public static void main(String[] args) {
    Inventory inventory = new Inventory();
    
    inventory.addProduct(new Product("Laptop", 999.99, 5));
    inventory.addProduct(new Product("Mouse", 29.99, 20));
    inventory.addProduct(new Product("Keyboard", 79.99, 15));
    
    inventory.listAll();
    System.out.println("Total inventory value: $" + inventory.getTotalValue());
    
    inventory.updateQuantity("Mouse", 25);
    inventory.sortByPrice();
    inventory.listAll();
}
```

---

## Exercise 6: Vector Enumeration vs Iterator

Create a file named `VectorIteration.java`:

### Task

Create a `Vector<String>` with values: "Apple", "Banana", "Cherry", "Date", "Elderberry"

Demonstrate **three different ways** to iterate:

1. **Enumeration** (Vector's legacy method)
2. **Iterator** (modern Collections Framework method)
3. **Enhanced for loop** (most modern)

For each method:
- Print each element
- Try to remove "Cherry" during iteration (observe what happens)
- Explain which methods allow safe removal during iteration

**Code Example:**
```java
Vector<String> fruits = new Vector<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");
fruits.add("Date");

// Method 1: Enumeration (legacy)
Enumeration<String> e = fruits.elements();
while (e.hasMoreElements()) {
    String fruit = e.nextElement();
    System.out.println(fruit);
    // Can't remove with Enumeration!
}

// Method 2: Iterator (modern)
Iterator<String> it = fruits.iterator();
while (it.hasNext()) {
    String fruit = it.next();
    if (fruit.equals("Cherry")) {
        it.remove();  // Safe removal!
    }
}

// Method 3: Enhanced for loop
for (String fruit : fruits) {
    System.out.println(fruit);
    // Can't remove during enhanced for loop!
}
```

**Write comments explaining** the differences and when to use each.

---

## Challenge Exercise (Optional)

Create a file named `VectorChallenge.java`:

### Task: Vector Performance Comparison

Compare performance of Vector vs ArrayList:

1. Create both a `Vector<Integer>` and `ArrayList<Integer>`
2. Time how long it takes to add 1,000,000 elements to each
3. Time how long it takes to access (get) 100,000 random elements from each
4. Print the results

**Discussion**: Why is Vector slower? Is the difference significant for your use case?

**Code Template:**
```java
long start = System.currentTimeMillis();
// ... operations ...
long end = System.currentTimeMillis();
System.out.println("Time: " + (end - start) + " ms");
```

---

## Submission Checklist

- [ ] `VectorBasics.java` - Exercise 1 complete
- [ ] `VectorVsArrayList.java` - Exercise 2 complete with comparisons
- [ ] `VectorOperations.java` - Exercise 3 complete with all methods
- [ ] `ThreadSafetyDemo.java` - Exercise 4 complete with explanations
- [ ] `ProductInventory.java` - Exercise 5 complete with Product class
- [ ] `VectorIteration.java` - Exercise 6 complete with 3 iteration methods
- [ ] All code compiles without errors
- [ ] All code runs and produces expected output
- [ ] Code is properly commented

---

## Notes

- Remember to import `java.util.Vector`, `java.util.Enumeration`, `java.util.Iterator`, `java.util.Collections`
- Vector API is nearly identical to ArrayList - most methods work the same way
- Vector has `capacity()` method that ArrayList doesn't expose
- Vector has legacy `elements()` method returning `Enumeration`
- Vector is synchronized (thread-safe) but slower than ArrayList
- Use `trimToSize()` to reduce Vector capacity to match size
- Use `ensureCapacity(int)` to pre-allocate space if you know how many elements you'll add
- **General rule**: Use ArrayList unless you specifically need thread-safety, then consider Vector or `Collections.synchronizedList()`

## Key Takeaways

1. **Vector and ArrayList are nearly identical** in their APIs
2. **Vector is thread-safe** but slower due to synchronization
3. **ArrayList is preferred** for single-threaded applications (95% of cases)
4. **Vector has legacy features** (Enumeration, capacity management) that ArrayList doesn't expose
5. **Thread-safety comes at a cost** - only use Vector when you actually need it

