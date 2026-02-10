# Advanced Generics Assignment

## Learning Objectives

By completing this assignment, you will:
- Deepen understanding of generics with ArrayLists and Vectors
- Create generic methods that work with collections
- Use bounded generics to restrict type parameters
- Understand wildcards (`?`, `? extends`, `? super`)
- Build generic classes that use collections
- Apply generics to solve real-world problems

## Prerequisites

- Completion of Basic ArrayLists and Basic Vectors assignments
- Understanding of generics basics (`ArrayList<String>`, `Vector<Integer>`)
- Knowledge of inheritance and interfaces
- Understanding of method overloading

---

## Exercise 1: Generic Collection Utilities

Create a file named `GenericCollectionUtils.java` and implement the following generic methods:

### Method 1: Generic Swap
```java
public static <T> void swap(List<T> list, int index1, int index2)
```
- Swaps two elements in any List (works with ArrayList, Vector, etc.)
- Generic type `T` represents the element type
- Example: `swap(list, 0, 2)` swaps first and third elements

### Method 2: Generic Find Maximum
```java
public static <T extends Comparable<T>> T findMax(List<T> list)
```
- Finds the maximum element in a list
- Uses bounded generic: `T extends Comparable<T>` means T must be comparable
- Works with `Integer`, `String`, `Double`, etc. (anything that implements `Comparable`)
- Returns `null` if list is empty

### Method 3: Generic Count Matches
```java
public static <T> int countMatches(List<T> list, T target)
```
- Counts how many times `target` appears in `list`
- Uses `equals()` for comparison
- Works with any type `T`

### Method 4: Generic Reverse
```java
public static <T> void reverse(List<T> list)
```
- Reverses any List in-place
- Generic - works with `ArrayList`, `Vector`, or any `List` implementation

**Test your methods:**
```java
public static void main(String[] args) {
    ArrayList<String> words = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
    swap(words, 0, 2);
    System.out.println(words);  // [cherry, banana, apple]
    
    ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
    System.out.println("Max: " + findMax(numbers));  // 9
    
    ArrayList<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "apple", "cherry"));
    System.out.println("Apple count: " + countMatches(fruits, "apple"));  // 2
}
```

---

## Exercise 2: Bounded Generics

Create a file named `BoundedGenerics.java`:

### Part A: Number Operations

Create methods that work only with `Number` types:

#### Method 1: Sum of Numbers
```java
public static <T extends Number> double sum(List<T> numbers)
```
- Takes a list of any Number type (`Integer`, `Double`, `Float`, etc.)
- Returns the sum as a `double`
- Uses `number.doubleValue()` to convert

#### Method 2: Average of Numbers
```java
public static <T extends Number> double average(List<T> numbers)
```
- Calculates average of any Number list
- Returns 0.0 if list is empty

#### Method 3: Filter Numbers Above Threshold
```java
public static <T extends Number & Comparable<T>> List<T> filterAbove(List<T> numbers, T threshold)
```
- Returns a new list containing only numbers greater than threshold
- Uses multiple bounds: `T extends Number & Comparable<T>`
- Works with `Integer`, `Double`, etc.

**Test:**
```java
ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
System.out.println("Sum: " + sum(ints));           // 150.0
System.out.println("Average: " + average(ints));   // 30.0
System.out.println("Above 25: " + filterAbove(ints, 25));  // [30, 40, 50]

ArrayList<Double> doubles = new ArrayList<>(Arrays.asList(1.5, 2.5, 3.5));
System.out.println("Sum: " + sum(doubles));  // 7.5
```

---

## Exercise 3: Generic Container Class

Create a file named `GenericContainer.java`:

### Task

Create a generic `Container<T>` class that wraps an `ArrayList<T>`:

