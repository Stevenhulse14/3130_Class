# Vectors Lab — One Piece: Crew Management

Welcome to a fun lab where you'll practice Java `Vector` operations using a One Piece theme: manage the Straw Hat crew, bounties, and treasure chests while learning vector APIs and behaviors.

````markdown
# Vectors Lab — One Piece: Crew Management

Welcome to a fun lab where you'll practice Java `Vector` operations using a One Piece theme: manage the Straw Hat crew, bounties, and treasure chests while learning vector APIs and behaviors.

## Learning Objectives

- Understand basic `Vector` operations: creation, add, insert, remove, access, size, capacity.
- Iterate through a `Vector` using `Enumeration`, for-loop, and enhanced for-loop.
- Compare `Vector` with `ArrayList` (thread-safety, synchronization, legacy API).
- Use `Collections` utilities with `Vector` (sorting, searching).
- Observe concurrent behavior differences between `Vector` and `ArrayList` (bonus).

## Prerequisites

- Java basics: classes, lists, loops
- Know how to compile and run Java programs (`javac` / `java`)

## This is a Practice Lab (No Grade)

This lab is for practice only — no grade will be assigned. Work through the exercises carefully, follow the step-by-step instructions below, and test your code locally. Share questions or your completed examples with the instructor if you want feedback.

## Quick commands

Compile one file:

```bash
javac CrewDemo.java
java CrewDemo
```

Compile multiple files (example for `Pirate` + demo):

```bash
javac Pirate.java BountyBoardDemo.java
java BountyBoardDemo
```

If using an IDE, create the classes and run them as usual.

## Exercise 1 — Warm-up: Straw Hat Crew Vector (detailed)

1. Create a file named `CrewDemo.java`.
2. Copy the sample code below into `CrewDemo.java`.
3. Compile and run using the quick commands above.

Step-by-step checklist:

- Create `CrewDemo.java` in your working directory.
- Implement a `main` that:
  1. Creates `Vector<String> crew = new Vector<>();`
  2. Adds the following in order: `Luffy, Zoro, Nami, Usopp, Sanji, Chopper, Robin, Franky, Brook, Jinbe`.
  3. Prints `Crew size: X` then prints each name on its own line.
- Compile: `javac CrewDemo.java` then run `java CrewDemo`.
- Expected: the console prints `Crew size: 10` followed by the 10 names.

CrewDemo sample:

```java
import java.util.Vector;

public class CrewDemo {
    public static void main(String[] args) {
        Vector<String> crew = new Vector<>();
        crew.add("Luffy");
        crew.add("Zoro");
        crew.add("Nami");
        crew.add("Usopp");
        crew.add("Sanji");
        crew.add("Chopper");
        crew.add("Robin");
        crew.add("Franky");
        crew.add("Brook");
        crew.add("Jinbe");

        System.out.println("Crew size: " + crew.size());
        for (String name : crew) System.out.println(name);
    }
}
```

## Exercise 2 — Bountyboard: Vector of Objects (detailed)

Files to create:

- `Pirate.java` — model class
- `BountyBoardDemo.java` — demo that uses `Vector<Pirate>`

Step-by-step:

1. Create `Pirate.java` with public fields `name` and `bounty` and an override `toString()` that returns `name + " — " + bounty`.
2. Create `BountyBoardDemo.java`. In `main`:
   - Create `Vector<Pirate> bountyBoard = new Vector<>();`
   - Add several `Pirate` instances (e.g., "Blackbeard" 2000000, "Zoro" 320000, "Luffy" 1500000).
   - Sort the vector by bounty descending using `Collections.sort` and a comparator.
   - Print the leaderboard with positions.
3. Compile both files and run `BountyBoardDemo`.

Compile & run example:

```bash
javac Pirate.java BountyBoardDemo.java
java BountyBoardDemo
```

Hints:

- Use `Collections.sort(bountyBoard, Comparator.comparingInt(p -> p.bounty).reversed());`
- Or implement `Comparator<Pirate>` as a lambda.

Expected behavior: the printed list shows pirates sorted highest bounty first.

## Exercise 3 — Treasure Chest: Insert / Remove / Replace (detailed)

Create `TreasureChestDemo.java` and follow these steps in `main`:

1. Create `Vector<String> treasureChest` and add: "Devil Fruit", "Poneglyph Fragment", "Berries", "Gold".
2. Insert a new item at index 1: `treasureChest.add(1, "Map Fragment");`
3. Remove the last item and print what was removed: `String removed = treasureChest.remove(treasureChest.size() - 1); System.out.println("Removed: " + removed);`
4. Replace item at index 2 with `treasureChest.set(2, "Ancient Weapon Map");`
5. Print final contents and call `System.out.println("Capacity: " + treasureChest.capacity());`

Discussion note: `ensureCapacity(20)` pre-allocates space to reduce reallocation; `trimToSize()` shrinks the backing array to the current size. Use `ensureCapacity` when you know you'll add many items, and `trimToSize` before long-term storage to reduce memory.

## Exercise 4 — Iteration Styles & Legacy APIs (detailed)

Tasks:

1. In `CrewDemo.java` (or a new `IterationDemo.java`), iterate the `crew` `Vector` three ways:
   - Using `Enumeration`:

```java
Enumeration<String> e = crew.elements();
while (e.hasMoreElements()) System.out.println(e.nextElement());
```

- Using a standard `for` loop with index.
- Using an enhanced `for` loop.

2. For each style, print the method used (e.g., "Enumeration:") then the items so it's clear in output which loop produced which results.

Why: compare readability and the legacy status of `Enumeration` versus `Iterator`/enhanced for.

## Exercise 5 (Challenge) — Thread-Safety Showdown (detailed)

This is an optional exercise for students curious about concurrency.

1. Create `ConcurrentDemo.java`.
2. Implement a shared `Vector<String> shared = new Vector<>();`
3. Create two threads:
   - Producer thread: loops 1000 times and calls `shared.add("Crew-" + i);` with a short sleep.
   - Consumer thread: loops 1000 times, occasionally removes `shared.remove(0)` (guard for size > 0) or reads `shared.get(0)`.
4. Run and observe that `Vector` avoids `ConcurrentModificationException` due to internal synchronization, but you may still see logical bugs (race conditions) without higher-level coordination.
5. Repeat using `ArrayList<String>` and one of:
   - Wrap with `Collections.synchronizedList(new ArrayList<>());` or
   - Use `CopyOnWriteArrayList<String>`.

Notes to observe and discuss:

- `Vector` methods are synchronized, but compound actions (check-then-act) still need external synchronization.
- `ArrayList` is not synchronized by default.
- `CopyOnWriteArrayList` is useful for many-read few-write workloads.

## Bonus: Pirate Drafting Game (Small Project)

Create an interactive console application that:

- Uses a `Vector<Pirate>` as the draft pool.
- Accepts console commands: `add <name> <bounty>`, `remove <name>`, `promote <name>`, `shuffle`, `list`, `exit`.

Stretch: persist the vector to a `.txt` file and reload on start.

## Instructor Notes

- This is a practice lab: no grading required. Use the exercises to demonstrate `Vector` behavior and encourage experimentation.
- Emphasize differences between `Vector` and `ArrayList` and modern alternatives. Point out when synchronization is needed for compound actions.
- If students want feedback, they may submit code snippets or screenshots; no formal grading.

---

Have fun — feel free to rename pirates, add more items, and pirate-ify your outputs with ASCII art!
````
