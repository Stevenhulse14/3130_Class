import java.util.Vector;

/**
 * VectorUtils class containing generic utility methods for working with Vectors.
 */
public class VectorUtils {

    /**
     * Swaps two elements in any Vector.
     * @param vec the Vector
     * @param index1 first index
     * @param index2 second index
     * @param <T> the type of elements in the Vector
     */
    public static <T> void swap(Vector<T> vec, int index1, int index2) {
        if (vec == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        
        if (index1 < 0 || index1 >= vec.size() || index2 < 0 || index2 >= vec.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        
        T temp = vec.get(index1);
        vec.set(index1, vec.get(index2));
        vec.set(index2, temp);
    }

    /**
     * Finds the maximum element in a Vector of Comparable elements.
     * @param vec the Vector
     * @param <T> the type that extends Comparable
     * @return the maximum element
     */
    public static <T extends Comparable<T>> T findMax(Vector<T> vec) {
        if (vec == null || vec.isEmpty()) {
            throw new IllegalArgumentException("Vector cannot be null or empty");
        }
        
        T max = vec.get(0);
        for (int i = 1; i < vec.size(); i++) {
            T current = vec.get(i);
            if (current.compareTo(max) > 0) {
                max = current;
            }
        }
        return max;
    }

    /**
     * Counts occurrences of target in Vector using equals() for comparison.
     * @param vec the Vector
     * @param target the target element to count
     * @param <T> the type of elements
     * @return number of occurrences
     */
    public static <T> int countMatches(Vector<T> vec, T target) {
        if (vec == null) {
            return 0;
        }
        
        int count = 0;
        for (T element : vec) {
            if (target == null ? element == null : target.equals(element)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Simple Predicate interface for filtering.
     */
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    /**
     * Returns a new Vector containing elements that match the condition.
     * @param vec the source Vector
     * @param condition the predicate to test elements
     * @param <T> the type of elements
     * @return filtered Vector
     */
    public static <T> Vector<T> filter(Vector<T> vec, Predicate<T> condition) {
        if (vec == null) {
            return new Vector<>();
        }
        
        Vector<T> result = new Vector<>();
        for (T element : vec) {
            if (condition.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Filters products by category (non-generic version for comparison).
     * @param vec the Vector of products
     * @param category the category to filter by
     * @return filtered Vector
     */
    public static Vector<Product> filterProducts(Vector<Product> vec, String category) {
        return filter(vec, product -> category.equals(product.getCategory()));
    }

    /**
     * Sums numbers in a Vector (works with Integer, Double, etc.).
     * @param numbers the Vector of numbers
     * @param <T> the type extending Number
     * @return sum as double
     */
    public static <T extends Number> double sumNumbers(Vector<T> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (T number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * Calculates average of numbers in a Vector.
     * @param numbers the Vector of numbers
     * @param <T> the type extending Number
     * @return average as double, or 0.0 if empty
     */
    public static <T extends Number> double averageNumbers(Vector<T> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0.0;
        }
        
        return sumNumbers(numbers) / numbers.size();
    }
}

