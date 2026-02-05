# Review: Java Generics

## Why Generics?

Generics provide **type safety** and **reusability**. They let you write one class or method that works for many types.

## Generic Class Example

```java
public class Box<T> {
    private T value;

    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
```

Usage:

```java
Box<Integer> b = new Box<>();
b.set(42);
int x = b.get();
```

## Generic Method Example

```java
public static <T> void printArray(T[] arr) {
    for (T item : arr) {
        System.out.println(item);
    }
}
```

## Bounded Type Parameters

```java
public static <T extends Number> double sum(T a, T b) {
    return a.doubleValue() + b.doubleValue();
}
```

## Wildcards

- `?` unknown type
- `? extends T` read-only (upper bound)
- `? super T` write-friendly (lower bound)

Example:

```java
public static void printAll(List<?> list) {
    for (Object o : list) System.out.println(o);
}
```

## Common Pitfalls

- You **cannot** use primitives directly: use wrappers like `Integer`.
- Type info is erased at runtime (type erasure).

## Practice Problems

1. Write a generic class `Pair<T, U>` with `getFirst()` and `getSecond()`.
2. Write a generic method that returns the max of two `Comparable` values.
3. What is the difference between `List<Object>` and `List<?>`?

## Short Answers

1. Two fields with getters.
2. Use `<T extends Comparable<T>>`.
3. `List<Object>` can accept only `Object` types; `List<?>` can accept any list type, but you can’t add items except `null`.
