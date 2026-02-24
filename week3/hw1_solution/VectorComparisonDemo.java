import java.util.ArrayList;
import java.util.Vector;

/**
 * VectorComparisonDemo class that compares Vector and ArrayList performance.
 */
public class VectorComparisonDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("Vector vs ArrayList Performance Comparison");
        System.out.println("=".repeat(80));
        
        // Test data sizes
        int addCount = 10000;
        int accessCount = 1000;
        
        // Create test products
        Vector<Product> testProducts = createTestProducts(addCount);
        
        // Test Vector
        System.out.println("\n--- Testing Vector ---");
        long vectorAddTime = testAddOperation(new Vector<>(), testProducts);
        Vector<Product> vectorProducts = new Vector<>();
        for (Product p : testProducts) {
            vectorProducts.add(p);
        }
        long vectorAccessTime = testAccessOperation(vectorProducts, accessCount);
        
        // Test ArrayList
        System.out.println("\n--- Testing ArrayList ---");
        long arrayListAddTime = testAddOperation(new ArrayList<>(), testProducts);
        ArrayList<Product> arrayListProducts = new ArrayList<>();
        for (Product p : testProducts) {
            arrayListProducts.add(p);
        }
        long arrayListAccessTime = testAccessOperation(arrayListProducts, accessCount);
        
        // Print comparison report
        printComparisonReport(vectorAddTime, arrayListAddTime, 
                            vectorAccessTime, arrayListAccessTime, 
                            addCount, accessCount);
        
        // Memory comparison (approximate)
        printMemoryComparison();
    }

    /**
     * Creates test products for performance testing.
     */
    private static Vector<Product> createTestProducts(int count) {
        Vector<Product> products = new Vector<>();
        for (int i = 0; i < count; i++) {
            products.add(new Product("P" + String.format("%05d", i), 
                                    "Product " + i, 
                                    "Category " + (i % 5), 
                                    (i + 1) * 10.0, 
                                    i % 100, 
                                    "Supplier " + (i % 3)));
        }
        return products;
    }

    /**
     * Tests add operation performance.
     */
    private static long testAddOperation(java.util.List<Product> list, Vector<Product> products) {
        long startTime = System.nanoTime();
        list.addAll(products);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Add " + products.size() + " products: " + duration + " ms");
        return duration;
    }

    /**
     * Tests random access operation performance.
     */
    private static long testAccessOperation(java.util.List<Product> list, int accessCount) {
        long startTime = System.nanoTime();
        for (int i = 0; i < accessCount; i++) {
            int randomIndex = (int) (Math.random() * list.size());
            list.get(randomIndex);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Access " + accessCount + " random elements: " + duration + " ms");
        return duration;
    }

    /**
     * Prints comparison report.
     */
    private static void printComparisonReport(long vectorAddTime, long arrayListAddTime,
                                            long vectorAccessTime, long arrayListAccessTime,
                                            int addCount, int accessCount) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PERFORMANCE COMPARISON REPORT");
        System.out.println("=".repeat(80));
        
        System.out.println("\nAdd Operation (" + addCount + " elements):");
        System.out.printf("  Vector:    %d ms%n", vectorAddTime);
        System.out.printf("  ArrayList: %d ms%n", arrayListAddTime);
        if (vectorAddTime > 0) {
            double ratio = (double) arrayListAddTime / vectorAddTime;
            System.out.printf("  Ratio:     %.2fx%n", ratio);
            if (ratio < 1.0) {
                System.out.println("  Winner:    ArrayList is " + String.format("%.2f", 1/ratio) + "x faster");
            } else {
                System.out.println("  Winner:    Vector is " + String.format("%.2f", ratio) + "x faster");
            }
        }
        
        System.out.println("\nRandom Access Operation (" + accessCount + " accesses):");
        System.out.printf("  Vector:    %d ms%n", vectorAccessTime);
        System.out.printf("  ArrayList: %d ms%n", arrayListAccessTime);
        if (vectorAccessTime > 0) {
            double ratio = (double) arrayListAccessTime / vectorAccessTime;
            System.out.printf("  Ratio:     %.2fx%n", ratio);
            if (ratio < 1.0) {
                System.out.println("  Winner:    ArrayList is " + String.format("%.2f", 1/ratio) + "x faster");
            } else {
                System.out.println("  Winner:    Vector is " + String.format("%.2f", ratio) + "x faster");
            }
        }
        
        System.out.println("\n" + "-".repeat(80));
        System.out.println("SUMMARY AND RECOMMENDATIONS:");
        System.out.println("-".repeat(80));
        System.out.println("""
            Performance Differences Observed:
            - Vector is synchronized (thread-safe), which adds overhead
            - ArrayList is not synchronized, making it faster for single-threaded operations
            - Both have similar O(1) access time for get() operations
            - Vector's synchronization overhead is most noticeable in write operations
            
            When to choose Vector over ArrayList:
            1. When you need thread-safety and don't want to manage synchronization yourself
            2. When working with legacy code that already uses Vector
            3. When you need Enumeration interface (legacy requirement)
            4. When you need capacity management methods (ensureCapacity, trimToSize)
            
            When to choose ArrayList over Vector:
            1. In single-threaded applications (most common case)
            2. When performance is critical and you can manage synchronization yourself
            3. When you want to use modern Iterator interface
            4. In new code development (ArrayList is the modern standard)
            5. When you don't need the overhead of synchronization
            """);
    }

    /**
     * Prints approximate memory comparison.
     */
    private static void printMemoryComparison() {
        System.out.println("\nMemory Usage (Approximate):");
        System.out.println("  Vector:    Slightly more memory due to synchronization overhead");
        System.out.println("  ArrayList: Less memory, no synchronization overhead");
        System.out.println("  Note:     Both use similar internal array structures");
        System.out.println("            The difference is minimal for most applications");
    }
}

