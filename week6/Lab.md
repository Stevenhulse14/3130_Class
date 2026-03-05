# Week 6 Lab: Pirate Crew Extension

This lab builds on the One Piece pirate crew example and adds a small extension for practice.

## Setup

You are given a list of pirate crew members with names and roles.

Create a `Pirate` class with:

- `name` (String)
- `role` (String)
- `bounty` (int)

Starter class:

```java
class Pirate {
	String name;
	String role;
	int bounty;

	Pirate(String name, String role, int bounty) {
		this.name = name;
		this.role = role;
		this.bounty = bounty;
	}
}
```

Starter list:

```java
List<Pirate> crew = new ArrayList<>(List.of(
	new Pirate("Luffy", "Captain", 300),
	new Pirate("Zoro", "Swordsman", 120),
	new Pirate("Nami", "Navigator", 66),
	new Pirate("Sanji", "Cook", 130),
	new Pirate("Chopper", "Doctor", 50),
	new Pirate("Apprentice A", "Apprentice", 10)
));
```

## Part A: Lambda Filter

Goal: Filter pirates using a lambda and a stream.

Steps:

1. Build a `List<Pirate>` with at least 6 crew members.
2. Write a `Predicate<Pirate>` lambda that keeps pirates with bounty >= 100.
3. Use `stream().filter(...)` to make a new list named `bigShots`.

Code target:

```java
Predicate<Pirate> highBounty = p -> p.bounty >= 100;

List<Pirate> bigShots = crew.stream()
	.filter(highBounty)
	.toList();
```

## Part B: Iterator Removal

Goal: Remove items safely while iterating.

Steps:

1. Use an `Iterator<Pirate>` on the original list.
2. Remove any pirate whose role contains "Apprentice".
3. Print the final list using `forEach` and a lambda.

Code target:

```java
Iterator<Pirate> it = crew.iterator();
while (it.hasNext()) {
	Pirate p = it.next();
	if (p.role.contains("Apprentice")) {
		it.remove();
	}
}

crew.forEach(p -> System.out.println(p.name + " - " + p.role));
```

## Part C: Small Extension

Do as many as you can extension and implement it:

- Option 1: Sort `bigShots` by bounty (highest first).
- Option 2: Group pirates by role and print each group.
- Option 3: Add a method reference for printing names only.
- Option 4: Create a new list of just the pirate names using `map` and `toList()`.
- Option 5: Use `reduce` to find the total bounty of all pirates.
- Option 6: Use `anyMatch` to check if any pirate has a bounty over 200.

Pick one and add a short comment explaining your choice.

## Deliverables

- Your `Pirate` class.
- A `main` method that runs Parts A, B, and C.
- Short notes explaining which extension you chose.

## Checklist / Rubric (10 points)

- 2 pts: `Pirate` class fields and constructor are correct.
- 3 pts: Lambda filter builds `bigShots` correctly.
- 2 pts: Iterator removal uses `remove()` safely.
- 2 pts: Extension (Part C) works as described.
- 1 pt: Notes explain the extension choice.
