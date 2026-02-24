/**
 * Lab 2 Starter Code: Iterator Fundamentals
 * 
 * Complete the tasks below.
 * Use iterators to solve each problem.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Lab2_Iterator_Starter {
    
    // ============================================
    // BASIC LAB: Iterator Fundamentals
    // ============================================
    
    /**
     * Task 1: Create a program that:
     * 1. Creates an ArrayList of 10 random integers (1-100)
     * 2. Uses an Iterator to print all elements
     * 3. Uses an Iterator to remove all even numbers
     * 4. Print the remaining list
     */
    public static void task1_BasicIterator() {
        System.out.println("=== Task 1: Basic Iterator ===");
        
        // TODO: Create ArrayList with 10 random integers (1-100)
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        
        // TODO: Add 10 random integers
        
        // TODO: Print all elements using Iterator
        
        // TODO: Remove all even numbers using Iterator
        
        // TODO: Print remaining list
    }
    
    /**
     * Task 2: Create a program that:
     * 1. Creates a LinkedList of student names
     * 2. Uses ListIterator to:
     *    - Print all names forward
     *    - Print all names backward
     *    - Replace names starting with "A" with "A-Student"
     * 3. Print the final list
     */
    public static void task2_ListIterator() {
        System.out.println("\n=== Task 2: ListIterator ===");
        
        // TODO: Create LinkedList with student names
        LinkedList<String> names = new LinkedList<>();
        
        // TODO: Add some names (include some starting with "A")
        
        // TODO: Print all names forward using ListIterator
        
        // TODO: Print all names backward using ListIterator
        
        // TODO: Replace names starting with "A" using ListIterator
        
        // TODO: Print final list
    }
    
    // ============================================
    // IN-DEPTH LAB: Custom Iterator
    // ============================================
    
    /**
     * Book class for the custom iterator lab
     */
    static class Book {
        private String title;
        private String author;
        private int pages;
        
        // TODO: Add constructor
        
        // TODO: Add getters
        
        // TODO: Add toString method
    }
    
    /**
     * BookShelf class that implements Iterable
     */
    static class BookShelf implements Iterable<Book> {
        private ArrayList<Book> books;
        
        public BookShelf() {
            this.books = new ArrayList<>();
        }
        
        public void addBook(Book book) {
            books.add(book);
        }
        
        // TODO: Implement iterator() method to return Iterator<Book>
        @Override
        public Iterator<Book> iterator() {
            // Return a standard iterator
            return null;
        }
        
        // TODO: Implement iterator(String author) to filter by author
        public Iterator<Book> iterator(String author) {
            // Return an iterator that only returns books by the specified author
            return null;
        }
        
        // TODO: Bonus: Implement iterator(int minPages) to filter by pages
        public Iterator<Book> iterator(int minPages) {
            // Return an iterator that only returns books with pages >= minPages
            return null;
        }
    }
    
    /**
     * Task 3: Test the custom BookShelf iterator
     */
    public static void task3_CustomIterator() {
        System.out.println("\n=== Task 3: Custom Iterator ===");
        
        // TODO: Create BookShelf and add books
        BookShelf shelf = new BookShelf();
        
        // TODO: Add some books
        
        // TODO: Iterate all books using enhanced for-loop
        
        // TODO: Iterate books by specific author
        
        // TODO: Bonus: Iterate books with pages > threshold
    }
    
    public static void main(String[] args) {
        System.out.println("Lab 2: Iterator Fundamentals");
        System.out.println("Complete the TODO items in each task!\n");
        
        // Uncomment to test each task:
        // task1_BasicIterator();
        // task2_ListIterator();
        // task3_CustomIterator();
    }
}
