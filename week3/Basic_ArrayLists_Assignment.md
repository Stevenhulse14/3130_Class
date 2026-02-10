# Basic ArrayLists Assignment

## Learning Objectives

By completing this assignment, you will:
- Understand how to create and manipulate ArrayLists
- Practice using ArrayList methods (add, remove, get, set, size)
- Learn to iterate through ArrayLists
- Understand dynamic sizing capabilities
- Work with wrapper classes for primitives
- Compare ArrayLists with arrays

## Prerequisites

- Completion of Basic Arrays Assignment
- Understanding of objects and classes
- Basic knowledge of generics (syntax: `ArrayList<String>`)

---

## Exercise 1: ArrayList Basics

Create a file named `ArrayListBasics.java` and complete the following:

### Part A: String ArrayList

1. Create an empty `ArrayList<String>` called `fruits`
2. Add the following fruits: "Apple", "Banana", "Orange", "Grape", "Mango"
3. Print the ArrayList (it should display nicely!)
4. Print the size of the ArrayList
5. Get and print the element at index 2
6. Change the element at index 1 to "Blueberry"
7. Remove "Grape" from the list
8. Print the ArrayList again to see changes

**Expected Output:**
```
Fruits: [Apple, Banana, Orange, Grape, Mango]
Size: 5
Element at index 2: Orange
Fruits after changes: [Apple, Blueberry, Orange, Mango]
```

### Part B: Integer ArrayList

