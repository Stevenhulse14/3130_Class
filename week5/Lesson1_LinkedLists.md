# Lesson 1: Linked Lists - From JavaScript to Java

## Learning Objectives

By the end of this lesson, you will:
- Understand the fundamental concept of linked lists
- Build a linked list from scratch in JavaScript
- Implement a linked list in Java
- Compare linked lists with arrays/ArrayLists
- Understand when to use linked lists vs arrays
- Master basic linked list operations (insert, delete, traverse)

---

## Part 1: Understanding Linked Lists (Conceptual)

### What is a Linked List?

A **linked list** is a linear data structure where elements are stored in nodes, and each node contains:
- **Data**: The actual value
- **Reference/Pointer**: A link to the next node

Unlike arrays, linked lists don't store elements in contiguous memory locations.

### Visual Representation

```
Array:     [A][B][C][D]
           └─┘└─┘└─┘└─┘
           Contiguous memory

Linked List:  A → B → C → D → null
              │    │    │    │
            Node Node Node Node
```

### Key Differences: Arrays vs Linked Lists

| Feature | Array/ArrayList | Linked List |
|---------|----------------|-------------|
| Memory | Contiguous | Non-contiguous |
| Access | O(1) - direct index | O(n) - must traverse |
| Insert at beginning | O(n) - shift elements | O(1) - change pointer |
| Insert at end | O(1) amortized | O(n) - traverse to end |
| Delete at beginning | O(n) - shift elements | O(1) - change pointer |
| Memory overhead | Low | Higher (pointers) |

---

## Part 2: Building a Linked List in JavaScript

We'll start with JavaScript because it's simpler and helps us understand the concept before diving into Java's type system.

### Step 1: Create a Node Class

```javascript
class Node {
    constructor(data) {
        this.data = data;      // The value stored in the node
        this.next = null;      // Reference to the next node
    }
}
```

### Step 2: Create a LinkedList Class

```javascript
class LinkedList {
    constructor() {
        this.head = null;      // First node in the list
        this.size = 0;         // Number of nodes
    }
    
    // Add a node at the end
    append(data) {
        const newNode = new Node(data);
        
        // If list is empty, new node becomes head
        if (this.head === null) {
            this.head = newNode;
        } else {
            // Traverse to the end
            let current = this.head;
            while (current.next !== null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }
    
    // Add a node at the beginning
    prepend(data) {
        const newNode = new Node(data);
        newNode.next = this.head;  // New node points to old head
        this.head = newNode;       // New node becomes head
        this.size++;
    }
    
    // Print all elements
    print() {
        let current = this.head;
        let result = [];
        while (current !== null) {
            result.push(current.data);
            current = current.next;
        }
        console.log(result.join(' → ') + ' → null');
    }
    
    // Get size
    getSize() {
        return this.size;
    }
    
    // Check if empty
    isEmpty() {
        return this.head === null;
    }
}
```

### Step 3: Test the JavaScript Linked List

```javascript
// Create a new linked list
const list = new LinkedList();

// Add elements
list.append(10);
list.append(20);
list.append(30);
list.prepend(5);

// Print: 5 → 10 → 20 → 30 → null
list.print();

console.log("Size:", list.getSize());  // Output: 4
console.log("Empty?", list.isEmpty()); // Output: false
```

### Key Observations from JavaScript:

1. **No fixed size** - Unlike arrays, we can grow dynamically
2. **No direct access** - We must traverse from head to find elements
3. **Easy insertion at beginning** - Just change the head pointer
4. **Memory efficient** - Only allocate what we need

---

## Part 3: Implementing Linked Lists in Java

Now let's translate this to Java with proper types and encapsulation.

### Step 1: Create a Node Class (Inner Class)

```java
public class LinkedList {
    // Inner class for Node
    private static class Node {
        int data;        // For simplicity, using int. Can be generic!
        Node next;       // Reference to next node
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;   // First node
    private int size;    // Number of nodes
    
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
}
```

### Step 2: Basic Operations

