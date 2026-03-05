# Week 6 Walkthrough

Use this walkthrough as a step-by-step guide for the week 6 exercises. Each section includes a small code target, plus checks you can use to self-verify.

## 1) Lambda Warmup

Goal: Recognize a functional interface, replace an anonymous class with a lambda, and use a method reference when it improves clarity.

### Step-by-step

1. Identify the single abstract method in the interface.
2. Keep the same parameter list and return type.
3. Replace the anonymous class body with a lambda.
4. If the lambda only calls one existing method, swap to a method reference.

### Example

Before:

```java
Consumer<String> printer = new Consumer<String>() {
	@Override
	public void accept(String s) {
		System.out.println(s);
	}
};
```

After (lambda):

```java
Consumer<String> printer = s -> System.out.println(s);
```

After (method reference):

```java
Consumer<String> printer = System.out::println;
```

## 2) Iterator Practice

Goal: Traverse a list using an `Iterator` and remove elements safely.

### Step-by-step

1. Create a mutable list (ArrayList) so removals are allowed.
2. Call `iterator()` to get an `Iterator`.
3. Use `hasNext()` and `next()` in a loop.
4. Call `remove()` only after `next()`.

### Example

```java
List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5));
Iterator<Integer> it = nums.iterator();

while (it.hasNext()) {
	int n = it.next();
	if (n % 2 == 0) {
		it.remove();
	}
}

System.out.println(nums); // [1, 3, 5]
```

## 3) Combine Concepts

Goal: Use a lambda to filter, then iterate the result explicitly.

### Step-by-step

1. Create a list.
2. Use a `Predicate` lambda in `stream().filter(...)`.
3. Collect the result into a new list.
4. Iterate that list with an `Iterator`.

### Example

```java
List<String> names = List.of("Luffy", "Nami", "Zoro", "Usopp");
Predicate<String> longName = s -> s.length() >= 5;

List<String> longNames = names.stream()
	.filter(longName)
	.toList();

Iterator<String> it = longNames.iterator();
while (it.hasNext()) {
	System.out.println(it.next());
}
```

## Example Walkthrough Problem 1: Pokemon List Filter

Goal: Use a lambda to filter Pokemon by level, then iterate to print results.

Starter class:

```java
class Pokemon {
	String name;
	int level;

	Pokemon(String name, int level) {
		this.name = name;
		this.level = level;
	}
}
```

Steps:

1. Create a `List<Pokemon>` with at least 5 entries.
2. Use a `Predicate<Pokemon>` lambda to keep only level 10 or higher.
3. Build a new list using `stream().filter(...)`.
4. Use an `Iterator<Pokemon>` to print each remaining Pokemon name and level.

Code target:

```java
List<Pokemon> team = new ArrayList<>(List.of(
	new Pokemon("Pikachu", 12),
	new Pokemon("Charmander", 8),
	new Pokemon("Bulbasaur", 10),
	new Pokemon("Squirtle", 7),
	new Pokemon("Eevee", 15)
));

Predicate<Pokemon> highLevel = p -> p.level >= 10;
List<Pokemon> strong = team.stream()
	.filter(highLevel)
	.toList();

Iterator<Pokemon> it = strong.iterator();
while (it.hasNext()) {
	Pokemon p = it.next();
	System.out.println(p.name + " (" + p.level + ")");
}
```

Checkpoints:

- The predicate should be one line.
- No direct indexing; use the iterator.

## Example Walkthrough Problem 2: One Piece Pirate Crew Cleanup

Goal: Use an iterator to remove crew members by role, then print the crew.

Steps:

1. Create a `List<String>` of Straw Hat crew roles (Captain, Navigator, etc.).
2. Use an `Iterator<String>` to remove any role that contains "Cook".
3. Use `forEach` with a lambda to print the remaining roles.

Code target:

```java
List<String> roles = new ArrayList<>(List.of(
	"Captain",
	"Navigator",
	"Cook",
	"Swordsman",
	"Doctor",
	"Cook Assistant"
));

Iterator<String> it = roles.iterator();
while (it.hasNext()) {
	String role = it.next();
	if (role.contains("Cook")) {
		it.remove();
	}
}

roles.forEach(r -> System.out.println(r));
```

Checkpoints:

- Removal must be done via `iterator.remove()`.
- No `ConcurrentModificationException` should occur.

## Deliverables

Submit:

- Updated source files for the exercises.
- Short written notes answering the questions in each section.

## Checklist / Rubric (10 points)

- 2 pts: Correct lambda conversion and method reference usage.
- 3 pts: Iterator traversal uses `hasNext()` and `next()` correctly.
- 2 pts: Safe removal using `iterator.remove()` with no exceptions.
- 2 pts: Pokemon filter uses a `Predicate` and a stream.
- 1 pt: Clear notes that explain stream vs iterator choice.
