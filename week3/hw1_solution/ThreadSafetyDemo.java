import java.util.ArrayList;
import java.util.Vector;

/**
 * ThreadSafetyDemo class demonstrating Vector's thread-safety vs ArrayList.
 * This is a bonus demonstration.
 */
public class ThreadSafetyDemo {
    private static final int NUM_THREADS = 5;
    private static final int OPERATIONS_PER_THREAD = 1000;

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("Thread Safety Demonstration: Vector vs ArrayList");
        System.out.println("=".repeat(80));
        
        // Test Vector (thread-safe)
        System.out.println("\n--- Testing Vector (Thread-Safe) ---");
        testVectorThreadSafety();
        
        // Test ArrayList (not thread-safe)
        System.out.println("\n--- Testing ArrayList (Not Thread-Safe) ---");
        testArrayListThreadSafety();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("CONCLUSION:");
        System.out.println("=".repeat(80));
        System.out.println("""
            Vector is synchronized and handles concurrent access safely.
            ArrayList is not synchronized and may produce:
            - ArrayIndexOutOfBoundsException
            - Lost updates
            - Inconsistent state
            
            For multi-threaded applications, use Vector or synchronize ArrayList manually.
            """);
    }

    /**
     * Tests Vector with multiple threads.
     */
    private static void testVectorThreadSafety() {
        Vector<Product> products = new Vector<>();
        Vector<Thread> threads = new Vector<>();
        
        long startTime = System.currentTimeMillis();
        
        // Create threads that add products
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                    Product p = new Product("P" + threadId + "-" + j, 
                                           "Product " + j, 
                                           "Category", 
                                           10.0, 
                                           5, 
                                           "Supplier");
                    products.add(p);
                }
            });
            threads.add(thread);
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("Expected size: " + (NUM_THREADS * OPERATIONS_PER_THREAD));
        System.out.println("Actual size: " + products.size());
        System.out.println("Time taken: " + duration + " ms");
        
        if (products.size() == NUM_THREADS * OPERATIONS_PER_THREAD) {
            System.out.println("✓ SUCCESS: All operations completed correctly!");
        } else {
            System.out.println("✗ ERROR: Size mismatch! Lost " + 
                             (NUM_THREADS * OPERATIONS_PER_THREAD - products.size()) + " elements");
        }
    }

    /**
     * Tests ArrayList with multiple threads (may produce errors).
     */
    private static void testArrayListThreadSafety() {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        final boolean[] errorOccurred = {false};
        
        long startTime = System.currentTimeMillis();
        
        // Create threads that add products
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < OPERATIONS_PER_THREAD; j++) {
                        Product p = new Product("P" + threadId + "-" + j, 
                                               "Product " + j, 
                                               "Category", 
                                               10.0, 
                                               5, 
                                               "Supplier");
                        products.add(p);
                    }
                } catch (Exception e) {
                    synchronized (errorOccurred) {
                        if (!errorOccurred[0]) {
                            System.out.println("Exception occurred: " + e.getClass().getSimpleName() + 
                                             " - " + e.getMessage());
                            errorOccurred[0] = true;
                        }
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("Expected size: " + (NUM_THREADS * OPERATIONS_PER_THREAD));
        System.out.println("Actual size: " + products.size());
        System.out.println("Time taken: " + duration + " ms");
        
        if (products.size() == NUM_THREADS * OPERATIONS_PER_THREAD && !errorOccurred[0]) {
            System.out.println("✓ No errors occurred (lucky run - not guaranteed)");
        } else {
            System.out.println("✗ Issues detected: Size mismatch or exceptions occurred");
            System.out.println("  This demonstrates ArrayList is NOT thread-safe");
        }
    }
}

