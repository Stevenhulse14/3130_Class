package week6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    static class Pirate {
        String name;
        String role;
        int bounty;

        public Pirate(String name, String role, int bounty) {
            this.name = name;
            this.role = role;
            this.bounty = bounty;
        }
    }
   

    public static void main(String[] args) {
        
        // 1. Create a list of pirates with their names, roles, and bounties
        List <Pirate> strawHat = new ArrayList<>(
            List.of(
                new Pirate("Luffy", "Captain", 150),
                new Pirate("Zoro", "Swordsman", 120),
                new Pirate("Nami", "Navigator", 80),
                new Pirate("Usopp", "Sniper", 50),
                new Pirate("Sanji", "Cook", 90),
                new Pirate("Chopper", "Doctor", 70),
                new Pirate("Robin", "Archaeologist", 110),
                new Pirate("Franky", "Shipwright", 100),
                new Pirate("Brook", "Musician", 60),
                new Pirate("Steven", "Apprentice", 10)
            )
        );
        
        // 2. Use a stream to filter out pirates with a bounty of 100 or more
        // whats a stream? its a way to process collections of data in a functional style
        // what is Predicate? its a functional interface that represents a boolean-valued function of one argument, used for filtering data in streams
        Predicate<Pirate> highBounty = pirate -> pirate.bounty >= 100;
        Predicate<Pirate> lowBounty = pirate -> pirate.bounty < 100;
            // a few things happening here:
            // 1. We declare a variable highBounty of type Predicate<Pirate>,
            // 2. We assign it a lambda expression pirate -> pirate.bounty >= 100, which takes a Pirate object as input and returns true if the pirate's bounty is 100 or more, and false otherwise.
            // 3. We can use this Predicate to filter our list of pirates in a stream operation.    

        List<Pirate> bigShots = strawHat.stream().filter(highBounty).toList();
        List<Pirate> smallFry = strawHat.stream().filter(lowBounty).toList();
        // 3. Print the names and bounties of the filtered pirates
    
        //smallFry.forEach(pirate -> System.out.println(pirate.name + ": " + pirate.bounty));

        // 4. Use an iterator to remove pirates with the role of "Apprentice"

        Iterator<Pirate> iterator = strawHat.iterator();
        while (iterator.hasNext()) {
            Pirate pirate = iterator.next();
            if (pirate.role.equals("Apprentice")) {
                iterator.remove();
            }
        }   
        
        // 5. Print the remaining pirates in the crew
        strawHat.forEach(pirate -> System.out.println(pirate.name + ": " + pirate.bounty));
    }
    
}
