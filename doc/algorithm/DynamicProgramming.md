# Dynamic Programming (DP)

## 📘 What is Dynamic Programming?

**Definition:**
> **Dynamic Programming (DP)** is an optimization technique used to solve complex problems by breaking them down into simpler subproblems and storing the results of these subproblems to avoid redundant calculations.

---

### 🔑 Two Core Principles of DP:

1. **Optimal Substructure**  
   - A problem has an optimal substructure if its optimal solution can be constructed from optimal solutions of its subproblems.

2. **Overlapping Subproblems**  
   - The problem can be broken down into subproblems which are reused several times.

---

### 🧠 Example: Fibonacci Numbers

A naive recursive solution has exponential time complexity because it recomputes the same values repeatedly.  
With DP, we can store already computed values, achieving linear time.

```python
# Bottom-up DP for Fibonacci
def fib(n):
    dp = [0, 1] + [0] * (n - 1)
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]
```

---

## 💼 Dynamic Programming for Coding Interviews

Here’s a categorized list of common DP problems you’ll likely encounter in coding tests, along with approaches to solve each type.

---

### 1. **Basic 1D DP**
> **Examples**: Fibonacci, Climbing Stairs, Number of Ways to Sum

- **Characteristics**: Only previous values are needed.
- **Strategy**:
  - Define a `dp[i]` that stores the result for the i-th state.
  - Use either bottom-up (iteration) or top-down (recursion + memoization).

```python
# Climbing stairs (1 or 2 steps at a time)
dp[0] = 1
dp[1] = 1
for i in range(2, n+1):
    dp[i] = dp[i-1] + dp[i-2]
```

---

### 2. **2D DP – Subsequence/Subarray Problems**
> **Examples**: Longest Common Subsequence (LCS), Longest Increasing Subsequence (LIS)

- **Characteristics**: Often compares two sequences or tracks subsequences.
- **Strategy**:
  - Use a 2D table `dp[i][j]` representing a subproblem up to i and j.
  - Build the solution based on character/element comparison.

```python
# LCS example
if s1[i-1] == s2[j-1]:
    dp[i][j] = dp[i-1][j-1] + 1
else:
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])
```

---

### 3. **Knapsack Problems**
> **Examples**: 0-1 Knapsack, Subset Sum

- **Characteristics**: Limited resources (like weight, time) and maximization/minimization.
- **Strategy**:
  - `dp[i][w] = max value using first i items with total weight w`
  - Update value by considering whether to include current item.

```python
if weight[i] <= w:
    dp[i][w] = max(dp[i-1][w], dp[i-1][w - weight[i]] + value[i])
else:
    dp[i][w] = dp[i-1][w]
```

---

### 4. **Combinatorics / Coin Change**
> **Examples**: Ways to make change, Count combinations to reach sum

- **Characteristics**: Count the number of ways to form a value.
- **Strategy**:
  - `dp[i] = number of ways to make amount i`
  - Iterate over coins, avoid duplicate combinations if order doesn’t matter.

```python
for coin in coins:
    for i in range(coin, target+1):
        dp[i] += dp[i - coin]
```

---

### 5. **Pathfinding / Grid Problems**
> **Examples**: Max/Min path in a grid (right or down only), Grid-based games

- **Characteristics**: Grid movement with constraints.
- **Strategy**:
  - `dp[y][x] = best score/path to reach cell (y, x)`
  - Use direction constraints to limit traversal (e.g., top and left only).

```python
dp[y][x] = max(dp[y-1][x], dp[y][x-1]) + grid[y][x]
```

---

### 6. **Interval/Range DP**
> **Examples**: Palindromic substrings, Matrix Chain Multiplication, String partitioning

- **Characteristics**: Problems based on subintervals of an array or string.
- **Strategy**:
  - `dp[i][j] = result for the interval from i to j`
  - Expand from small intervals to larger ones

```python
for length in range(2, n+1):
    for i in range(n - length + 1):
        j = i + length - 1
        dp[i][j] = min(dp[i][k] + dp[k+1][j] + cost(i, j)) for k in range(i, j)
```

---

## 🎯 Interview Tips for DP

- **Clearly define the meaning of your dp[i] or dp[i][j]**
- Start with brute-force, then look for overlapping subproblems.
- Use **Top-down** (easier to write) or **Bottom-up** (better performance, avoids stack overflow).
- Watch your **time complexity**. Some DP solutions run in O(N), others in O(N²), O(NW), etc.