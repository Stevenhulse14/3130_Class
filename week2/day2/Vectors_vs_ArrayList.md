# Vectors in Java vs ArrayList (with Practice)

## Key Differences

| Feature       | Vector                  | ArrayList              |
| ------------- | ----------------------- | ---------------------- |
| Thread safety | Synchronized            | Not synchronized       |
| Performance   | Slower (due to sync)    | Faster                 |
| Growth        | Doubles size            | Grows by 50% (typical) |
| Legacy        | Older (pre-Collections) | Modern, preferred      |

**Use Vector** only if you need built-in synchronization. Otherwise, prefer **ArrayList**.

## Quick Examples

```java
Vector<String> v = new Vector<>();
v.add("A");

ArrayList<String> a = new ArrayList<>();
a.add("A");
```

## Practice Problems

1. Create an `ArrayList<Integer>` and add 5 integers. Print the list.
2. Convert a `Vector<String>` to an `ArrayList<String>`.
3. Remove all even numbers from an `ArrayList<Integer>` using an iterator.
4. Explain why `Vector` may be slower than `ArrayList`.

## Challenge

Write a method that accepts a `List<String>` and returns the longest string.
