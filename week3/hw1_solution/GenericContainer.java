import java.util.Vector;

/**
 * GenericContainer class - a generic wrapper around Vector that adds utility methods.
 * @param <T> the type of elements stored in the container
 */
public class GenericContainer<T> {
    private Vector<T> items;

    /**
     * Constructor that initializes an empty Vector.
     */
    public GenericContainer() {
        this.items = new Vector<>();
    }

    /**
     * Constructor with initial capacity.
     */
    public GenericContainer(int initialCapacity) {
        this.items = new Vector<>(initialCapacity);
    }

    /**
     * Adds an item to the container.
     * @param item the item to add
     */
    public void add(T item) {
        if (item != null) {
            items.add(item);
        }
    }

    /**
     * Gets an item by index.
     * @param index the index
     * @return the item at the index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public T get(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items.get(index);
    }

    /**
     * Removes an item from the container.
     * @param item the item to remove
     * @return true if item was found and removed, false otherwise
     */
    public boolean remove(T item) {
        return items.remove(item);
    }

    /**
     * Returns the size of the container.
     * @return number of items
     */
    public int size() {
        return items.size();
    }

    /**
     * Returns a copy of all items.
     * @return new Vector containing all items
     */
    public Vector<T> getAll() {
        Vector<T> copy = new Vector<>();
        copy.addAll(items);
        return copy;
    }

    /**
     * Clears all items from the container.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Checks if the container contains an item.
     * @param item the item to check
     * @return true if contains, false otherwise
     */
    public boolean contains(T item) {
        return items.contains(item);
    }

    /**
     * Adds all items from another Vector.
     * @param other the Vector to add from
     */
    public void addAll(Vector<T> other) {
        if (other != null) {
            items.addAll(other);
        }
    }

    /**
     * Returns string representation of the container.
     */
    @Override
    public String toString() {
        return "GenericContainer{" + items + "}";
    }
}

