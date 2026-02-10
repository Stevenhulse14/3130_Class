# Arrays, ArrayLists, and Vectors in Java

## Table of Contents
1. [Arrays](#arrays)
2. [ArrayLists](#arraylists)
3. [Vectors](#vectors)
4. [Comparison Summary](#comparison-summary)
5. [When to Use Each](#when-to-use-each)
6. [Common Operations](#common-operations)
7. [Generics Integration](#generics-integration)

---

## Arrays

### What is an Array?

An **array** is a fixed-size collection of elements of the same type. Once created, an array cannot grow or shrink in size.

### Key Characteristics

- **Fixed Size**: Size must be specified at creation and cannot change
- **Primitive Support**: Can hold primitive types (int, double, boolean, etc.) or objects
- **Direct Access**: Elements accessed via index using square brackets `[]`
- **Memory Efficient**: Lower overhead than ArrayLists/Vectors
- **Zero-Based Indexing**: First element is at index 0

### Declaration and Initialization

#### Method 1: Declare size, then assign values
```java
// Declare an array of integers with size 5
int[] numbers = new int[5];

// Assign values
numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
numbers[3] = 40;
numbers[4] = 50;
```

#### Method 2: Initialize with values directly
```java
// Array of integers initialized with values
int[] numbers = {10, 20, 30, 40, 50};

// Array of Strings
String[] friends = {"John", "Chris", "Eric", "Luke"};
```

#### Method 3: Using new with initializer
```java
String[] friends = new String[]{"John", "Chris", "Eric", "Luke"};
```

### Accessing Elements

```java
String[] friends = {"John", "Chris", "Eric", "Luke"};

// Get element at index 1 (second element)
String secondFriend = friends[1];  // "Chris"

// Set element at index 0
friends[0] = "Carl";

// Print element
System.out.println(friends[0]);  // "Carl"
```

### Getting Length

```java
int[] numbers = {10, 20, 30, 40, 50};
int length = numbers.length;  // Note: length is a FIELD, not a method
System.out.println("Array length: " + length);  // Prints: Array length: 5
```

### Important Limitations

1. **Cannot add elements**: Arrays have fixed size
2. **Cannot remove elements**: You can set elements to null, but size remains the same
3. **No built-in methods**: Arrays don't have methods like `add()`, `remove()`, `contains()`
4. **Printing**: Printing an array directly shows memory address, not contents

```java
int[] numbers = {10, 20, 30};
System.out.println(numbers);  // Prints something like: [I@2a139a55
```

To print array contents, use `Arrays.toString()`:
```java
import java.util.Arrays;
System.out.println(Arrays.toString(numbers));  // Prints: [10, 20, 30]
```

### Iterating Through Arrays

```java
String[] friends = {"John", "Chris", "Eric", "Luke"};

// Method 1: Traditional for loop
for (int i = 0; i < friends.length; i++) {
    System.out.println(friends[i]);
}

// Method 2: Enhanced for loop (for-each)
for (String friend : friends) {
    System.out.println(friend);
}
```

---

## ArrayLists

### What is an ArrayList?

An **ArrayList** is a resizable array implementation of the List interface. It automatically grows and shrinks as elements are added or removed.

### Key Characteristics

- **Dynamic Size**: Automatically resizes as needed
- **Object Only**: Can only hold objects (not primitives), but wrapper classes work fine
- **Method-Based Access**: Uses methods like `get()`, `set()`, `add()`, `remove()`
- **Part of Collections Framework**: Implements List interface
- **Not Thread-Safe**: Operations are not synchronized

### Declaration and Initialization

```java
import java.util.ArrayList;

// Create an empty ArrayList
ArrayList<String> friends = new ArrayList<>();

// Create ArrayList with initial capacity (optional optimization)
ArrayList<String> friends = new ArrayList<>(10);

// Initialize with values using Arrays.asList()
ArrayList<String> friends = new ArrayList<>(Arrays.asList("John", "Chris", "Eric", "Luke"));
```

### Common Operations

#### Adding Elements

```java
ArrayList<String> friends = new ArrayList<>();

// Add to end
friends.add("John");
friends.add("Chris");
friends.add("Eric");

// Add at specific index
friends.add(1, "Mitch");  // Inserts "Mitch" at index 1, shifts others right
```

#### Accessing Elements

```java
// Get element at index
String firstFriend = friends.get(0);  // "John"

// Set element at index
friends.set(0, "Carl");  // Replaces "John" with "Carl"
```

#### Removing Elements

```java
// Remove by index
String removed = friends.remove(1);  // Removes and returns element at index 1

// Remove by value (removes first occurrence)
boolean removed = friends.remove("Chris");  // Returns true if found and removed
```

#### Getting Size

```java
int size = friends.size();  // Note: size() is a METHOD, not a field
System.out.println("Size: " + size);
```

#### Checking Contents

```java
// Check if empty
boolean isEmpty = friends.isEmpty();

// Check if contains element
boolean hasJohn = friends.contains("John");

// Get index of element
int index = friends.indexOf("Chris");  // Returns -1 if not found
```

### Printing ArrayLists

```java
ArrayList<String> friends = new ArrayList<>();
friends.add("John");
friends.add("Chris");

System.out.println(friends);  // Prints: [John, Chris]
// ArrayList implements toString() nicely!
```

### Iterating Through ArrayLists

```java
ArrayList<String> friends = new ArrayList<>();
friends.add("John");
friends.add("Chris");
friends.add("Eric");

// Method 1: Traditional for loop
for (int i = 0; i < friends.size(); i++) {
    System.out.println(friends.get(i));
}

// Method 2: Enhanced for loop
for (String friend : friends) {
    System.out.println(friend);
}

// Method 3: Using Iterator
Iterator<String> it = friends.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

### Working with Primitives

Since ArrayLists can only hold objects, use wrapper classes:

```java
// Instead of int, use Integer
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);

// Instead of double, use Double
ArrayList<Double> prices = new ArrayList<>();
prices.add(19.99);
prices.add(29.99);

// Instead of boolean, use Boolean
ArrayList<Boolean> flags = new ArrayList<>();
flags.add(true);
flags.add(false);
```

---

## Vectors

### What is a Vector?

A **Vector** is similar to an ArrayList but with one key difference: **all operations are synchronized**, making it thread-safe.

### Key Characteristics

- **Thread-Safe**: All methods are synchronized
- **Dynamic Size**: Automatically resizes like ArrayList
- **Legacy Class**: Existed before Collections Framework (since Java 1.0)
- **Slower Performance**: Synchronization overhead makes it slower than ArrayList
- **Part of Collections Framework**: Now implements List interface (retrofitted in Java 1.2)

### Declaration and Initialization

```java
import java.util.Vector;

// Create an empty Vector
Vector<String> friends = new Vector<>();

// Create Vector with initial capacity
Vector<String> friends = new Vector<>(10);

// Create Vector with initial capacity and capacity increment
Vector<String> friends = new Vector<>(10, 5);  // Starts at 10, grows by 5

// Initialize with values
Vector<String> friends = new Vector<>(Arrays.asList("John", "Chris", "Eric", "Luke"));
```

### Common Operations

Vector operations are nearly identical to ArrayList:

```java
Vector<String> friends = new Vector<>();

// Add elements
friends.add("John");
friends.add("Chris");
friends.add(1, "Mitch");  // Insert at index

// Access elements
String first = friends.get(0);
friends.set(0, "Carl");

// Remove elements
friends.remove(1);  // Remove by index
friends.remove("Chris");  // Remove by value

// Size
int size = friends.size();
int capacity = friends.capacity();  // Unique to Vector - shows current capacity
```

### Vector-Specific Features

#### Capacity Management

```java
Vector<String> friends = new Vector<>(10);

// Ensure minimum capacity
friends.ensureCapacity(20);  // Pre-allocate space

// Trim to current size
friends.trimToSize();  // Reduce capacity to match size

// Get capacity (not available in ArrayList)
int cap = friends.capacity();
```

#### Legacy Enumeration

```java
Vector<String> friends = new Vector<>();
friends.add("John");
friends.add("Chris");

// Old-style iteration (legacy)
Enumeration<String> e = friends.elements();
while (e.hasMoreElements()) {
    System.out.println(e.nextElement());
}

// Modern iteration (preferred)
for (String friend : friends) {
    System.out.println(friend);
}
```

### Thread Safety Example

```java
// Vector is thread-safe
Vector<String> shared = new Vector<>();

// Multiple threads can safely add/remove
Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        shared.add("Item-" + i);
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        shared.add("Item-" + i);
    }
});

t1.start();
t2.start();
t1.join();
t2.join();

// Vector will have 2000 elements safely
System.out.println(shared.size());  // 2000
```

**Note**: Even though Vector is thread-safe, compound operations (check-then-act) may still need external synchronization.

---

## Comparison Summary

| Feature | Array | ArrayList | Vector |
|---------|-------|-----------|--------|
| **Size** | Fixed | Dynamic | Dynamic |
| **Thread-Safe** | N/A | No | Yes |
| **Performance** | Fastest | Fast | Slower (due to sync) |
| **Primitive Support** | Yes | No (use wrappers) | No (use wrappers) |
| **Access Method** | `arr[index]` | `list.get(index)` | `vec.get(index)` |
| **Length/Size** | `arr.length` (field) | `list.size()` (method) | `vec.size()` (method) |
| **Add Element** | Cannot | `add()` | `add()` |
| **Remove Element** | Cannot | `remove()` | `remove()` |
| **Printing** | Shows address | Shows contents | Shows contents |
| **Memory Overhead** | Low | Medium | Medium |
| **When Introduced** | Java 1.0 | Java 1.2 | Java 1.0 |
| **Part of Collections** | No | Yes | Yes (retrofitted) |

---

## When to Use Each

### Use Arrays When:
- You know the exact size and it won't change
- You need maximum performance
- You're working with primitives and want to avoid wrapper class overhead
- You're implementing low-level algorithms
- Memory efficiency is critical

**Example**: Storing RGB values for pixels, fixed-size lookup tables

### Use ArrayList When:
- You need a dynamic-size collection (most common case)
- You're working in a single-threaded environment
- You want modern, clean API
- You need Collections Framework features (sorting, searching, etc.)
- **This is the recommended choice 95% of the time**

**Example**: Storing user input, managing a list of students, collecting search results

### Use Vector When:
- You need thread-safety for individual operations
- Multiple threads will access the collection concurrently
- You're working with legacy code that uses Vector
- **Note**: Even then, consider `Collections.synchronizedList(new ArrayList<>())` first

**Example**: Shared data structure accessed by multiple threads (though modern alternatives like `ConcurrentHashMap` or `CopyOnWriteArrayList` are often better)

---

## Common Operations

### Converting Between Types

```java
// Array to ArrayList
String[] arr = {"John", "Chris", "Eric"};
ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

// ArrayList to Array
ArrayList<String> list = new ArrayList<>();
list.add("John");
list.add("Chris");
String[] arr = list.toArray(new String[0]);

// Vector to ArrayList
Vector<String> vec = new Vector<>();
vec.add("John");
ArrayList<String> list = new ArrayList<>(vec);

// ArrayList to Vector
ArrayList<String> list = new ArrayList<>();
list.add("John");
Vector<String> vec = new Vector<>(list);
```

### Sorting

```java
// Array
int[] numbers = {5, 2, 8, 1, 9};
Arrays.sort(numbers);

// ArrayList
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(5);
numbers.add(2);
numbers.add(8);
Collections.sort(numbers);

// Vector
Vector<Integer> numbers = new Vector<>();
numbers.add(5);
numbers.add(2);
numbers.add(8);
Collections.sort(numbers);
```

### Searching

```java
// Array
int[] numbers = {1, 2, 3, 4, 5};
int index = Arrays.binarySearch(numbers, 3);  // Must be sorted first

// ArrayList
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);
int index = Collections.binarySearch(numbers, 3);  // Must be sorted first
// Or use indexOf()
int index = numbers.indexOf(3);
```

---

## Generics Integration

### Why Generics Matter

Generics provide **type safety** and eliminate the need for casting. They work seamlessly with ArrayLists and Vectors.

### Basic Generic Usage

```java
// Without generics (old way - don't do this!)
ArrayList list = new ArrayList();  // Can hold any Object
list.add("Hello");
list.add(42);  // No type checking!
String str = (String) list.get(0);  // Need casting

// With generics (modern way)
ArrayList<String> list = new ArrayList<>();  // Can only hold Strings
list.add("Hello");
// list.add(42);  // Compile error!
String str = list.get(0);  // No casting needed
```

### Generic Methods with Collections

```java
// Generic method that works with any List
public static <T> void printList(List<T> list) {
    for (T item : list) {
        System.out.println(item);
    }
}

// Usage
ArrayList<String> strings = new ArrayList<>();
strings.add("Hello");
printList(strings);  // Works!

ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(42);
printList(numbers);  // Also works!
```

### Bounded Generics

```java
// Method that only works with Numbers
public static <T extends Number> double sum(List<T> numbers) {
    double total = 0.0;
    for (T num : numbers) {
        total += num.doubleValue();
    }
    return total;
}

// Usage
ArrayList<Integer> ints = new ArrayList<>();
ints.add(10);
ints.add(20);
double result = sum(ints);  // Works!

ArrayList<String> strings = new ArrayList<>();
// double result = sum(strings);  // Compile error!
```

### Generic Classes with Collections

```java
// Generic container class
public class Container<T> {
    private ArrayList<T> items;
    
    public Container() {
        items = new ArrayList<>();
    }
    
    public void add(T item) {
        items.add(item);
    }
    
    public T get(int index) {
        return items.get(index);
    }
    
    public int size() {
        return items.size();
    }
}

// Usage
Container<String> stringContainer = new Container<>();
stringContainer.add("Hello");
stringContainer.add("World");

Container<Integer> intContainer = new Container<>();
intContainer.add(42);
intContainer.add(100);
```

### Wildcards

```java
// Method that accepts any List of Numbers
public static void printNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) {
        System.out.println(num.doubleValue());
    }
}

// Works with ArrayList<Integer>, ArrayList<Double>, etc.
ArrayList<Integer> ints = new ArrayList<>();
ints.add(10);
printNumbers(ints);  // Works!

ArrayList<Double> doubles = new ArrayList<>();
doubles.add(3.14);
printNumbers(doubles);  // Also works!
```

---

## Summary

- **Arrays**: Fixed-size, fastest, supports primitives. Use when size is known and won't change.
- **ArrayList**: Dynamic-size, modern, recommended for most cases. Use 95% of the time.
- **Vector**: Thread-safe ArrayList, slower due to synchronization. Use only when thread-safety is needed for individual operations.

**Best Practice**: Start with ArrayList. Only use Array if you need fixed size and primitives. Only use Vector if you need thread-safety and can't use modern alternatives.

