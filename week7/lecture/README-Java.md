# Practical Data Structures: Stacks, Queues, and Linked Lists (Java Version)

## 📚 Lesson Series Overview

This lesson series teaches students not just **what** data structures are, but **why** engineers actually use them in real-world systems.

> **Note:** This is the Java version. For the JavaScript version, see [README.md](./README.md).

### The Problem with Traditional Learning

One of the biggest mistakes when learning data structures is that students only see:
- `push()`
- `pop()`
- `enqueue()`
- `dequeue()`

But they **never see why real systems need these structures**.

### Our Approach

Each lesson follows the same proven pattern:

1. **Real world problem** → Understanding the actual need
2. **Why a specific structure solves it** → Connecting theory to practice
3. **How the structure works internally** → Deep understanding
4. **Implementing the structure** → Hands-on coding
5. **Applying it to solve the real problem** → Complete solution

---

## 📋 Table of Contents

| Lesson | Topic | Real-World Application |
|--------|-------|----------------------|
| [Lesson 1](#lesson-1--linked-lists) | Linked Lists | Music Playlists |
| [Lesson 2](#lesson-2--stacks) | Stacks | Undo Feature |
| [Lesson 3](#lesson-3--queues) | Queues | Customer Support Queue |

---

## Lesson 1 — Linked Lists

### Understanding the Foundation

Before learning stacks and queues, students **must** understand linked lists, because stacks and queues are often implemented on top of them.

#### The Array Problem

Most beginner programming classes only teach arrays. Arrays are simple but they hide an important reality:

> **Arrays require contiguous memory.**

That means the computer must store elements in a block of memory like this:

```
[10][20][30][40]
```

#### The Insertion Problem

The problem arises when we want to insert elements in the middle.

If we insert `15`:

```
[10][15][20][30][40]
```

**Everything after the insertion must shift.**

**Time Complexity: O(n)**

This becomes inefficient when dealing with very large datasets.

---

### Linked List Concept

Instead of storing elements together, we store them as **separate objects connected with pointers**.

Each element stores:
- **value** — the actual data
- **pointer to next element** — reference to the next node

#### Visual Representation

```
Head
 ↓
[10 | * ] → [20 | * ] → [30 | * ] → [40 | null]
```

Each box is called a **node**.

#### Key Advantage

This means the elements **do not need to be next to each other in memory**.

This makes inserting elements **extremely efficient**.

**Time Complexity: O(1)** for insertion at the beginning

---

### Real Engineering Use Cases for Linked Lists

Linked lists appear in many real systems:

| System | Use Case |
|--------|----------|
| **Music Playlists** | Sequential song navigation |
| **Browser Navigation** | History management |
| **Memory Allocation** | Dynamic memory management |
| **Graph Traversal** | Adjacency lists |
| **Networking** | Packet routing |

#### Example: Music Playlist

One of the most intuitive examples is a **music playlist**.

Think about **Spotify**.

When you press "next song", the system simply moves to the next node:

```
Song A → Song B → Song C → Song D
```

If you insert a song after Song B:

```
Song A → Song B → Song X → Song C → Song D
```

**Only one pointer changes.**

That is extremely efficient!

---

### Building a Linked List in Java

#### Step 1: The Node Class

Each node stores two pieces of information:
- The data value
- The reference to the next node

```java
class Node {
    int value;
    Node next;
    
    Node(int value) {
        this.value = value;
        this.next = null;
    }
}
```

**When a node is created:**
- `value` = the stored data
- `next` = pointer to the next node (initially `null`)

---

#### Step 2: Creating the Linked List Class

Now we create a class that manages the list.

The linked list must track the **first node**, called the **head**.

```
Head
 ↓
Node → Node → Node
```

```java
class LinkedList {
    Node head;
    
    LinkedList() {
        this.head = null;
    }
}
```

**When the list starts**, there are no nodes, so `head` is `null`.

---

#### Step 3: Adding an Element to the Beginning

The simplest insertion is adding to the beginning.

This is important because it is the operation used by **stacks**.

**Example before insertion:**

```
Head
 ↓
10 → 20 → 30
```

**Insert 5:**

```
Head
 ↓
5 → 10 → 20 → 30
```

**Only two pointer operations happen:**
1. New node points to old head
2. Head moves to new node

**Code Implementation:**

```java
void addFirst(int value) {
    Node newNode = new Node(value);
    
    if (this.head == null) {
        this.head = newNode;
        return;
    }
    
    newNode.next = this.head;
    this.head = newNode;
}
```

**Step-by-step explanation:**

1. **Create the node:**
   ```java
   Node newNode = new Node(value);
   ```

2. **Check if the list is empty:**
   ```java
   if (this.head == null) {
       this.head = newNode;
       return;
   }
   ```
   If the list is empty, the new node becomes the head.

3. **Otherwise, do two pointer updates:**
   ```java
   newNode.next = this.head;  // New node points to old head
   this.head = newNode;         // Head moves to new node
   ```

**Time Complexity: O(1)**

---

#### Step 4: Removing an Element from the Beginning

Now we remove the first element.

**Before removal:**

```
Head
 ↓
5 → 10 → 20
```

**After removal:**

```
Head
 ↓
10 → 20
```

**Only one pointer update is required.**

**Code:**

```java
Integer removeFirst() {
    if (this.head == null) {
        return null;
    }
    
    int value = this.head.value;
    this.head = this.head.next;
    
    return value;
}
```

**Complexity: O(1)** — No traversal required.

---

#### Step 5: Traversing the Linked List

To view all elements we must **walk through the chain**.

```
Node → Node → Node
```

**Code:**

```java
void printList() {
    Node current = this.head;
    
    while (current != null) {
        System.out.print(current.value + " -> ");
        current = current.next;
    }
    
    System.out.println("null");
}
```

This process is called **iteration**.

**Time Complexity: O(n)** — Because we must visit every node.

---

### Applying Linked Lists — Playlist System

Now we apply this to a real use case.

A playlist behaves exactly like a linked list.

Songs are connected sequentially:

```
Song A → Song B → Song C
```

When the user presses "next", we simply move forward.

#### Playlist Implementation

```java
class Playlist {
    private LinkedList list;
    
    Playlist() {
        this.list = new LinkedList();
    }
    
    void addSong(String song) {
        // For simplicity, we'll store songs as integers
        // In a real system, you'd have a Song class
        this.list.addFirst(song.hashCode());
    }
    
    void showPlaylist() {
        this.list.printList();
    }
}
```

**Usage:**

```java
public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        
        playlist.addSong("Song A");
        playlist.addSong("Song B");
        playlist.addSong("Song C");
        
        playlist.showPlaylist();
    }
}
```

---

## Lesson 2 — Stacks

### Introduction

A stack is one of the **most important structures** in programming.

Many students do not realize that **every program uses stacks constantly**.

---

### Real System Example — Undo Feature

Imagine a **text editor**.

**User actions:**
1. Type "hello"
2. Type "world"
3. Delete word
4. Bold text

These actions are stored like this:

```
top
│
[bold text]
[delete word]
[type world]
[type hello]
```

**Undo removes the most recent action first.**

That is: **Last In First Out (LIFO)**

---

### Stack Operations

| Operation | Description | Time Complexity |
|-----------|-------------|-----------------|
| `push(value)` | Add element to top | O(1) |
| `pop()` | Remove element from top | O(1) |
| `peek()` | View top element without removing | O(1) |

#### Visual Representation

```
Top
 │
[Delete]
[Type World]
[Type Hello]
```

---

### Implementing Stack Using Linked List

Stacks operate only at **one end**, so the head of a linked list is perfect.

#### Stack Class

```java
class Stack {
    private LinkedList list;
    
    Stack() {
        this.list = new LinkedList();
    }
    
    void push(int value) {
        this.list.addFirst(value);
    }
    
    Integer pop() {
        return this.list.removeFirst();
    }
    
    Integer peek() {
        return this.list.head != null ? this.list.head.value : null;
    }
    
    boolean isEmpty() {
        return this.list.head == null;
    }
}
```

---

### Undo Manager Example

```java
class UndoManager {
    private Stack stack;
    
    UndoManager() {
        this.stack = new Stack();
    }
    
    void perform(String action) {
        System.out.println("Action: " + action);
        this.stack.push(action.hashCode());
    }
    
    void undo() {
        Integer action = this.stack.pop();
        
        if (action == null) {
            System.out.println("Nothing to undo");
            return;
        }
        
        System.out.println("Undo: " + action);
    }
}
```

#### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        UndoManager editor = new UndoManager();
        
        editor.perform("type hello");
        editor.perform("type world");
        editor.perform("delete world");
        
        editor.undo();
        editor.undo();
    }
}
```

**Output:**
```
Action: type hello
Action: type world
Action: delete world
Undo: 1234567890
Undo: 9876543210
```

---

## Lesson 3 — Queues

### Introduction

Queues are used whenever systems process tasks **in order**.

### Real-World Examples

| System | Use Case |
|--------|----------|
| **Print Queues** | Documents printed in order |
| **CPU Scheduling** | Processes executed sequentially |
| **Task Pipelines** | Jobs processed one by one |
| **API Rate Limiting** | Requests handled in order |
| **Message Brokers** | Kafka, RabbitMQ message queues |

---

### Queue Concept

Queues operate on: **First In First Out (FIFO)**

**Example:**

```
Customer line
Front → Alice → Bob → Charlie → David ← Back
```

Alice is served first.

---

### Queue Operations

| Operation | Description | Time Complexity |
|-----------|-------------|-----------------|
| `enqueue(value)` | Add element to back | O(1) |
| `dequeue()` | Remove element from front | O(1) |
| `peek()` | View front element without removing | O(1) |

---

### Queue Using Linked List

Unlike stacks, queues require access to **both ends**.

```
Head → Front
Tail → Back
```

---

### Queue Implementation

```java
class Queue {
    Node head;  // Front of queue
    Node tail;  // Back of queue
    
    Queue() {
        this.head = null;
        this.tail = null;
    }
    
    void enqueue(int value) {
        Node node = new Node(value);
        
        if (this.tail == null) {
            // First element
            this.head = node;
            this.tail = node;
            return;
        }
        
        // Add to back
        this.tail.next = node;
        this.tail = node;
    }
    
    Integer dequeue() {
        if (this.head == null) {
            return null;
        }
        
        int value = this.head.value;
        this.head = this.head.next;
        
        // If queue is now empty, reset tail
        if (this.head == null) {
            this.tail = null;
        }
        
        return value;
    }
    
    Integer peek() {
        return this.head != null ? this.head.value : null;
    }
    
    boolean isEmpty() {
        return this.head == null;
    }
}
```

---

### Real System — Customer Support Queue

Incoming support tickets must be handled **in order**.

```
Ticket1 → Ticket2 → Ticket3
```

#### Example Implementation

```java
class SupportQueue {
    private Queue queue;
    
    SupportQueue() {
        this.queue = new Queue();
    }
    
    void addTicket(String ticket) {
        this.queue.enqueue(ticket.hashCode());
        System.out.println("Ticket added: " + ticket);
    }
    
    void processTicket() {
        Integer ticket = this.queue.dequeue();
        
        if (ticket == null) {
            System.out.println("No tickets to process");
            return;
        }
        
        System.out.println("Processing ticket: " + ticket);
    }
}
```

#### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        SupportQueue queue = new SupportQueue();
        
        queue.addTicket("Ticket1");
        queue.addTicket("Ticket2");
        queue.addTicket("Ticket3");
        
        queue.processTicket();
        queue.processTicket();
        queue.processTicket();
    }
}
```

---

## 📊 Complexity Comparison

| Operation | Array | Linked List | Stack | Queue |
|-----------|-------|-------------|-------|--------|
| **Insert at beginning** | O(n) | O(1) | O(1) | O(1) |
| **Insert at end** | O(1) | O(n)* | N/A | O(1) |
| **Remove from beginning** | O(n) | O(1) | O(1) | O(1) |
| **Access by index** | O(1) | O(n) | N/A | N/A |
| **Search** | O(n) | O(n) | O(n) | O(n) |

*Can be O(1) if tail pointer is maintained

---

## 🎯 Key Takeaways

1. **Linked Lists** provide efficient insertion/deletion without memory shifting
2. **Stacks** use LIFO for undo operations, function calls, and expression evaluation
3. **Queues** use FIFO for ordered processing (print queues, task scheduling)
4. **Real systems** use these structures constantly — understanding them is essential

---

## 📚 Additional Resources

- [Java Collections Framework](https://docs.oracle.com/javase/tutorial/collections/)
- [Data Structures in Java](https://www.geeksforgeeks.org/data-structures/)
- [Algorithm Complexity Analysis](https://www.bigocheatsheet.com/)

---

## 🔗 Related Labs

After completing these lessons, practice with the hands-on labs:

- [Lab 1: Browser Back Button (Stack)](../labs/README.md#lab-1--stack)
- [Lab 2: Restaurant Order System (Queue)](../labs/README.md#lab-2--queue)
- [Lab 3: Train Route System (Linked List)](../labs/README.md#lab-3--linked-list)

---

## 📝 JavaScript Version

For the JavaScript implementation of these lessons, see [README.md](./README.md).

---

**Last Updated:** March 2026
