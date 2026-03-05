# Week 6

This week focuses on lambda expressions and iterator basics in Java.

## Lambda Basics

Lambda expressions let you pass behavior as data, most often as a single-method interface (a functional interface).

### Syntax

- `(params) -> expression`
- `(params) -> { statements; }`

### Common Functional Interfaces

- `Runnable` -> `() -> { ... }`
- `Consumer<T>` -> `(t) -> { ... }`
- `Supplier<T>` -> `() -> t`
- `Function<T, R>` -> `(t) -> r`
- `Predicate<T>` -> `(t) -> true/false`

### Examples

```java
Runnable r = () -> System.out.println("Hello");

List<String> names = List.of("Luffy", "Nami", "Zoro");
names.forEach(n -> System.out.println(n));

Predicate<String> isLong = s -> s.length() > 4;
List<String> longNames = names.stream()
	.filter(isLong)
	.toList();
```

### Notes

- Lambdas can capture effectively final variables from the outer scope.
- Prefer method references when they improve readability, e.g. `System.out::println`.

## Iterator Basics

Iterators provide a consistent way to traverse collections without exposing internal structure.

### Core Methods

- `hasNext()` checks for remaining elements.
- `next()` returns the next element.
- `remove()` removes the last element returned by `next()` (optional, collection dependent).

### Example

```java
List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
Iterator<Integer> it = nums.iterator();

while (it.hasNext()) {
	Integer n = it.next();
	if (n % 2 == 0) {
		it.remove();
	}
}
```

### Notes

- Use the iterator `remove()` method to avoid `ConcurrentModificationException`.
- Enhanced for-loops use iterators under the hood but do not allow safe removal.

## Next Steps

- Practice converting loops to iterators.
- Practice translating anonymous classes into lambdas.
