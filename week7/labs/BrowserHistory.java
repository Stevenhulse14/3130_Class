/**
 * Lab 1: Browser Back Button System
 * 
 * This program demonstrates the use of a Stack data structure
 * to implement browser history navigation, similar to how web browsers
 * handle the "Back" button functionality.
 * 
 * @author Student Name
 * @version 1.0
 */

import java.util.Stack;

/**
 * BrowserHistory class manages web page navigation history using a Stack.
 * 
 * The stack stores URLs in LIFO (Last In First Out) order, which is
 * perfect for browser back navigation - the most recently visited page
 * is the first one to be returned when going back.
 */
class BrowserHistory {
    private Stack<String> history;
    
    /**
     * Constructor initializes an empty history stack.
     */
    public BrowserHistory() {
        this.history = new Stack<>();
    }
    
    /**
     * Visits a new URL and adds it to the history stack.
     * 
     * @param url The URL of the page being visited
     */
    public void visit(String url) {
        history.push(url);
        System.out.println("Visited: " + url);
    }
    
    /**
     * Navigates back to the previous page in history.
     * 
     * Removes the current page from the stack and displays the
     * previous page. If there's only one page or no pages, it
     * displays an appropriate message.
     */
    public void back() {
        // Need at least 2 pages to go back
        if (history.size() <= 1) {
            System.out.println("No previous page.");
            return;
        }
        
        // Remove current page
        history.pop();
        
        // Show the page we're going back to
        System.out.println("Back to: " + history.peek());
    }
    
    /**
     * Displays the current page without navigating away.
     * 
     * @return The current page URL, or null if no pages visited
     */
    public String getCurrentPage() {
        if (history.isEmpty()) {
            return null;
        }
        return history.peek();
    }
    
    /**
     * Displays the current page.
     */
    public void showCurrentPage() {
        if (history.isEmpty()) {
            System.out.println("No pages visited yet.");
        } else {
            System.out.println("Current page: " + history.peek());
        }
    }
    
    /**
     * Returns the number of pages in history.
     * 
     * @return The size of the history stack
     */
    public int getHistorySize() {
        return history.size();
    }
}

/**
 * Main class to test the BrowserHistory system.
 */
public class BrowserHistoryMain {
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        
        // Simulate browsing session
        System.out.println("=== Browsing Session ===");
        browser.visit("google.com");
        browser.visit("youtube.com");
        browser.visit("wikipedia.org");
        browser.visit("github.com");
        
        System.out.println("\n=== Navigation ===");
        browser.showCurrentPage();
        
        System.out.println("\n=== Going Back ===");
        browser.back();
        browser.back();
        
        System.out.println("\n=== Final State ===");
        browser.showCurrentPage();
        System.out.println("History size: " + browser.getHistorySize());
        
        // Test edge case: trying to go back when only one page
        System.out.println("\n=== Edge Case Test ===");
        browser.back(); // Should show "No previous page"
    }
}
