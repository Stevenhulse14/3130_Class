package week6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {

    static class Pirate {
        String name;
        String role;
        int bounty;

        Pirate(String name, String role, int bounty) {
            this.name = name;
            this.role = role;
            this.bounty = bounty;
        }
    }

    public static void main(String[] args) {
        // Starter crew list for the lab tasks.
        List<Pirate> crew = new ArrayList<>(List.of(
            new Pirate("Luffy", "Captain", 300),
            new Pirate("Zoro", "Swordsman", 120),
            new Pirate("Nami", "Navigator", 66),
            new Pirate("Sanji", "Cook", 130),
            new Pirate("Chopper", "Doctor", 50),
            new Pirate("Robin", "Archaeologist", 110),
            new Pirate("Franky", "Shipwright", 100),
            new Pirate("Brook", "Musician", 60),
            new Pirate("Apprentice A", "Apprentice", 10)
        ));

        // Part A: filter using a Predicate and stream.
        Predicate<Pirate> highBounty = p -> p.bounty >= 100;
        List<Pirate> bigShots = crew.stream()
            .filter(highBounty)
            .toList();

        System.out.println("Big shots (bounty >= 100):");
        bigShots.forEach(p -> System.out.println(p.name + " - " + p.bounty));

        // Part B: remove during iteration using iterator.remove().
        Iterator<Pirate> it = crew.iterator();
        while (it.hasNext()) {
            Pirate p = it.next();
            if (p.role.contains("Apprentice")) {
                it.remove();
            }
        }

        System.out.println("\nCrew after removing apprentices:");
        crew.forEach(p -> System.out.println(p.name + " - " + p.role));

        // Extension option 1: sort by bounty (descending).
        // I chose this extension because it allows us to see the most valuable pirates 
        // at the top of the list, which is often how bounties are presented in pirate lore.
        // Comparator means we can define a custom sorting order. 
        // We use comparingInt to compare the bounty field, and reversed() to sort in
        // descending order.
        List<Pirate> sortedByBounty = bigShots.stream()
            .sorted(Comparator.comparingInt((Pirate p) -> p.bounty).reversed())
            .toList();

        System.out.println("\nBig shots sorted by bounty:");
        sortedByBounty.forEach(p -> System.out.println(p.name + " - " + p.bounty));

        // Extension option 2: group by role.
        // Mapping pirates to their roles allows us 
        // to see how many pirates we have in each role 
        // and who they are. This is useful for understanding the composition of the crew.
        Map<String, List<Pirate>> byRole = crew.stream()
            .collect(Collectors.groupingBy(p -> p.role));

        System.out.println("\nCrew grouped by role:");
        byRole.forEach((role, pirates) -> {
            System.out.println(role + ":");
            pirates.forEach(p -> System.out.println("  " + p.name));
        });

        // Extension option 3: method reference for printing names.
        // Using a method reference makes the code more concise 
        // and readable when we just want to perform a simple action like printing.
        // .map(p -> p.name) transforms the stream of Pirate
        //  objects into a stream of their names (Strings), and 
        // then forEach(System.out::println) prints each name on a new line.
        System.out.println("\nNames only (method reference):");
        crew.stream().map(p -> p.name).forEach(System.out::println);

        // Extension option 4: map to a list of names.
        List<String> names = crew.stream()
            .map(p -> p.name)
            .toList();

        System.out.println("\nNames list: " + names);

        // Extension option 5: reduce to get total bounty.
        // reduce method allows us to combine all the bounty values into a single total.
        //  We start with 0 as the identity value, and Integer::sum 
        // is a method reference that adds two integers together.
        int totalBounty = crew.stream()
            .map(p -> p.bounty)
            .reduce(0, Integer::sum);

        System.out.println("\nTotal bounty: " + totalBounty);

        // Extension option 6: anyMatch check.
        // anyMatch is a convenient way to check if any element 
        // in the stream meets a certain condition without needing 
        // to loop through the entire list manually.
        boolean anyOver200 = crew.stream().anyMatch(p -> p.bounty > 200);
        System.out.println("Any bounty over 200? " + anyOver200);
    }
}
