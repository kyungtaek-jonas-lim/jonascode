
# Topological Sort
> **Topological Sort involves a DAG (Directed Acyclic Graph), which is a directed graph with no cycles.**

Topological Sort is an algorithm that arranges the nodes of a directed graph in a linear order such that for every directed edge `u → v`, node `u` comes before node `v`.
This is only possible in a **DAG (Directed Acyclic Graph)**:

* **Directed** means the edges have direction.
* **Acyclic** means there are no cycles (no way to return to the same node by following directed edges).
* **Graph** means a collection of nodes (vertices) and edges (connections).

In problems like the **Alien Dictionary**, we use Topological Sort to determine the order of characters by building a graph of dependencies and then sorting it.

## Idea

We are given a list of words in a special language. The words are already sorted in dictionary order. From this, we need to find the order of the letters.
We do this by building a graph of rules between letters, then running **topological sort**.

---

## Steps

1. **Collect all letters**

   * Take every unique letter from all the words. These are our graph nodes.

2. **Build rules (edges)**

   * Compare each pair of words that are next to each other.
   * Find the first place where the letters are different.

     * Example: "wrt" and "wrf" → at the 3rd position, `t` and `f` differ → so `t` must come before `f`.
   * Make a rule (edge) from the first letter to the second.
   * If one word is longer but the shorter word is a prefix (e.g. "abc" and "ab"), this is invalid (no solution).

3. **Topological Sort (BFS method)**

   * Count how many arrows point to each letter (in-degree).
   * Put letters with **0 arrows pointing to them** into a queue.
   * Take from the queue, add to result, and remove its arrows.
   * If another letter’s in-degree becomes 0, add it to the queue.

4. **Check result**

   * If we used all letters, the result is valid.
   * If not, there was a cycle (contradiction).

---

## Example

Words: `["wrt", "wrf", "er", "ett", "rftt"]`

* "wrt" vs "wrf" → `t → f`
* "wrf" vs "er" → `w → e`
* "er" vs "ett" → `r → t`
* "ett" vs "rftt" → `e → r`

Rules (edges): `t→f, w→e, r→t, e→r`

Order by BFS:

* Start with `w` → result: `w`
* Then `e` → result: `we`
* Then `r` → result: `wer`
* Then `t` → result: `wert`
* Then `f` → result: `wertf`

So the answer is `"wertf"`.

---

## Python Code

```python
from collections import defaultdict, deque

def alien_order(words):
    # 1. collect all letters
    nodes = set(ch for w in words for ch in w)
    graph = defaultdict(set)
    indeg = {ch: 0 for ch in nodes}

    # 2. build edges
    for i in range(len(words) - 1):
        w1, w2 = words[i], words[i+1]
        if len(w1) > len(w2) and w1.startswith(w2):
            return ""  # invalid case
        for a, b in zip(w1, w2):
            if a != b:
                if b not in graph[a]:
                    graph[a].add(b)
                    indeg[b] += 1
                break

    # 3. BFS
    q = deque([ch for ch in indeg if indeg[ch] == 0])
    order = []
    while q:
        ch = q.popleft()
        order.append(ch)
        for nxt in graph[ch]:
            indeg[nxt] -= 1
            if indeg[nxt] == 0:
                q.append(nxt)

    # 4. check
    return "".join(order) if len(order) == len(nodes) else ""
```