**Requirements:**
- Private field: `ArrayList<T> items`
- Constructor: `Container()` - creates empty container
- Constructor: `Container(Collection<T> collection)` - initializes with collection
- Method: `void add(T item)` - adds item
- Method: `T get(int index)` - gets item at index
- Method: `boolean remove(T item)` - removes item, returns true if found
- Method: `int size()` - returns size
- Method: `boolean isEmpty()` - checks if empty
- Method: `void clear()` - removes all items
- Method: `boolean contains(T item)` - checks if item exists
- Method: `List<T> getAll()` - returns copy of all items
- Method: `void addAll(Collection<T> collection)` - adds all from collection

**Bonus Methods:**
- Method: `<U extends T> void addAllFrom(Container<U> other)` - adds all items from another container (if U is subtype of T)
- Method: `void sort()` - sorts items (requires `T extends Comparable<T>`)

**Test:**
```java
Container<String> container = new Container<>();
container.add("Hello");
container.add("World");
System.out.println(container.size());  // 2
System.out.println(container.get(0));  // Hello
System.out.println(container.contains("World"));  // true

Container<Integer> numbers = new Container<>(Arrays.asList(5, 2, 8));
numbers.sort();
System.out.println(numbers.getAll());  // [2, 5, 8]
```

---

## Exercise 4: Wildcards

Create a file named `WildcardExamples.java`:

### Part A: Upper Bounded Wildcards (`? extends`)

#### Method 1: Print Any List of Numbers
```java
public static void printNumbers(List<? extends Number> numbers)
```
- Accepts `List<Integer>`, `List<Double>`, `List<Float>`, etc.
- Can read from the list, but **cannot add** to it (except null)
- Why? Because you don't know the exact type

#### Method 2: Sum Any List of Numbers
```java
public static double sumNumbers(List<? extends Number> numbers)
```
- Works with any Number subtype list
- Returns sum as double

### Part B: Lower Bounded Wildcards (`? super`)

#### Method 3: Add Numbers to List
```java
public static void addNumbers(List<? super Integer> list)
```
- Accepts `List<Integer>`, `List<Number>`, `List<Object>`
- Can **add** Integer (or subtypes) to the list
- Why? Because any supertype of Integer can hold an Integer

### Part C: Unbounded Wildcards (`?`)

#### Method 4: Print Any List
```java
public static void printList(List<?> list)
```
- Accepts any List regardless of element type
- Can only read as `Object`
- Useful when you don't care about the type

**Test:**
```java
ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3));
ArrayList<Double> doubles = new ArrayList<>(Arrays.asList(1.5, 2.5, 3.5));

printNumbers(ints);      // Works!
printNumbers(doubles);   // Works!
System.out.println("Sum: " + sumNumbers(ints));  // 6.0

List<Number> numbers = new ArrayList<>();
addNumbers(numbers);     // Can add Integers to List<Number>
numbers.add(10);
numbers.add(20.5);       // Also works!
```

---

## Exercise 5: Generic Pair and Map-like Structure

Create a file named `GenericPairMap.java`:

### Part A: Generic Pair Class

Create a `Pair<K, V>` class:
- Two generic type parameters: `K` (key) and `V` (value)
- Fields: `K first`, `V second`
- Constructor: `Pair(K first, V second)`
- Getters: `getFirst()`, `getSecond()`
- Setters: `setFirst(K)`, `setSecond(V)`
- `toString()`: Returns `"(first, second)"`
- `equals()` and `hashCode()`: Compare both first and second

### Part B: PairMap Class

Create a `PairMap<K, V>` class that stores `Pair<K, V>` objects in an `ArrayList`:

**Requirements:**
- Field: `ArrayList<Pair<K, V>> pairs`
- Method: `void put(K key, V value)` - adds or updates pair
- Method: `V get(K key)` - returns value for key, or null if not found
- Method: `boolean containsKey(K key)` - checks if key exists
- Method: `void remove(K key)` - removes pair with key
- Method: `int size()` - returns number of pairs
- Method: `List<K> getAllKeys()` - returns list of all keys
- Method: `List<V> getAllValues()` - returns list of all values
- Method: `void clear()` - removes all pairs

