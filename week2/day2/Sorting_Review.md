# Review: Sorting Algorithms

This review covers **bubble**, **insertion**, and **selection** sort. Focus on how they move elements and their time complexity.

## Bubble Sort

**Idea:** Repeatedly swap adjacent out-of-order elements; largest "bubbles" to the end.

**Steps (short):**

1. Compare adjacent pairs.
2. Swap if needed.
3. Repeat passes until no swaps.

**Time:**

- Best: $O(n)$ (already sorted with swap check)
- Average/Worst: $O(n^2)$

**Stable:** Yes

### Trace Example

Array: `[5, 1, 4, 2]`

- Pass 1: `[1, 4, 2, 5]`
- Pass 2: `[1, 2, 4, 5]`

## Insertion Sort

**Idea:** Build a sorted left portion by inserting each new element into its correct position.

**Steps (short):**

1. Start from index 1.
2. Compare current with left side.
3. Shift larger items right; insert.

**Time:**

- Best: $O(n)$ (already sorted)
- Average/Worst: $O(n^2)$

**Stable:** Yes

### Trace Example

Array: `[5, 1, 4, 2]`

- Insert 1 into `[5]` → `[1, 5, 4, 2]`
- Insert 4 into `[1, 5]` → `[1, 4, 5, 2]`
- Insert 2 into `[1, 4, 5]` → `[1, 2, 4, 5]`

## Selection Sort

**Idea:** Repeatedly select the smallest element from the unsorted part and swap into place.

**Steps (short):**

1. Find smallest in unsorted section.
2. Swap with first unsorted position.

**Time:**

- Best/Average/Worst: $O(n^2)$

**Stable:** No (typical implementation)

### Trace Example

Array: `[5, 1, 4, 2]`

- Select 1 → swap with 5 → `[1, 5, 4, 2]`
- Select 2 → swap with 5 → `[1, 2, 4, 5]`

## Compare at a Glance

| Algorithm | Best     | Average  | Worst    | Stable | In-Place |
| --------- | -------- | -------- | -------- | ------ | -------- |
| Bubble    | $O(n)$   | $O(n^2)$ | $O(n^2)$ | Yes    | Yes      |
| Insertion | $O(n)$   | $O(n^2)$ | $O(n^2)$ | Yes    | Yes      |
| Selection | $O(n^2)$ | $O(n^2)$ | $O(n^2)$ | No     | Yes      |

## Practice: Understand the Process

1. Trace each algorithm on `[3, 1, 2]` and write each pass.
2. For each algorithm, identify when the array becomes fully sorted.
3. Which algorithm does the **fewest swaps** on `[1, 2, 3, 4]`?
4. Which algorithm does the **fewest comparisons** on `[4, 3, 2, 1]`?

## Short Answers

1. Varies by algorithm; write out passes.
2. Bubble: after pass 1 (if optimized). Insertion: after final insertion. Selection: after final selection.
3. Insertion (0 swaps if implemented with shifts), Bubble (0 swaps if optimized), Selection (always swaps).
4. Selection does the same number of comparisons regardless of order.