```java
public class LinkedList {
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
    
    // Add at the end
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
    
    // Add at the beginning
    public void prepend(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    // Print all elements
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println(" → null");
    }
    
    // Get size
    public int getSize() {
        return size;
    }
    
    // Check if empty
    public boolean isEmpty() {
        return head == null;
    }
}
```

### Step 3: More Advanced Operations

```java
// Delete a node by value
public boolean delete(int data) {
    if (head == null) {
        return false;
    }
    
    // If deleting head
    if (head.data == data) {
        head = head.next;
        size--;
        return true;
    }
    
    // Find and delete
    Node current = head;
    while (current.next != null) {
        if (current.next.data == data) {
            current.next = current.next.next;
            size--;
            return true;
        }
        current = current.next;
    }
    return false;
}

// Find a value
public boolean contains(int data) {
    Node current = head;
    while (current != null) {
        if (current.data == data) {
            return true;
        }
        current = current.next;
    }
    return false;
}

// Get element at index
public int get(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index);
    }
    
    Node current = head;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    return current.data;
}
```

### Step 4: Testing the Java Linked List

```java
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        
        list.append(10);
        list.append(20);
        list.append(30);
        list.prepend(5);
        
        list.print();  // 5 → 10 → 20 → 30 → null
        
        System.out.println("Size: " + list.getSize());
        System.out.println("Contains 20? " + list.contains(20));
        
        list.delete(20);
        list.print();  // 5 → 10 → 30 → null
    }
}
```

---

## Part 4: Types of Linked Lists

### 1. Singly Linked List (What we built)
- Each node points to the next node only
- Can traverse in one direction (forward)

### 2. Doubly Linked List
- Each node has references to both next and previous
- Can traverse in both directions

```java
class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode prev;
}
```

### 3. Circular Linked List
- Last node points back to the first node
- Forms a circle

---

## Part 5: When to Use Linked Lists

### Use Linked Lists When:
- ✅ You frequently insert/delete at the beginning
- ✅ Size is unknown and changes frequently
- ✅ Memory is fragmented (can't allocate large contiguous block)
- ✅ You don't need random access

### Use Arrays/ArrayLists When:
- ✅ You need fast random access (by index)
- ✅ Size is relatively fixed
- ✅ You mostly append to the end
- ✅ Memory efficiency is critical

---

## Lab Exercise: 10-15 minutes

### Task: Build a Student Roster Using Linked Lists

Create a `StudentLinkedList` class that manages a roster of students.

**Requirements:**

1. Create a `Student` class with:
   - `name` (String)
   - `studentId` (String)
   - `gpa` (double)

2. Create a `StudentLinkedList` class with:
   - `append(Student student)` - Add student to end
   - `prepend(Student student)` - Add student to beginning
   - `delete(String studentId)` - Remove student by ID
   - `find(String studentId)` - Find and return student
   - `printAll()` - Print all students
   - `getAverageGPA()` - Calculate average GPA
   - `getSize()` - Return number of students

3. Test your implementation:
   ```java
   StudentLinkedList roster = new StudentLinkedList();
   
   roster.append(new Student("Alice", "S001", 3.8));
   roster.append(new Student("Bob", "S002", 3.5));
   roster.prepend(new Student("Charlie", "S003", 3.9));
   
   roster.printAll();
   System.out.println("Average GPA: " + roster.getAverageGPA());
   
   roster.delete("S002");
   roster.printAll();
   ```

**Challenge (Optional):**
- Add a method to sort students by GPA
- Add a method to reverse the linked list
- Implement a doubly linked list version

---

## Summary

- **Linked lists** store data in nodes with pointers to the next node
- **Advantages**: Dynamic size, efficient insertion/deletion at beginning
- **Disadvantages**: No random access, extra memory for pointers
- **Java implementation** uses inner classes and proper encapsulation
- **Choose linked lists** when you need frequent insertions/deletions at the beginning

---

## Next Steps

- Practice implementing more operations (insert at position, reverse, etc.)
- Learn about doubly linked lists
- Compare performance with ArrayList
- Prepare for Lesson 2: Iterators!