**Test:**
```java
PairMap<String, Integer> map = new PairMap<>();
map.put("Alice", 25);
map.put("Bob", 30);
map.put("Charlie", 28);

System.out.println(map.get("Bob"));  // 30
System.out.println(map.containsKey("Alice"));  // true
map.put("Alice", 26);  // Update
System.out.println(map.get("Alice"));  // 26
map.remove("Charlie");
System.out.println(map.size());  // 2
```

---

## Exercise 6: Generic Stack Implementation

Create a file named `GenericStack.java`:

### Task

Implement a generic `Stack<T>` class using an `ArrayList<T>`:

**Stack Operations:**
- `void push(T item)` - adds item to top of stack
- `T pop()` - removes and returns top item (throws exception if empty)
- `T peek()` - returns top item without removing (throws exception if empty)
- `boolean isEmpty()` - checks if stack is empty
- `int size()` - returns number of items
- `void clear()` - removes all items

**Bonus:**
- `boolean contains(T item)` - checks if item is in stack
- `void pushAll(Collection<? extends T> collection)` - pushes all items from collection

**Test:**
```java
Stack<String> stack = new Stack<>();
stack.push("First");
stack.push("Second");
stack.push("Third");

System.out.println(stack.peek());  // Third
System.out.println(stack.pop());   // Third
System.out.println(stack.pop());   // Second
System.out.println(stack.size());  // 1
```

---

## Challenge Exercise (Optional)

Create a file named `GenericChallenge.java`:

### Task: Generic Binary Search Tree Node

Create a generic `BSTNode<T>` class for a binary search tree:

**Requirements:**
- Field: `T data`
- Field: `BSTNode<T> left`
- Field: `BSTNode<T> right`
- Constructor: `BSTNode(T data)`
- Method: `void insert(T item)` - inserts item (requires `T extends Comparable<T>`)
- Method: `boolean contains(T item)` - searches for item
- Method: `List<T> inOrderTraversal()` - returns list of elements in sorted order

**Test:**
```java
BSTNode<Integer> root = new BSTNode<>(5);
root.insert(3);
root.insert(7);
root.insert(2);
root.insert(4);

System.out.println(root.contains(3));  // true
System.out.println(root.inOrderTraversal());  // [2, 3, 4, 5, 7]
```

---

## Submission Checklist

- [ ] `GenericCollectionUtils.java` - Exercise 1 complete with all generic methods
- [ ] `BoundedGenerics.java` - Exercise 2 complete with Number operations
- [ ] `GenericContainer.java` - Exercise 3 complete with Container class
- [ ] `WildcardExamples.java` - Exercise 4 complete with wildcard examples
- [ ] `GenericPairMap.java` - Exercise 5 complete with Pair and PairMap
- [ ] `GenericStack.java` - Exercise 6 complete with Stack implementation
- [ ] All code compiles without errors
- [ ] All code runs and produces expected output
- [ ] Code is properly commented
- [ ] Generics are used correctly (no raw types, proper bounds)

---

## Notes

- **Always use generics** - avoid raw types (`ArrayList` instead of `ArrayList<String>`)
- **Bounded generics** (`<T extends Comparable<T>>`) restrict what types can be used
- **Wildcards** (`?`, `? extends`, `? super`) provide flexibility when you don't know the exact type
- **Upper bounded** (`? extends`) - can read, cannot write (except null)
- **Lower bounded** (`? super`) - can write, read as Object
- **Unbounded** (`?`) - can only read as Object
- Use `Comparable<T>` interface for types that can be compared
- Use `Number` class as upper bound for numeric types
- Remember: generics provide **compile-time type safety**, not runtime type information

## Key Concepts

1. **Generic Methods**: `<T> returnType methodName(...)` - method-level generics
2. **Generic Classes**: `class Name<T>` - class-level generics
3. **Bounded Types**: `<T extends SomeClass>` - restricts what T can be
4. **Wildcards**: `List<?>` - unknown type, provides flexibility
5. **Type Erasure**: Generics are removed at runtime (compile-time feature)

