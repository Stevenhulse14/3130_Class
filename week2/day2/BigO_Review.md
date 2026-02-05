# Review: Big-O

## What Big-O Describes

Big-O gives an upper bound on how runtime or memory grows as input size $n$ increases. We focus on dominant terms and ignore constants.

## Common Growth Rates (fast → slow)

- $O(1)$: constant
- $O(\log n)$: logarithmic
- $O(n)$: linear
- $O(n \log n)$: linearithmic
- $O(n^2)$: quadratic
- $O(2^n)$: exponential
- $O(n!)$: factorial

## Rules of Thumb

- Loops in sequence: add costs
- Nested loops: multiply costs
- Drop constants: $O(2n) \to O(n)$
- Drop lower terms: $O(n^2 + n) \to O(n^2)$

## Example Snippets

### Constant

```java
int x = arr[0]; // O(1)
```

### Linear

```java
for (int i = 0; i < n; i++) {
    sum += arr[i];
} // O(n)
```

### Quadratic (nested)

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        count++;
    }
} // O(n^2)
```

## Quick Practice

1. What is the Big-O of two separate loops that each run $n$ times?
2. What is the Big-O of a loop that halves $n$ each time?
3. What is the Big-O of a nested loop where inner runs $i$ times for each $i$ from $1$ to $n$?

## Answers

1. $O(n)$
2. $O(\log n)$
3. $O(n^2)$ (specifically $\frac{n(n+1)}{2}$)
