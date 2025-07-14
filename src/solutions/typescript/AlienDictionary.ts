
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/alien-dictionary/
        - `LintCode`: https://www.lintcode.com/problem/892/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 14, 2025
	- `Answer`: alienOrder
*/

/*
# Option #1
- Recursive DFS
- O(n * m + k + e) (n = words.length, m = the average length of words, k = total number of unique characters, e = number of edges)
- ref) https://www.youtube.com/watch?v=6kTZYvNNyps
*/
function alienOrder(words: string[]): string {
    
    const n: number = words.length;
    const map: Map<string, Set<string>> = new Map();

    // Put every character into Map not to miss any chacter (Every character should be in the ordered string)
    for (const word of words) {
        for (const c of word) {
            if (!map.has(c)) map.set(c, new Set<string>());
        }
    }

    // Make graphs
    for (let i = 1; i < n; i ++) {
        const w1 = words[i - 1], w2 = words[i];
        const w1Length = w1.length, w2Length = w2.length;

        // Edge case
        if (w1Length > w2Length && w1.startsWith(w2)) return "";

        const minLength = Math.min(w1Length, w2Length);
        for (let j = 0; j < minLength; j++) {
            if (w1[j] !== w2[j]) {
                // w1 -> w2
                map.get(w1[j])!.add(w2[j]);
                break;
            }
        }
    }

    // Make string (DFS)
    const result = [];
    const visited: Map<string, boolean> = new Map();
    for (const c of map.keys()) {
        if (!dfs(map, c, visited, result)) return "";
    }
    
    // Reverse
    return result.reverse().join("");
}

function dfs(map: Map<string, Set<string>>, curr: string, visited: Map<string, boolean>, result: string[]): boolean {
    if (visited.has(curr)) return visited.get(curr)!;

    visited.set(curr, false); // Visited but not finished to prevent cycle

    const nexts = map.get(curr)!;
    for (const next of nexts) {
        if (!dfs(map, next, visited, result)) return false;
    }
    
    visited.set(curr, true); // Visited and finished
    result.push(curr);
    return true;
}