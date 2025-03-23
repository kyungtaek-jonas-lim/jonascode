# ⚡ Greedy Algorithm

## 📘 What is a Greedy Algorithm?

**Definition:**
> A **Greedy Algorithm** is a problem-solving technique that builds up a solution piece by piece, always choosing the option that offers the most immediate benefit (locally optimal), hoping that this approach leads to a globally optimal solution.

---

## 🔑 Core Principle of Greedy:

1. **Greedy Choice Property**  
   - A globally optimal solution can be arrived at by choosing the best local option at each step.
2. **No Overlapping Subproblems**  
   - Unlike DP, greedy algorithms don't revisit subproblems; they make decisions once and move on.

---

## 🧠 Example: Coin Change (Greedy)

```python
def greedy_coin_change(coins, amount):
    coins.sort(reverse=True)  # Start with the largest coin
    count = 0
    for coin in coins:
        while amount >= coin:
            amount -= coin
            count += 1
    return count if amount == 0 else -1  # -1 if change cannot be made
```

> ⚠️ This greedy solution works only when coin denominations are canonical (e.g., US coins). Otherwise, a DP approach is needed.

---

# 💼 Greedy Algorithms for Coding Interviews

Here are some of the most common greedy problems and patterns you might face in coding interviews.

---

## 1. **Activity Selection / Interval Scheduling**
> **Examples**: Meeting Rooms, Maximum Number of Non-overlapping Intervals

- **Strategy**:
  - Sort by end time.
  - Always pick the earliest finishing activity that doesn't overlap with the previous one.

```python
intervals.sort(key=lambda x: x[1])
count = 0
end = 0
for start, finish in intervals:
    if start >= end:
        count += 1
        end = finish
```

---

## 2. **Minimum Number of Coins / Cashier Problem**
> **Examples**: Greedy Coin Change (when denominations allow)

- **Strategy**:
  - Use the largest coin possible until the amount is fulfilled.
  - Fails if coin system isn’t greedy-optimal (e.g., [1, 3, 4] for amount 6).

---

## 3. **Huffman Encoding / Priority-Based Greedy**
> **Examples**: Huffman Coding Tree (uses a min-heap)

- **Strategy**:
  - Greedily combine the two smallest frequency nodes to build an optimal prefix code.

---

## 4. **Greedy with Sorting**
> **Examples**: Job Scheduling, Assigning Tasks, Gas Station Problem

- **Strategy**:
  - Often sort items by weight/value ratio, deadlines, or end times.

---

## 5. **Greedy + Two Pointers**
> **Examples**: Assign Cookies, Jump Game

- **Strategy**:
  - Use pointers and greedy conditions to match or maximize/minimize results efficiently.

---

## 🎯 Interview Tips for Greedy

- **Prove correctness**: Greedy doesn't always work; **ensure it leads to global optimum**.
- Look for:
  - **Greedy-choice property**
  - **Optimal substructure**
- Always ask: _“Does a local optimal choice always lead to a globally optimal solution?”_
- Compare your greedy approach to a DP brute-force method to test validity.