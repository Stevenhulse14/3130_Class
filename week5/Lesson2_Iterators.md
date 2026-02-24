# Lesson 2: Iterators in Java - Basic and In-Depth

## Learning Objectives

By the end of this lesson, you will:
- Understand what iterators are and why they're useful
- Master the Iterator interface and its methods
- Use iterators with ArrayList, LinkedList, and other collections
- Implement custom iterators for your own classes
- Understand the enhanced for-loop (for-each) and how it works
- Compare different iteration methods
- Learn about ListIterator for bidirectional traversal

---

## Part 1: Introduction to Iterators

### What is an Iterator?

An **Iterator** is an object that allows you to traverse through a collection of elements, one at a time, without exposing the underlying structure.

### Why Use Iterators?

1. **Abstraction**: Hide implementation details
2. **Consistency**: Same interface for all collections
3. **Safety**: Can prevent concurrent modification errors
4. **Flexibility**: Easy to switch between collection types

### The Iterator Interface

```java
public interface Iterator<E> {
    boolean hasNext();    // Returns true if more elements exist
    E next();             // Returns the next element
    void remove();        // Removes the last element returned (optional)
}
```

---

## Part 2: Basic Iterator Usage

### Example 1: Iterating Over an ArrayList

```java
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorBasics {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        
        // Get an iterator
        Iterator<String> iterator = fruits.iterator();
        
        // Traverse using iterator
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println(fruit);
        }
    }
}
```

**Output:**
```
Apple
Banana
Cherry
Date
```

### Example 2: Removing Elements While Iterating

**❌ WRONG WAY** (ConcurrentModificationException):
```java
ArrayList<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");

// This will throw ConcurrentModificationException!
for (String fruit : fruits) {
    if (fruit.equals("Banana")) {
        fruits.remove(fruit);  // ERROR!
    }
}
```

**✅ CORRECT WAY** (Using Iterator):
```java
ArrayList<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");

Iterator<String> iterator = fruits.iterator();
while (iterator.hasNext()) {
    String fruit = iterator.next();
    if (fruit.equals("Banana")) {
        iterator.remove();  // Safe removal!
    }
}

System.out.println(fruits);  // [Apple, Cherry]
```

### Example 3: Iterating Over a LinkedList

```java
import java.util.LinkedList;
import java.util.Iterator;

LinkedList<Integer> numbers = new LinkedList<>();
numbers.add(10);
numbers.add(20);
numbers.add(30);

Iterator<Integer> iterator = numbers.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

## Part 3: Enhanced For-Loop (For-Each)

### What is the Enhanced For-Loop?

The enhanced for-loop is syntactic sugar that uses iterators behind the scenes.

```java
// Traditional for-loop
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// Enhanced for-loop (for-each)
for (String item : list) {
    System.out.println(item);
}
```

### How It Works Internally

```java
// This code:
for (String fruit : fruits) {
    System.out.println(fruit);
}

// Is equivalent to:
Iterator<String> iterator = fruits.iterator();
while (iterator.hasNext()) {
    String fruit = iterator.next();
    System.out.println(fruit);
}
```

### Example: Using Enhanced For-Loop

```java
ArrayList<Student> students = new ArrayList<>();
students.add(new Student("Alice", 3.8));
students.add(new Student("Bob", 3.5));
students.add(new Student("Charlie", 3.9));

// Enhanced for-loop
for (Student student : students) {
    System.out.println(student.getName() + ": " + student.getGPA());
}
```

**Note**: You cannot remove elements using enhanced for-loop! Use Iterator for that.

---

## Part 4: ListIterator (Bidirectional Traversal)

### What is ListIterator?

`ListIterator` extends `Iterator` and allows:
- Forward traversal (like Iterator)
- Backward traversal
- Modification at current position
- Getting index positions

### ListIterator Methods

```java
public interface ListIterator<E> extends Iterator<E> {
    // Forward operations
    boolean hasNext();
    E next();
    int nextIndex();
    
    // Backward operations
    boolean hasPrevious();
    E previous();
    int previousIndex();
    
    // Modification
    void set(E e);        // Replace last returned element
    void add(E e);        // Insert before next element
    void remove();        // Remove last returned element
}
```

### Example: Using ListIterator

```java
import java.util.ArrayList;
import java.util.ListIterator;

ArrayList<String> list = new ArrayList<>();
list.add("A");
list.add("B");
list.add("C");
list.add("D");

// Forward traversal
ListIterator<String> iterator = list.listIterator();
while (iterator.hasNext()) {
    System.out.print(iterator.next() + " ");  // A B C D
}

System.out.println();

// Backward traversal
while (iterator.hasPrevious()) {
    System.out.print(iterator.previous() + " ");  // D C B A
}
```

### Example: Modifying with ListIterator

```java
ListIterator<String> iterator = list.listIterator();

while (iterator.hasNext()) {
    String item = iterator.next();
    if (item.equals("B")) {
        iterator.set("B-Updated");  // Replace current element
    }
}

// Insert at current position
iterator = list.listIterator(2);  // Start at index 2
iterator.add("New Item");         // Insert before index 2
```

---

## Part 5: Custom Iterator Implementation

### Creating an Iterator for Your Own Class

Let's create a custom iterator for our LinkedList class from Lesson 1:

```java
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList implements Iterable<Integer> {
    private static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    // Implement Iterable interface
    @Override
    public Iterator<Integer> iterator() {
        return new LinkedListIterator();
    }
    
    // Inner class implementing Iterator
    private class LinkedListIterator implements Iterator<Integer> {
        private Node current = head;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            int data = current.data;
            current = current.next;
            return data;
        }
    }
}
```

### Using the Custom Iterator

```java
public class CustomIteratorDemo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(10);
        list.append(20);
        list.append(30);
        
        // Now we can use enhanced for-loop!
        for (int value : list) {
            System.out.println(value);
        }
        
        // Or explicit iterator
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

