/**
 * Lab 3: Train Route System
 * 
 * This program demonstrates the use of a Linked List data structure
 * to manage a train route with multiple stations. Linked lists are
 * perfect for this because stations can be easily inserted or removed
 * without shifting other elements.
 * 
 * @author Student Name
 * @version 1.0
 */

/**
 * Station class represents a single station node in the train route.
 * 
 * Each station contains:
 * - name: The name of the station
 * - next: Reference to the next station in the route
 */
class Station {
    String name;
    Station next;
    
    /**
     * Constructor creates a new station node.
     * 
     * @param name The name of the station
     */
    Station(String name) {
        this.name = name;
        this.next = null;
    }
}

/**
 * TrainRoute class manages a sequence of stations using a linked list.
 * 
 * This allows efficient insertion and removal of stations at any
 * position in the route without shifting other stations.
 */
class TrainRoute {
    private Station head;
    
    /**
     * Constructor initializes an empty route.
     */
    public TrainRoute() {
        this.head = null;
    }
    
    /**
     * Adds a station to the end of the route.
     * 
     * @param name The name of the station to add
     */
    public void addStation(String name) {
        Station newStation = new Station(name);
        
        if (head == null) {
            // First station in route
            head = newStation;
            return;
        }
        
        // Find the last station
        Station current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        // Add new station to the end
        current.next = newStation;
    }
    
    /**
     * Inserts a station at a specific position in the route.
     * 
     * Position 0 is the beginning of the route.
     * 
     * @param name The name of the station to insert
     * @param position The position where to insert (0-based index)
     */
    public void insertStation(String name, int position) {
        Station newStation = new Station(name);
        
        if (position == 0) {
            // Insert at beginning
            newStation.next = head;
            head = newStation;
            return;
        }
        
        // Find the station before the insertion point
        Station current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        
        if (current == null) {
            System.out.println("Position out of bounds. Adding to end instead.");
            addStation(name);
            return;
        }
        
        // Insert the new station
        newStation.next = current.next;
        current.next = newStation;
    }
    
    /**
     * Removes a station from the route by name.
     * 
     * @param name The name of the station to remove
     * @return true if station was found and removed, false otherwise
     */
    public boolean removeStation(String name) {
        if (head == null) {
            return false;
        }
        
        // Check if removing the first station
        if (head.name.equals(name)) {
            head = head.next;
            return true;
        }
        
        // Find the station before the one to remove
        Station current = head;
        while (current.next != null && !current.next.name.equals(name)) {
            current = current.next;
        }
        
        if (current.next == null) {
            return false; // Station not found
        }
        
        // Remove the station
        current.next = current.next.next;
        return true;
    }
    
    /**
     * Prints the entire route showing all stations in order.
     */
    public void printRoute() {
        if (head == null) {
            System.out.println("Route is empty");
            return;
        }
        
        System.out.print("Route: ");
        Station current = head;
        
        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        
        System.out.println();
    }
    
    /**
     * Checks if a station exists in the route.
     * 
     * @param name The name of the station to find
     * @return true if station exists, false otherwise
     */
    public boolean findStation(String name) {
        Station current = head;
        
        while (current != null) {
            if (current.name.equals(name)) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    /**
     * Returns the number of stations in the route.
     * 
     * @return The length of the route
     */
    public int getRouteLength() {
        int count = 0;
        Station current = head;
        
        while (current != null) {
            count++;
            current = current.next;
        }
        
        return count;
    }
    
    /**
     * Gets the name of the station at a specific position.
     * 
     * @param position The position in the route (0-based index)
     * @return The station name, or null if position is invalid
     */
    public String getStationAt(int position) {
        Station current = head;
        
        for (int i = 0; i < position && current != null; i++) {
            current = current.next;
        }
        
        return current != null ? current.name : null;
    }
}

/**
 * Main class to test the TrainRoute system.
 */
public class TrainRouteMain {
    public static void main(String[] args) {
        TrainRoute route = new TrainRoute();
        
        // Build initial route
        System.out.println("=== Building Route ===");
        route.addStation("Station A");
        route.addStation("Station B");
        route.addStation("Station C");
        route.addStation("Station D");
        
        route.printRoute();
        System.out.println("Route length: " + route.getRouteLength());
        
        // Insert a new station
        System.out.println("\n=== Inserting Station ===");
        route.insertStation("Station X", 2);
        route.printRoute();
        
        // Search for a station
        System.out.println("\n=== Searching ===");
        System.out.println("Station B exists: " + route.findStation("Station B"));
        System.out.println("Station Z exists: " + route.findStation("Station Z"));
        
        // Remove a station
        System.out.println("\n=== Removing Station ===");
        boolean removed = route.removeStation("Station B");
        System.out.println("Station B removed: " + removed);
        route.printRoute();
        
        // Test edge cases
        System.out.println("\n=== Edge Case Tests ===");
        System.out.println("Route length: " + route.getRouteLength());
        System.out.println("Station at position 1: " + route.getStationAt(1));
        System.out.println("Station at position 10: " + route.getStationAt(10));
        
        // Try removing non-existent station
        boolean removed2 = route.removeStation("Station Z");
        System.out.println("Station Z removed: " + removed2);
    }
}
