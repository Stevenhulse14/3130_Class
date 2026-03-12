# Lab Assignments: Practical Data Structures

## 📋 Lab Overview

Students must implement **three systems**, each demonstrating a different data structure in a real-world application.

| Data Structure | Lab System | Real-World Application |
|----------------|------------|------------------------|
| **Stack** | Browser Back Button | Web browser history navigation |
| **Queue** | Restaurant Order System | Order processing in restaurants |
| **Linked List** | Train Route System | Train station route management |

---

## 🎯 Learning Objectives

By completing these labs, students will:

- ✅ Understand how data structures solve real-world problems
- ✅ Implement stacks, queues, and linked lists from scratch
- ✅ Apply data structures to practical scenarios
- ✅ Analyze time complexity of operations
- ✅ Write clean, maintainable Java code

---

## 📚 Prerequisites

Before starting these labs, ensure you have completed:

- [x] Lesson 1: Linked Lists
- [x] Lesson 2: Stacks  
- [x] Lesson 3: Queues

Review the [lecture materials](../lecture/README.md) if needed.

---

## Lab 1 — Stack: Browser Back Button

### 🎯 Problem Statement

Web browsers maintain a history of pages visited. When the user presses **Back**, the browser returns to the previous page visited.

#### Example Browsing Session

```
google.com → youtube.com → wikipedia.org → github.com
```

**Stack representation:**

```
TOP
│
github.com
wikipedia.org
youtube.com
google.com
```

When the user presses Back, the browser **pops** the most recent page.

```
Back → github.com removed
Back → wikipedia.org removed
```

---

### 📋 Requirements

Students must build a program that:

1. ✅ Stores visited pages in a **stack**
2. ✅ Allows navigating to a new page (`visit()`)
3. ✅ Allows going back to the previous page (`back()`)
4. ✅ Handles edge cases (no history, only one page)

---

### 💻 Implementation Guide

#### Step 1: Create the Stack Class

First, implement a basic stack using a linked list:

```java
class Stack {
    private Node top;
    
    Stack() {
        this.top = null;
    }
    
    void push(String value) {
        // TODO: Implement push operation
    }
    
    String pop() {
        // TODO: Implement pop operation
    }
    
    String peek() {
        // TODO: Implement peek operation
    }
    
    boolean isEmpty() {
        // TODO: Check if stack is empty
    }
    
    int size() {
        // TODO: Return number of elements
    }
}
```

#### Step 2: Create the BrowserHistory Class

```java
class BrowserHistory {
    private Stack<String> history;
    
    BrowserHistory() {
        this.history = new Stack<>();
    }
    
    void visit(String url) {
        // TODO: Add URL to history
        // Print confirmation message
    }
    
    void back() {
        // TODO: Remove current page
        // TODO: Check if there's a previous page
        // Print the page we're going back to
    }
    
    void showCurrentPage() {
        // TODO: Show current page without removing it
    }
}
```

#### Step 3: Complete Implementation

See the provided solution in [`BrowserHistory.java`](./BrowserHistory.java) for reference.

---

### ✅ Expected Output

```java
public class Main {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        
        browser.visit("google.com");
        browser.visit("youtube.com");
        browser.visit("github.com");
        
        browser.back();
        browser.back();
    }
}
```

**Output:**
```
Visited: google.com
Visited: youtube.com
Visited: github.com
Back to: youtube.com
Back to: google.com
```

---

### 🧪 Testing Checklist

- [ ] Can visit multiple pages
- [ ] Back button works correctly
- [ ] Cannot go back when only one page exists
- [ ] Cannot go back when no pages exist
- [ ] Current page is displayed correctly

---

## Lab 2 — Queue: Restaurant Order System

### 🎯 Problem Statement

In restaurants, orders must be prepared **in the order they were received**.

#### Example Orders

```
Order1: Burger
Order2: Pizza
Order3: Salad
```

**Queue representation:**

```
Front → Burger → Pizza → Salad ← Back
```

The kitchen always prepares the **front order first**.

---

### 📋 Requirements

Students must build a system that:

1. ✅ Adds orders to the queue (`addOrder()`)
2. ✅ Processes the next order (`prepareOrder()`)
3. ✅ Displays the next order waiting (`peekNextOrder()`)
4. ✅ Shows all pending orders
5. ✅ Handles empty queue cases

---

### 💻 Implementation Guide

#### Step 1: Create the Queue Class

```java
class Queue {
    private Node front;
    private Node rear;
    
    Queue() {
        this.front = null;
        this.rear = null;
    }
    
    void enqueue(String value) {
        // TODO: Add to back of queue
    }
    
    String dequeue() {
        // TODO: Remove from front of queue
    }
    
    String peek() {
        // TODO: View front without removing
    }
    
    boolean isEmpty() {
        // TODO: Check if queue is empty
    }
}
```

#### Step 2: Create the RestaurantOrders Class

```java
class RestaurantOrders {
    private Queue orders;
    
    RestaurantOrders() {
        this.orders = new Queue();
    }
    
    void addOrder(String order) {
        // TODO: Add order to queue
        // Print confirmation
    }
    
    void prepareOrder() {
        // TODO: Remove and process next order
        // Handle empty queue
    }
    
    void showNextOrder() {
        // TODO: Display next order without removing
    }
    
    void showAllOrders() {
        // TODO: Display all pending orders
    }
}
```

