/**
 * Lab 1 Starter Code: Student Roster Using Linked Lists
 * 
 * Complete the methods marked with TODO comments.
 * Test your implementation in the main method.
 */

public class Lab1_StudentLinkedList_Starter {
    
    // Student class
    static class Student {
        private String name;
        private String studentId;
        private double gpa;
        
        // TODO: Add constructor
        
        // TODO: Add getters and setters
        
        // TODO: Add toString() method
    }
    
    // Node class for linked list
    static class Node {
        Student student;
        Node next;
        
        // TODO: Add constructor
    }
    
    // StudentLinkedList class
    static class StudentLinkedList {
        private Node head;
        private int size;
        
        public StudentLinkedList() {
            this.head = null;
            this.size = 0;
        }
        
        // TODO: Implement append method
        public void append(Student student) {
            // Add student to the end of the list
        }
        
        // TODO: Implement prepend method
        public void prepend(Student student) {
            // Add student to the beginning of the list
        }
        
        // TODO: Implement delete method
        public boolean delete(String studentId) {
            // Remove student by ID, return true if found and removed
            return false;
        }
        
        // TODO: Implement find method
        public Student find(String studentId) {
            // Find and return student by ID, or null if not found
            return null;
        }
        
        // TODO: Implement printAll method
        public void printAll() {
            // Print all students in the list
        }
        
        // TODO: Implement getAverageGPA method
        public double getAverageGPA() {
            // Calculate and return the average GPA
            return 0.0;
        }
        
        // TODO: Implement getSize method
        public int getSize() {
            // Return the number of students
            return 0;
        }
    }
    
    // Test your implementation here
    public static void main(String[] args) {
        // TODO: Create a StudentLinkedList
        // TODO: Add some students
        // TODO: Test all your methods
        
        System.out.println("Lab 1: Student Roster Using Linked Lists");
        System.out.println("Complete the TODO items above!");
    }
}
