# Week 5: Linked Lists and Iterators

## Overview

This week covers two fundamental topics in data structures and Java collections:
1. **Linked Lists** - Understanding dynamic data structures
2. **Iterators** - Mastering collection traversal

---

## Lesson 1: Linked Lists

**File**: `Lesson1_LinkedLists.md`

**Topics Covered:**
- Introduction to linked lists
- Building linked lists in JavaScript (conceptual introduction)
- Implementing linked lists in Java
- Comparing arrays vs linked lists
- Types of linked lists (singly, doubly, circular)
- When to use linked lists

**Lab**: Student Roster using Linked Lists (10-15 minutes)
- Starter code: `Lab1_StudentLinkedList_Starter.java`

**Key Concepts:**
- Node structure (data + pointer)
- Dynamic memory allocation
- Traversal algorithms
- Insertion and deletion operations

---

## Lesson 2: Iterators

**File**: `Lesson2_Iterators.md`

**Topics Covered:**
- Iterator interface and methods
- Enhanced for-loop (for-each)
- ListIterator for bidirectional traversal
- Custom iterator implementation
- Comparing iteration methods
- Common iterator patterns
- Best practices

**Lab**: Iterator Fundamentals (10-15 minutes)
- Basic: Iterator operations with ArrayList and LinkedList
- In-Depth: Custom iterator for BookShelf class
- Starter code: `Lab2_Iterator_Starter.java`

**Key Concepts:**
- Iterator vs enhanced for-loop
- Safe element removal during iteration
- ListIterator capabilities
- Implementing Iterable interface
- Iterator design patterns

---

## Learning Objectives

By the end of this week, students will be able to:

1. **Linked Lists:**
   - Explain the difference between arrays and linked lists
   - Implement a basic linked list from scratch
   - Perform insertion, deletion, and traversal operations
   - Choose appropriate data structures based on use case

2. **Iterators:**
   - Use Iterator interface with collections
   - Safely remove elements during iteration
   - Implement custom iterators for custom classes
   - Use ListIterator for bidirectional traversal
   - Understand how enhanced for-loop works internally

---

## Files in This Week

```
week5/
├── Lesson1_LinkedLists.md          # Main lesson content
├── Lesson2_Iterators.md            # Main lesson content
├── Lab1_StudentLinkedList_Starter.java  # Lab 1 starter code
├── Lab2_Iterator_Starter.java      # Lab 2 starter code
└── README.md                       # This file
```

---

## Prerequisites

- Understanding of arrays and ArrayLists
- Basic Java OOP concepts (classes, objects, methods)
- Familiarity with collections (ArrayList, LinkedList)
- Understanding of generics (from previous weeks)

---

## Suggested Schedule

### Day 1: Linked Lists
- **Lecture**: Lesson 1 content (45-60 minutes)
- **Lab**: Student Roster implementation (10-15 minutes)
- **Practice**: Additional linked list exercises

### Day 2: Iterators
- **Lecture**: Lesson 2 content (45-60 minutes)
- **Lab**: Iterator fundamentals (10-15 minutes)
- **Practice**: Custom iterator implementation

---

## Additional Resources

### Linked Lists
- [Oracle Java Documentation - LinkedList](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)
- Visualizations: [VisuAlgo - Linked List](https://visualgo.net/en/list)

### Iterators
- [Oracle Java Documentation - Iterator](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)
- [Oracle Java Documentation - ListIterator](https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html)
- [Oracle Java Documentation - Iterable](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)

---

## Assessment Ideas

1. **Linked Lists:**
   - Implement a doubly linked list
   - Reverse a linked list
   - Detect cycles in a linked list
   - Merge two sorted linked lists

2. **Iterators:**
   - Implement a custom iterator for a binary tree
   - Create a filtered iterator that skips certain elements
   - Implement a reverse iterator for ArrayList
   - Build an iterator that combines multiple collections

---

## Common Pitfalls

### Linked Lists
- ❌ Forgetting to update size counter
- ❌ Not handling empty list cases
- ❌ Losing references (memory leaks)
- ❌ Off-by-one errors in traversal

### Iterators
- ❌ Modifying collection while iterating (ConcurrentModificationException)
- ❌ Calling `next()` without checking `hasNext()` first
- ❌ Calling `remove()` without calling `next()` first
- ❌ Using enhanced for-loop when removal is needed

---

## Next Week Preview

After mastering linked lists and iterators, students will be ready for:
- Stacks and Queues
- Trees and Binary Trees
- Hash Tables
- Advanced collection classes

---

## Questions or Issues?

If you encounter any issues or have questions:
1. Review the lesson materials
2. Check the starter code comments
3. Test with small examples first
4. Use debugger to trace execution

Good luck and happy coding! 🚀