1. Create an `ArrayList<Integer>` called `numbers`
2. Add the numbers: 10, 20, 30, 40, 50
3. Add the number 25 at index 2 (insert, don't replace)
4. Remove the number 40
5. Print the sum of all numbers
6. Print the average of all numbers

**Note**: Remember to use `Integer` (wrapper class), not `int` (primitive).

---

## Exercise 2: ArrayList Operations

Create a file named `ArrayListOperations.java` and implement the following methods:

### Method 1: Remove Duplicates
```java
public static ArrayList<String> removeDuplicates(ArrayList<String> list)
```
- Takes an ArrayList of Strings
- Returns a new ArrayList with duplicates removed
- Original list should remain unchanged
- Example: `["a", "b", "a", "c", "b"]` → `["a", "b", "c"]`

### Method 2: Merge Lists
```java
public static ArrayList<Integer> mergeLists(ArrayList<Integer> list1, ArrayList<Integer> list2)
```
- Takes two ArrayLists of Integers
- Returns a new ArrayList containing all elements from both lists
- Example: `[1, 2, 3]` and `[4, 5, 6]` → `[1, 2, 3, 4, 5, 6]`

### Method 3: Find Common Elements
```java
public static ArrayList<String> findCommon(ArrayList<String> list1, ArrayList<String> list2)
```
- Takes two ArrayLists of Strings
- Returns a new ArrayList containing elements that appear in both lists
- Example: `["a", "b", "c"]` and `["b", "c", "d"]` → `["b", "c"]`

### Method 4: Reverse ArrayList
```java
public static void reverseArrayList(ArrayList<Integer> list)
```
- Takes an ArrayList of Integers
- Reverses the list in-place (modifies the original)
- Example: `[1, 2, 3, 4]` → `[4, 3, 2, 1]`

**Test your methods in main():**
```java
public static void main(String[] args) {
    ArrayList<String> list1 = new ArrayList<>(Arrays.asList("a", "b", "a", "c"));
    ArrayList<String> unique = removeDuplicates(list1);
    System.out.println("Original: " + list1);
    System.out.println("No duplicates: " + unique);
    
    // Test other methods...
}
```

---

## Exercise 3: ArrayList vs Array

Create a file named `ArrayListVsArray.java` to demonstrate the differences:

### Task

1. **Fixed Size Problem**: 
   - Create an array of size 3 and try to add a 4th element (explain why it fails)
   - Create an ArrayList and add 4 elements (show it works)

2. **Adding Elements**:
   - Show how arrays cannot add elements dynamically
   - Show how ArrayLists can add elements at any time

3. **Removing Elements**:
   - Show how arrays cannot truly remove elements (only set to null/0)
   - Show how ArrayLists can remove elements and shrink

4. **Printing**:
   - Print an array directly (shows address)
   - Print an ArrayList directly (shows contents nicely)

**Write comments explaining each difference and why ArrayLists are more flexible.**

---

## Exercise 4: ArrayList of Objects

Create a file named `BookManager.java`:

### Part A: Book Class

Create a `Book` class with:
- `title` (String)
- `author` (String)
- `pages` (int)
- `price` (double)
- Constructor, getters, setters, and `toString()` method

### Part B: Book Management System

Create a `BookManager` class that uses an `ArrayList<Book>` to manage books:

1. **Add Book**: Method to add a book to the collection
2. **Remove Book**: Method to remove a book by title
3. **Find Book**: Method to find and return a book by title (returns null if not found)
4. **Total Pages**: Method to calculate total pages of all books
5. **Average Price**: Method to calculate average price
6. **List All Books**: Method to print all books

**Test in main():**
```java
public static void main(String[] args) {
    BookManager manager = new BookManager();
    
    manager.addBook(new Book("Java Programming", "John Doe", 500, 49.99));
    manager.addBook(new Book("Data Structures", "Jane Smith", 600, 59.99));
    manager.addBook(new Book("Algorithms", "Bob Johnson", 700, 69.99));
    
    manager.listAllBooks();
    System.out.println("Total pages: " + manager.getTotalPages());
    System.out.println("Average price: $" + manager.getAveragePrice());
    
    Book found = manager.findBook("Java Programming");
    if (found != null) {
        System.out.println("Found: " + found);
    }
    
    manager.removeBook("Data Structures");
    manager.listAllBooks();
}
```

---

## Exercise 5: ArrayList Iteration

Create a file named `ArrayListIteration.java`:

### Task

Create an `ArrayList<Integer>` with values: `5, 10, 15, 20, 25, 30`

Demonstrate **three different ways** to iterate through the ArrayList:

1. **Traditional for loop** (using index)
2. **Enhanced for loop** (for-each)
3. **Iterator** (using `Iterator<Integer>`)

For each iteration method:
- Print each element
- Calculate the sum of all elements
- Count how many elements are greater than 15

**Expected Output:**
```
Method 1: Traditional for loop
Elements: 5 10 15 20 25 30
Sum: 105
Count > 15: 3

Method 2: Enhanced for loop
Elements: 5 10 15 20 25 30
Sum: 105
Count > 15: 3

Method 3: Iterator
Elements: 5 10 15 20 25 30
Sum: 105
Count > 15: 3
```

---

## Exercise 6: ArrayList Sorting and Searching

Create a file named `ArrayListSorting.java`:

### Task

1. Create an `ArrayList<String>` with: "Zebra", "Apple", "Banana", "Orange", "Grape"
2. Print the unsorted list
3. Sort the list using `Collections.sort()`
4. Print the sorted list
5. Search for "Banana" using `Collections.binarySearch()` (must be sorted first!)
6. Search for "Mango" (should return negative value indicating not found)

**Then do the same with an `ArrayList<Integer>`:**
- Create list: `42, 15, 8, 23, 16, 4`
- Sort and search for 23
- Search for 100 (not in list)

**Expected Output:**
```
Unsorted: [Zebra, Apple, Banana, Orange, Grape]
Sorted: [Apple, Banana, Grape, Orange, Zebra]
Found 'Banana' at index: 1
'Mango' not found (would be at index: -4)

Unsorted: [42, 15, 8, 23, 16, 4]
Sorted: [4, 8, 15, 16, 23, 42]
Found 23 at index: 4
100 not found (would be at index: -7)
```

---

## Challenge Exercise (Optional)

Create a file named `ArrayListChallenge.java`:

### Task: ArrayList Shuffle and Deal

Simulate dealing cards from a deck:

1. Create an `ArrayList<String>` representing a deck of cards
   - Suits: Hearts, Diamonds, Clubs, Spades
   - Values: Ace, 2-10, Jack, Queen, King
   - Example: "Ace of Hearts", "2 of Diamonds", etc.

2. Shuffle the deck using `Collections.shuffle()`

3. Deal 5 cards to a "hand" (remove from deck, add to hand)

4. Print the hand and remaining deck size

**Bonus**: Deal to multiple hands (players) and show each hand.

---

## Submission Checklist

- [ ] `ArrayListBasics.java` - Exercise 1 complete
- [ ] `ArrayListOperations.java` - Exercise 2 complete with all methods
- [ ] `ArrayListVsArray.java` - Exercise 3 complete with explanations
- [ ] `BookManager.java` - Exercise 4 complete with Book class
- [ ] `ArrayListIteration.java` - Exercise 5 complete with 3 iteration methods
- [ ] `ArrayListSorting.java` - Exercise 6 complete
- [ ] All code compiles without errors
- [ ] All code runs and produces expected output
- [ ] Code is properly commented

---

## Notes

- Remember to import `java.util.ArrayList`, `java.util.Collections`, `java.util.Iterator`, `java.util.Arrays`
- ArrayList uses **methods** (`size()`, `get()`, `add()`, etc.), not fields or operators
- ArrayList can only hold objects - use wrapper classes (`Integer`, `Double`, `Boolean`) for primitives
- ArrayList automatically resizes - no need to worry about capacity (usually)
- Use `Collections.sort()` to sort ArrayLists
- ArrayList's `toString()` is nicely implemented - printing shows contents, not address

