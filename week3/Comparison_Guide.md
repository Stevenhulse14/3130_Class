# Quick Reference: Arrays vs ArrayLists vs Vectors

## Quick Comparison Table

| Feature | Array | ArrayList | Vector |
|---------|-------|-----------|--------|
| **Declaration** | `int[] arr = new int[5];` | `ArrayList<String> list = new ArrayList<>();` | `Vector<String> vec = new Vector<>();` |
| **Initialization** | `int[] arr = {1, 2, 3};` | `new ArrayList<>(Arrays.asList("a", "b"))` | `new Vector<>(Arrays.asList("a", "b"))` |
| **Size** | Fixed | Dynamic | Dynamic |
| **Access** | `arr[0]` | `list.get(0)` | `vec.get(0)` |
| **Set Value** | `arr[0] = 5;` | `list.set(0, "hello");` | `vec.set(0, "hello");` |
| **Add Element** | ❌ Cannot | `list.add("item");` | `vec.add("item");` |
| **Remove Element** | ❌ Cannot | `list.remove(0);` | `vec.remove(0);` |
| **Length/Size** | `arr.length` (field) | `list.size()` (method) | `vec.size()` (method) |
| **Thread-Safe** | N/A | ❌ No | ✅ Yes |
| **Performance** | ⚡ Fastest | ⚡ Fast | 🐌 Slower |
| **Primitives** | ✅ Yes | ❌ No (use wrappers) | ❌ No (use wrappers) |
| **Printing** | Shows address | Shows contents | Shows contents |

## Code Examples Side-by-Side

### Creating and Adding Elements

```java
// ARRAY
String[] friends = new String[4];
friends[0] = "John";
friends[1] = "Chris";
// Cannot add more - fixed size!

// ARRAYLIST
ArrayList<String> friends = new ArrayList<>();
friends.add("John");
friends.add("Chris");
friends.add("Eric");  // Can keep adding!

// VECTOR
Vector<String> friends = new Vector<>();
friends.add("John");
friends.add("Chris");
friends.add("Eric");  // Can keep adding!
```

### Accessing Elements

```java
// ARRAY
String first = friends[0];
friends[0] = "Carl";

// ARRAYLIST
String first = friends.get(0);
friends.set(0, "Carl");

// VECTOR
String first = friends.get(0);
friends.set(0, "Carl");
```

### Getting Size

```java
// ARRAY
int size = friends.length;  // Field, no parentheses

// ARRAYLIST
int size = friends.size();  // Method, needs parentheses

// VECTOR
int size = friends.size();  // Method, needs parentheses
int capacity = friends.capacity();  // Unique to Vector
```

### Printing

```java
// ARRAY
System.out.println(friends);  
// Output: [Ljava.lang.String;@2a139a55 (memory address)
// Use Arrays.toString(friends) for readable output

// ARRAYLIST
System.out.println(friends);
// Output: [John, Chris, Eric] (nice!)

// VECTOR
System.out.println(friends);
// Output: [John, Chris, Eric] (nice!)
```

## Decision Tree

```
Do you need a collection?
│
├─ Yes, size is FIXED and known?
│  ├─ Yes → Use ARRAY
│  └─ No → Continue...
│
├─ Multiple threads accessing simultaneously?
│  ├─ Yes → Use VECTOR (or Collections.synchronizedList)
│  └─ No → Continue...
│
└─ Use ARRAYLIST (recommended 95% of the time)
```

## Common Mistakes

### ❌ Wrong: Using array syntax with ArrayList
```java
ArrayList<String> list = new ArrayList<>();
String item = list[0];  // ERROR! Arrays use [], ArrayList uses get()
```

### ✅ Correct: Using ArrayList methods
```java
ArrayList<String> list = new ArrayList<>();
String item = list.get(0);  // Correct!
```

### ❌ Wrong: Using .length() on ArrayList
```java
ArrayList<String> list = new ArrayList<>();
int size = list.length();  // ERROR! ArrayList uses size(), not length()
```

### ✅ Correct: Using .size()
```java
ArrayList<String> list = new ArrayList<>();
int size = list.size();  // Correct!
```

### ❌ Wrong: Trying to add to array
```java
String[] arr = new String[5];
arr.add("item");  // ERROR! Arrays don't have add() method
```

### ✅ Correct: Use ArrayList for dynamic sizing
```java
ArrayList<String> list = new ArrayList<>();
list.add("item");  // Correct!
```

## Performance Notes

- **Array**: Fastest - direct memory access, no overhead
- **ArrayList**: Fast - slight overhead for dynamic resizing
- **Vector**: Slower - synchronization overhead (even in single-threaded code)

**Rule of Thumb**: Use ArrayList unless you have a specific reason not to.

## Memory Considerations

- **Array**: Most memory-efficient
- **ArrayList**: Slightly more overhead (stores size, modCount, etc.)
- **Vector**: Similar to ArrayList, but with synchronization overhead

## Thread Safety

- **Array**: Not applicable (fixed size)
- **ArrayList**: **NOT thread-safe** - multiple threads can cause data corruption
- **Vector**: **Thread-safe** - synchronized methods prevent race conditions

**Note**: Even Vector's thread-safety doesn't protect compound operations (check-then-act). You may still need external synchronization for complex operations.

