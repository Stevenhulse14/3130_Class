# Day 2 — Iterators & Generics (Group Tasks)

## 1️⃣ Remove Failing Students (Real-World Scenario)

**You are given:**

```java
ArrayList<Double> grades = new ArrayList<>();
```

**Task Setup**

- Add at least 8 grade values.

**Group Task**

- Use an `Iterator` to remove all grades below 65.
- Explain why using a regular for-each loop would cause problems.
- Why does `ArrayList<Double>` require generics?
- What would happen if you declared `ArrayList grades` instead?

**Discussion Extension**

- What is happening internally when an element is removed from an `ArrayList`?

---

## 2️⃣ Generic Inventory System

**Create a generic class:**

```java
public class Box<T>
```

**Now create:**

```java
ArrayList<Box<String>> names;
ArrayList<Box<Integer>> ids;
```

**Group Task**

- Add 5 objects to each list.
- Use an `Iterator` to print all values.
- Explain why `Box<T>` is reusable but without generics it would not be.
- Why does the `Iterator` not need casting?

**Deep Thinking**

- How are Generics and `ArrayList` related internally?

---

## 3️⃣ Remove Duplicates from a Generic List

**Create:**

```java
ArrayList<String> pokemon = new ArrayList<>();
```

**Task Setup**

- Add duplicate values manually.

**Group Task**

- Use an `Iterator` to remove duplicates.
- Explain why removing elements inside a regular for loop is dangerous.
- Why does `ArrayList<String>` prevent mixing types?
- If this were `ArrayList<Object>`, what would change?

**Discussion**

- What does type safety actually mean?

---

## 4️⃣ Find and Replace Using Iterator

**Create:**

```java
ArrayList<Integer> numbers = new ArrayList<>();
```

**Task Setup**

- Add 10 numbers.

**Group Task**

- Use an `Iterator` to remove all even numbers.
- Then add the value `0` to the list.
- Why must removal happen through the `Iterator`?
- How do generics prevent runtime errors here?

**Critical Thinking**

- What would happen if you removed items using `numbers.remove()` inside the loop?

---

## 5️⃣ Design a Generic Student List Processor

**Create a generic method:**

```java
public static <T> void processList(ArrayList<T> list)
```

**Group Task**

- Inside the method, use an `Iterator<T>`.
- Print all elements.
- Explain how the same method works for:
  - `ArrayList<String>`
  - `ArrayList<Integer>`
  - `ArrayList<Double>`

**Discussion Questions**

- Why does the method not need to know the exact type?
- What would break if `<T>` was removed?
- Why are generics important for large systems?
