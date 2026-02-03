# Java Generics

## What Are Generics?

Generics allow you to write code that works with different data types while maintaining type safety. They enable you to create classes, interfaces, and methods that can work with any data type while catching type errors at compile time rather than runtime.

## Basic Syntax

### Generic Classes

```java
public class Box<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

**Usage:**

```java
Box<String> stringBox = new Box<>();
stringBox.setValue("Hello");
String result = stringBox.getValue();

Box<Integer> intBox = new Box<>();
intBox.setValue(42);
Integer num = intBox.getValue();
```

### Generic Methods

```java
public class GenericMethods {
    // Single type parameter
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }

    // Multiple type parameters
    public static <K, V> void printMap(K key, V value) {
        System.out.println("Key: " + key + ", Value: " + value);
    }
}
```

## Bounded Type Parameters

Restrict the types that can be used with generics:

```java
// Upper bound - T must be a Number or subclass
public class NumberBox<T extends Number> {
    private T value;

    public double getDouble() {
        return value.doubleValue();
    }
}
```

**Usage:**

```java
NumberBox<Integer> intBox = new NumberBox<>();
NumberBox<Double> doubleBox = new NumberBox<>();
// NumberBox<String> stringBox = new NumberBox<>();  // ERROR: String is not a Number
```

## Wildcards

### Unbounded Wildcard

```java
public void printList(List<?> list) {
    for (Object obj : list) {
        System.out.println(obj);
    }
}
```

### Upper Bounded Wildcard

```java
public double sumNumbers(List<? extends Number> list) {
    double sum = 0;
    for (Number num : list) {
        sum += num.doubleValue();
    }
    return sum;
}
```

### Lower Bounded Wildcard

```java
public void addNumbers(List<? super Integer> list) {
    list.add(5);
    list.add(10);
}
```

## Common Generic Collections

```java
// List
List<String> names = new ArrayList<>();
names.add("Alice");
names.add("Bob");

// Map
Map<String, Integer> ages = new HashMap<>();
ages.put("Alice", 25);
ages.put("Bob", 30);

// Set
Set<Integer> numbers = new HashSet<>();
numbers.add(1);
numbers.add(2);
```

## Benefits of Generics

1. **Type Safety** - Catch errors at compile time, not runtime
2. **Code Reusability** - Write once, use with multiple types
3. **Elimination of Casting** - No need to cast when retrieving values
4. **Better Readability** - Intent is clear from the code

## Type Erasure

Java uses **type erasure** - generic type information is removed at compile time:

```java
List<String> stringList = new ArrayList<>();
List<Integer> intList = new ArrayList<>();

// At runtime, both are just List
System.out.println(stringList.getClass() == intList.getClass()); // true
```

## Best Practices

1. Use descriptive type variable names (T for Type, K for Key, V for Value)
2. Keep generics simple and understandable
3. Use bounded wildcards when you need more control
4. Avoid mixing raw types and generics in the same code
5. Use `<?>` when you don't care about the type

## Example: Generic Pair Class

```java
public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}

// Usage
Pair<String, Integer> pair = new Pair<>("Age", 25);
System.out.println(pair.getFirst() + ": " + pair.getSecond());
```

## Common Pitfalls

1. **Cannot instantiate generic types**: `new T()` is not allowed
2. **Cannot create generic arrays**: `T[] array = new T[10]` is not allowed
3. **Generic type info is erased at runtime** - Cannot use `instanceof` with generics
4. **Cannot use primitives**: `Box<int>` is not allowed; use `Box<Integer>` instead