#### Step 3: Complete Implementation

See the provided solution in [`RestaurantOrders.java`](./RestaurantOrders.java) for reference.

---

### ✅ Expected Output

```java
public class Main {
    public static void main(String[] args) {
        RestaurantOrders system = new RestaurantOrders();
        
        system.addOrder("Burger");
        system.addOrder("Pizza");
        system.addOrder("Salad");
        
        system.prepareOrder();
        system.prepareOrder();
        system.prepareOrder();
        system.prepareOrder(); // Should handle empty queue
    }
}
```

**Output:**
```
New order: Burger
New order: Pizza
New order: Salad
Preparing: Burger
Preparing: Pizza
Preparing: Salad
No orders to prepare
```

---

### 🧪 Testing Checklist

- [ ] Orders are added correctly
- [ ] Orders are processed in FIFO order
- [ ] Next order can be viewed without removing
- [ ] Empty queue is handled gracefully
- [ ] All orders can be displayed

---

## Lab 3 — Linked List: Train Route System

### 🎯 Problem Statement

A train travels through a sequence of stations. Sometimes new stations must be inserted between stops.

#### Example Route

```
Station A → Station B → Station C → Station D
```

**Insert new station:**

```
Station A → Station B → Station X → Station C → Station D
```

Linked lists are perfect for this because insertions **do not require shifting elements**.

---

### 📋 Requirements

Students must implement:

1. ✅ Add station to end of route (`addStation()`)
2. ✅ Insert station at specific position (`insertStation()`)
3. ✅ Remove station from route (`removeStation()`)
4. ✅ Print entire route (`printRoute()`)
5. ✅ Find station in route (`findStation()`)
6. ✅ Get route length (`getRouteLength()`)

---

### 💻 Implementation Guide

#### Step 1: Create the Station Node Class

```java
class Station {
    String name;
    Station next;
    
    Station(String name) {
        this.name = name;
        this.next = null;
    }
}
```

#### Step 2: Create the TrainRoute Class

```java
class TrainRoute {
    private Station head;
    
    TrainRoute() {
        this.head = null;
    }
    
    void addStation(String name) {
        // TODO: Add station to end of route
    }
    
    void insertStation(String name, int position) {
        // TODO: Insert station at specific position
    }
    
    boolean removeStation(String name) {
        // TODO: Remove station by name
    }
    
    void printRoute() {
        // TODO: Print all stations in order
    }
    
    boolean findStation(String name) {
        // TODO: Check if station exists
    }
    
    int getRouteLength() {
        // TODO: Return number of stations
    }
}
```

#### Step 3: Complete Implementation

See the provided solution in [`TrainRoute.java`](./TrainRoute.java) for reference.

---

### ✅ Expected Output

```java
public class Main {
    public static void main(String[] args) {
        TrainRoute route = new TrainRoute();
        
        route.addStation("Station A");
        route.addStation("Station B");
        route.addStation("Station C");
        
        route.printRoute();
        
        route.insertStation("Station X", 2);
        route.printRoute();
        
        route.removeStation("Station B");
        route.printRoute();
    }
}
```

**Output:**
```
Route: Station A -> Station B -> Station C
Route: Station A -> Station B -> Station X -> Station C
Route: Station A -> Station X -> Station C
```

---

### 🧪 Testing Checklist

- [ ] Stations can be added to end
- [ ] Stations can be inserted at specific positions
- [ ] Stations can be removed by name
- [ ] Route prints correctly
- [ ] Station search works
- [ ] Route length is calculated correctly
- [ ] Edge cases handled (empty route, invalid positions)

---

## 📝 Submission Guidelines

### Required Files

For each lab, submit:

1. **Source Code** — All `.java` files
2. **Test Program** — `Main.java` demonstrating functionality
3. **README** — Brief explanation of your implementation

### File Structure

```
labs/
├── README.md (this file)
├── BrowserHistory.java
├── RestaurantOrders.java
├── TrainRoute.java
└── Main.java (or separate Main files for each lab)
```

### Code Quality

- ✅ **Comments** — Explain complex logic
- ✅ **Naming** — Use descriptive variable and method names
- ✅ **Structure** — Organize code logically
- ✅ **Error Handling** — Handle edge cases gracefully

---

## 🎓 Grading Rubric

| Criteria | Points | Description |
|----------|--------|-------------|
| **Correctness** | 40% | Code works as specified |
| **Implementation** | 30% | Proper use of data structures |
| **Code Quality** | 20% | Clean, readable, well-commented |
| **Testing** | 10% | Handles edge cases |

---

## 💡 Tips for Success

1. **Start Simple** — Get basic functionality working first
2. **Test Incrementally** — Test each method as you write it
3. **Handle Edge Cases** — Empty structures, single elements, etc.
4. **Draw Diagrams** — Visualize pointer operations before coding
5. **Review Lectures** — Refer back to lesson materials

---

## 🆘 Getting Help

If you're stuck:

1. **Review the lectures** — Concepts are explained there
2. **Draw diagrams** — Visualize the data structure
3. **Trace through examples** — Step through code manually
4. **Ask questions** — Office hours, discussion forums

---

## 📚 Additional Resources

- [Java Documentation](https://docs.oracle.com/javase/tutorial/)
- [Data Structures Visualization](https://visualgo.net/)
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)

---

**Good luck with your implementations!** 🚀
