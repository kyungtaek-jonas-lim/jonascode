# Question #1
## ❓Your Question:
You're asking:

> "If my goal is to reach a specific `amount`, why are we looping from `coin` to `amount`, incrementing by 1? Shouldn’t we directly calculate `dp[amount]`?"

Great question — this goes to the **heart of Dynamic Programming (DP)**.

---

## 🎯 Objective
You want to find the **minimum number of coins** needed to make up a certain `amount`.

But here's the key idea:
> To calculate `dp[amount]`, you **must know the optimal number of coins for all smaller amounts**, like `dp[amount - 1]`, `dp[amount - 2]`, etc.

---

## 🧠 Core DP Principle

In DP, you **build the solution to a big problem from solutions to smaller subproblems**.

So to compute the best way to make `amount = 5`, you may need:
- the best way to make 4 (and add a 1-coin),
- or the best way to make 3 (and add a 2-coin), etc.

This is why we fill up `dp[i]` values from low to high — so that when we get to `dp[5]`, we already know `dp[4]`, `dp[3]`, etc.

---

## 🔁 Why this loop?

```python
for coin in coins:
    for i in range(coin, amount + 1):
        dp[i] = min(dp[i], dp[i - coin] + 1)
```

### Explanation:
- We loop from `coin` to `amount` because:
  - For any amount `i`, we can **only make it using coin `c` if `i >= c`**
  - For example, we can't use a 2-coin to make amount 1.

---

## 📌 Example: `coins = [1, 2]`, `amount = 5`

Let’s walk through what happens with `dp`:

### Initial state:
```python
dp = [0, inf, inf, inf, inf, inf]  # dp[0] = 0, everything else = unreachable
```

### Step 1: Use coin = 1

We loop from 1 to 5:

| i | dp[i - 1] + 1 | dp[i] updated |
|--|----------------|---------------|
| 1 | dp[0] + 1 = 1 | dp[1] = 1     |
| 2 | dp[1] + 1 = 2 | dp[2] = 2     |
| 3 | dp[2] + 1 = 3 | dp[3] = 3     |
| 4 | dp[3] + 1 = 4 | dp[4] = 4     |
| 5 | dp[4] + 1 = 5 | dp[5] = 5     |

So after using 1-coin: `dp = [0, 1, 2, 3, 4, 5]`

### Step 2: Use coin = 2

Now we loop from 2 to 5:

| i | dp[i - 2] + 1 | dp[i] updated |
|--|----------------|---------------|
| 2 | dp[0] + 1 = 1 | dp[2] = 1     |
| 3 | dp[1] + 1 = 2 | dp[3] = 2     |
| 4 | dp[2] + 1 = 2 | dp[4] = 2     |
| 5 | dp[3] + 1 = 3 | dp[5] = 3     |

After using 2-coin: `dp = [0, 1, 1, 2, 2, 3]`

✅ Final answer: `dp[5] = 3` (e.g. 2 + 2 + 1)

---

## ✅ Key Takeaways

| Concept | Why |
|--------|-----|
| Why start from `coin`? | You can’t make smaller amounts with that coin |
| Why go up to `amount`? | You want to fill in all possible amounts you can make with that coin |
| Why build from small to large? | So when you get to `amount`, you’ve already computed the best ways to make smaller amounts |

---

## 🧠 In simple terms:
> "To build a house, you first need to lay the bricks.  
> DP is about laying all the bricks (small amounts) **before** constructing the whole house (final amount)."

---

# Question #2
## ❓ Your question:
> “Why is it `dp[i - coin] + 1`?  
> If `coin = 3` and `i = 4`, then `i - coin = 1`.  
> Why does subtracting a coin give me a valid result?  
> It doesn’t seem like it's building multiples of the coin, or that it's adding one coin…”

This is a very insightful question — and it touches on the **core logic of the DP update rule** in the coin change problem.

---

## 🧠 The Core Idea:

```python
dp[i] = min(dp[i], dp[i - coin] + 1)
```

This means:
> “To make `i` amount,  
> try using one `coin` on top of the **best way to make `i - coin`**.”

We are not subtracting for the sake of subtraction — we are saying:

> “If I know how to make `i - coin`,  
> then I can make `i` by adding one more coin.”

---

## 🔢 Example: `coins = [3]`, `amount = 7`

Let’s walk through what happens when we use only 3-coin.

Initial `dp` array (index = amount):

```python
dp = [0, inf, inf, inf, inf, inf, inf, inf]
# index: 0   1    2    3    4    5    6    7
```

### Now we use `coin = 3`

We loop from `i = 3` to `7`, because we can’t build amounts less than 3 with a 3-coin.

#### i = 3:
```python
dp[3] = min(dp[3], dp[0] + 1) = min(inf, 0 + 1) = 1
```
→ We can make 3 using one 3-coin.

#### i = 4:
```python
dp[4] = min(dp[4], dp[1] + 1) = min(inf, inf + 1) = inf
```
→ Since we can't make 1 (`dp[1] = inf`), we can't build 4.

#### i = 6:
```python
dp[6] = min(dp[6], dp[3] + 1) = min(inf, 1 + 1) = 2
```
→ We can make 6 by first making 3 (using one 3-coin), and adding another 3-coin → total of 2 coins.

#### i = 7:
```python
dp[7] = min(dp[7], dp[4] + 1) = min(inf, inf + 1) = inf
```
→ Since we can't make 4, we can't make 7 either.

---

## 📘 Summary Table:

| i | i - coin | dp[i - coin] | Can build? | dp[i] |
|---|----------|---------------|-------------|--------|
| 3 | 0        | 0             | ✅           | 1      |
| 4 | 1        | inf           | ❌           | inf    |
| 6 | 3        | 1             | ✅           | 2      |
| 7 | 4        | inf           | ❌           | inf    |

---

## ✅ Key Takeaways

| Concept | Meaning |
|--------|---------|
| `i - coin` | The smaller sub-amount you could already build |
| `dp[i - coin]` | The minimum coins needed to make that sub-amount |
| `dp[i - coin] + 1` | Add one more coin to reach amount `i` |
| So `dp[i] = min(dp[i], dp[i - coin] + 1)` | Try building `i` using this coin, and see if it improves your current solution |

This is the **foundation of bottom-up DP** — build bigger solutions using smaller, already-solved problems.

---

## 💬 In plain English:

> “To build amount `i`,  
> see if you can build amount `i - coin`.  
> If you can, then just **add one more `coin`** to that to get `i`.”

This way, you try **all possible coin choices** for each amount and always store the **best (minimum)** number of coins.