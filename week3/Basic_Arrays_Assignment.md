# Basic Arrays Assignment

## Learning Objectives

By completing this assignment, you will:
- Understand how to declare and initialize arrays
- Practice accessing and modifying array elements
- Learn to iterate through arrays
- Work with both primitive and object arrays
- Understand array limitations (fixed size)

## Prerequisites

- Basic Java syntax
- Understanding of loops (for, while)
- Understanding of conditionals

---

## Exercise 1: Array Basics

Create a file named `ArrayBasics.java` and complete the following:

### Part A: Integer Array

1. Declare and initialize an array of integers with the values: `10, 20, 30, 40, 50`
2. Print the array using `Arrays.toString()`
3. Print the length of the array
4. Print each element on a separate line using a for loop
5. Change the value at index 2 to `35`
6. Print the array again to verify the change

**Expected Output:**
```
Array: [10, 20, 30, 40, 50]
Length: 5
Element 0: 10
Element 1: 20
Element 2: 30
Element 3: 40
Element 4: 50
Array after change: [10, 20, 35, 40, 50]
```

### Part B: String Array

1. Declare and initialize an array of Strings with your favorite programming languages (at least 4)
2. Print the array
3. Find and print the longest language name
4. Print all languages that start with the letter 'J' (if any)

**Hint**: Use `charAt(0)` to check the first character of a string.

---

## Exercise 2: Array Operations

Create a file named `ArrayOperations.java` and implement the following methods:

### Method 1: Find Maximum
```java
public static int findMax(int[] arr)
```
- Takes an array of integers
- Returns the maximum value in the array
- Assume array is not empty

### Method 2: Calculate Average
```java
public static double calculateAverage(int[] arr)
```
- Takes an array of integers
- Returns the average (as a double)
- Handle empty array (return 0.0)

### Method 3: Count Occurrences
```java
public static int countOccurrences(int[] arr, int target)
```
- Takes an array of integers and a target value
- Returns how many times the target appears in the array

### Method 4: Reverse Array
```java
public static void reverseArray(int[] arr)
```
- Takes an array of integers
- Reverses the array in-place (modifies the original array)
- Example: `[1, 2, 3, 4]` becomes `[4, 3, 2, 1]`

**Test your methods in main():**
```java
public static void main(String[] args) {
    int[] numbers = {5, 2, 8, 1, 9, 3, 8};
    
    System.out.println("Max: " + findMax(numbers));
    System.out.println("Average: " + calculateAverage(numbers));
    System.out.println("Occurrences of 8: " + countOccurrences(numbers, 8));
    
    System.out.println("Before reverse: " + Arrays.toString(numbers));
    reverseArray(numbers);
    System.out.println("After reverse: " + Arrays.toString(numbers));
}
```

---

## Exercise 3: Two-Dimensional Arrays

Create a file named `TwoDimensionalArrays.java` and complete:

### Part A: Matrix Creation

1. Create a 3x3 integer matrix (2D array) initialized with values:
   ```
   1  2  3
   4  5  6
   7  8  9
   ```

2. Print the matrix in a readable format (each row on a new line)

### Part B: Matrix Operations

1. **Sum of all elements**: Write a method to calculate the sum of all elements
2. **Sum of diagonal**: Write a method to calculate the sum of the main diagonal (top-left to bottom-right)
3. **Find element**: Write a method that takes a value and returns `true` if it exists in the matrix

**Expected Output:**
```
Matrix:
1  2  3
4  5  6
7  8  9

Sum of all elements: 45
Sum of diagonal: 15
Element 5 found: true
Element 10 found: false
```

---

## Exercise 4: Array Limitations Demonstration

Create a file named `ArrayLimitations.java` to demonstrate why arrays have limitations:

### Task

1. Create an array of size 5 with some initial values
2. Try to "add" a 6th element (you'll need to explain why this is impossible)
3. Try to "remove" an element (you can set it to null/0, but size stays the same)
4. Print the array before and after to show the size doesn't change

**Write comments explaining:**
- Why arrays can't grow or shrink
- What happens when you try to access an invalid index
- Why this limitation exists

**Example:**
```java
int[] arr = new int[5];
// ... fill array ...
// arr[5] = 100;  // What happens? Why?
// How do we "remove" an element? What's the problem?
```

---

## Exercise 5: Array of Objects

Create a file named `StudentArray.java`:

### Part A: Student Class

Create a `Student` class with:
- `name` (String)
- `id` (int)
- `grade` (double)
- Constructor, getters, and `toString()` method

### Part B: Student Array Operations

1. Create an array of 5 Student objects
2. Initialize them with sample data
3. Print all students
4. Find and print the student with the highest grade
5. Calculate and print the average grade

**Expected Output:**
```
Students:
Student{name='Alice', id=1001, grade=85.5}
Student{name='Bob', id=1002, grade=92.0}
...

Highest Grade: Student{name='Bob', id=1002, grade=92.0}
Average Grade: 87.4
```

---

## Challenge Exercise (Optional)

Create a file named `ArrayChallenge.java`:

### Task: Array Rotation

Write a method that rotates an array to the right by k positions:

```java
public static void rotateRight(int[] arr, int k)
```

**Example:**
- Array: `[1, 2, 3, 4, 5]`
- Rotate by 2: `[4, 5, 1, 2, 3]`

**Hint**: You may need a temporary array or use a clever swapping technique.

---

## Submission Checklist

- [ ] `ArrayBasics.java` - Exercise 1 complete
- [ ] `ArrayOperations.java` - Exercise 2 complete with all methods
- [ ] `TwoDimensionalArrays.java` - Exercise 3 complete
- [ ] `ArrayLimitations.java` - Exercise 4 complete with explanations
- [ ] `StudentArray.java` - Exercise 5 complete with Student class
- [ ] All code compiles without errors
- [ ] All code runs and produces expected output
- [ ] Code is properly commented

---

## Notes

- Remember to import `java.util.Arrays` when using `Arrays.toString()`
- Arrays use zero-based indexing (first element is at index 0)
- Array length is a **field** (`arr.length`), not a method
- Arrays cannot change size after creation
- Use enhanced for-loops when you don't need the index

