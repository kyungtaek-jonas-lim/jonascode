# ⚖️ **Greedy Algorithm vs Dynamic Programming**

| Feature | **Greedy Algorithm** | **Dynamic Programming (DP)** |
|--------|----------------------|------------------------------|
| **Strategy** | Make the *locally optimal* choice at each step | Explore all subproblems, store results to avoid recomputation |
| **Optimality Guarantee** | Only works when the problem has the **Greedy Choice Property** and **Optimal Substructure** | Always finds the optimal solution if subproblem structure is valid |
| **Subproblem Overlap** | No overlapping subproblems | Has overlapping subproblems |
| **Memoization Required?** | ❌ No | ✅ Yes |
| **Implementation** | Typically simpler, faster | More complex, may use recursion or tabulation |
| **Time Complexity** | Usually better (O(n log n), O(n)) | May be higher (O(n²), O(nW), etc.) |
| **Use Case** | When local optimal decisions guarantee global optimality | When overlapping subproblems and optimal substructure are present |
| **Space Complexity** | Often lower (just counters, flags, or simple accumulators) | Usually higher (due to DP table or cache) |

---

## 🧠 **How to Choose Between Greedy and DP**

| Scenario | Prefer Greedy | Prefer DP |
|----------|---------------|-----------|
| Problem allows a single pass with local decisions | ✅ | ❌ |
| You must explore multiple combinations | ❌ | ✅ |
| The greedy approach can be **proven** to yield the global optimum | ✅ | ❌ |
| Problem involves counting combinations, subsequences, or overlapping states | ❌ | ✅ |
| You're asked for "maximum" or "minimum" but constraints are tight | ✅ (if provable) | ✅ (fallback) |

---

## 📌 **Examples**

| Problem | Greedy Approach | DP Approach |
|--------|------------------|-------------|
| **Coin Change** | ✅ When coin system is canonical (e.g., US coins) | ✅ Always works (general case) |
| **Activity Selection / Meeting Room** | ✅ Sort by end time, pick non-overlapping intervals | ❌ DP not needed |
| **Fibonacci Numbers** | ❌ Doesn’t work | ✅ DP ideal (top-down or bottom-up) |
| **Climbing Stairs** | ❌ No greedy choice property | ✅ Classic DP |
| **Jump Game** (LeetCode 55) | ✅ Greedy works with farthest reachable index | ✅ DP also possible, but slower |
| **Job Scheduling with Deadlines** | ✅ Greedy + sorting or heap | ✅ DP for more complex variants |
| **0/1 Knapsack** | ❌ Can't greedily choose items | ✅ Classic DP problem |
| **Fractional Knapsack** | ✅ Greedy based on value/weight | ❌ DP overkill here |

---

## 📝 Summary (TL;DR)

- **Greedy is fast and simple**, but requires proof of correctness (local → global optimality).
- **DP is slower but safer**, especially when subproblem results can be reused.
- When in doubt:  
  **Try Greedy first** (e.g., sorting or picking max/min),  
  then fall back to **DP** if greedy fails or cannot guarantee correctness.

&nbsp;
&nbsp;

---

## ❌ Greedy Fails: **Non-Canonical Coin Change Problem**

### 🧩 Problem

> Given coin denominations `[1, 3, 4]` and a target amount of `6`,  
> what is the **minimum number of coins** needed to make change?

---

### 🧠 Intuition (Why this is interesting)

Most people instinctively apply a **greedy algorithm**:
- Always take the **largest coin** possible at each step.
- This works with U.S. coins (1, 5, 10, 25), but **fails here**.

---

### 🚫 Greedy Attempt (Fails)

```text
Target = 6
Use 4 → Remaining: 2
Use 1 → Remaining: 1
Use 1 → Remaining: 0
Total coins used: 3
```

But that's not optimal!

---

### ✅ Correct (DP or Exhaustive Search)

```text
Use 3 + 3 → Total coins: 2
```

So the **minimum** number of coins is **2**, not 3.

---

### 📌 Why Greedy Fails Here?

Greedy doesn’t look ahead — it just chooses what seems best *right now*.  
It fails when a **smaller local choice leads to a better global outcome**, which is exactly what DP is designed to handle.

---

## 📘 Summary

- **Problem type**: Optimization
- **Why Greedy fails**: No greedy-choice property — local optimum ≠ global optimum
- **Correct approach**: Dynamic Programming (bottom-up or memoized recursion)

---

### 🧪 Bonus

This problem is frequently used to teach:
- The need to **analyze the problem structure** before choosing an algorithm
- That **Greedy is not always safe**, especially in change-making or path-finding problems