---

## Part 6: Comparing Iteration Methods

### Method 1: Traditional For-Loop
```java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```
- ✅ Can access by index
- ✅ Can modify during iteration (carefully)
- ❌ Only works with indexable collections (ArrayList)
- ❌ Slower for LinkedList (O(n²))

### Method 2: Enhanced For-Loop
```java
for (String item : list) {
    System.out.println(item);
}
```
- ✅ Clean syntax
- ✅ Works with any Iterable
- ✅ Fast for all collections
- ❌ Cannot remove elements
- ❌ No index access

### Method 3: Iterator
```java
Iterator<String> iterator = list.iterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```
- ✅ Can remove elements safely
- ✅ Works with any collection
- ✅ Fast for all collections
- ❌ More verbose
- ❌ No index access

### Method 4: ListIterator
```java
ListIterator<String> iterator = list.listIterator();
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```
- ✅ Bidirectional traversal
- ✅ Can modify elements
- ✅ Can insert elements
- ✅ Has index information
- ❌ Only works with Lists
- ❌ More complex

---

## Part 7: Common Iterator Patterns

### Pattern 1: Filtering Elements

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);
numbers.add(4);
numbers.add(5);

// Remove even numbers
Iterator<Integer> iterator = numbers.iterator();
while (iterator.hasNext()) {
    if (iterator.next() % 2 == 0) {
        iterator.remove();
    }
}
// Result: [1, 3, 5]
```

### Pattern 2: Finding Elements

```java
ArrayList<Student> students = new ArrayList<>();
// ... add students ...

Iterator<Student> iterator = students.iterator();
Student found = null;
while (iterator.hasNext()) {
    Student student = iterator.next();
    if (student.getGPA() > 3.8) {
        found = student;
        break;
    }
}
```

### Pattern 3: Transforming Elements

```java
ArrayList<String> names = new ArrayList<>();
names.add("alice");
names.add("bob");
names.add("charlie");

// Convert to uppercase
ListIterator<String> iterator = names.listIterator();
while (iterator.hasNext()) {
    String name = iterator.next();
    iterator.set(name.toUpperCase());
}
// Result: [ALICE, BOB, CHARLIE]
```

---

## Part 8: Iterator Best Practices

### ✅ DO:
- Use enhanced for-loop when you don't need to remove elements
- Use Iterator when you need to remove elements during iteration
- Use ListIterator when you need bidirectional traversal
- Check `hasNext()` before calling `next()`
- Handle `NoSuchElementException` when appropriate

### ❌ DON'T:
- Don't modify collection while iterating (except through iterator)
- Don't call `next()` without checking `hasNext()` first
- Don't call `remove()` without calling `next()` first
- Don't use multiple iterators on the same collection simultaneously (unless thread-safe)

---

## Lab Exercise: 10-15 minutes

### Basic Lab: Iterator Fundamentals

**Task 1**: Create a program that:
1. Creates an ArrayList of 10 random integers (1-100)
2. Uses an Iterator to print all elements
3. Uses an Iterator to remove all even numbers
4. Print the remaining list

**Task 2**: Create a program that:
1. Creates a LinkedList of student names
2. Uses ListIterator to:
   - Print all names forward
   - Print all names backward
   - Replace names starting with "A" with "A-Student"
3. Print the final list

### In-Depth Lab: Custom Iterator

**Task**: Implement a custom iterator for a `BookShelf` class.

**Requirements:**

1. Create a `Book` class:
   ```java
   class Book {
       private String title;
       private String author;
       private int pages;
       // Constructor, getters, toString
   }
   ```

2. Create a `BookShelf` class that:
   - Stores books in an ArrayList
   - Implements `Iterable<Book>`
   - Has a custom iterator that:
     - Can iterate forward
     - Can filter books by author
     - Can skip books with less than 200 pages

3. Test your implementation:
   ```java
   BookShelf shelf = new BookShelf();
   shelf.addBook(new Book("Java Basics", "John Doe", 300));
   shelf.addBook(new Book("Python 101", "Jane Smith", 150));
   shelf.addBook(new Book("Advanced Java", "John Doe", 400));
   
   // Iterate all books
   for (Book book : shelf) {
       System.out.println(book);
   }
   
   // Iterate books by specific author
   Iterator<Book> authorIterator = shelf.iterator("John Doe");
   while (authorIterator.hasNext()) {
       System.out.println(authorIterator.next());
   }
   ```

**Challenge (Optional):**
- Implement a reverse iterator
- Add a method to iterate only books with pages > threshold
- Implement ListIterator for bidirectional traversal

---

## Summary

- **Iterators** provide a consistent way to traverse collections
- **Enhanced for-loop** is syntactic sugar for iterators
- **Iterator.remove()** is the safe way to remove during iteration
- **ListIterator** enables bidirectional traversal and modification
- **Custom iterators** allow your classes to work with enhanced for-loops
- **Choose the right method**: enhanced for-loop for reading, Iterator for removing, ListIterator for complex operations

---

## Key Takeaways

1. **Iterator Interface**: `hasNext()`, `next()`, `remove()`
2. **Enhanced For-Loop**: `for (Type item : collection)`
3. **ListIterator**: Bidirectional traversal with `hasPrevious()`, `previous()`
4. **Custom Iterators**: Implement `Iterable<T>` and return `Iterator<T>`
5. **Safe Removal**: Always use `iterator.remove()`, never `collection.remove()` during iteration

---

## Next Steps

- Practice implementing custom iterators
- Experiment with ListIterator for complex operations
- Learn about concurrent collections and their iterators
- Combine iterators with generics for type-safe code
