
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/alien-dictionary/
        - `LintCode`: https://www.lintcode.com/problem/892/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 14, 2025
	- `Answer`: alienOrder / alien_order
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
    const result: string[] = [];
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



/*
# Option #2
- Recursive DFS
- O(n * m + k + e) (n = words.length, m = the average length of words, k = total number of unique characters, e = number of edges)
- August 11, 2026
*/
function alien_order(words: string[]): string {
    
    const n: number = words.length;
    if (n === 0) return "";

    // Make Graph
    const graph: Map<string, Set<string>> = new Map();
    for (const word of words) {
        for (let i = 0; i < word.length; i++) {
            if (graph.has(word[i])) continue;
            graph.set(word[i], new Set());
        }
    }

    for (let i = 1; i < n; i++) {
        if (words[i].startsWith(words[i - 1])) continue;
        if (words[i - 1].startsWith(words[i])) return "";
        
        const minLen: number = Math.min(words[i - 1].length, words[i].length);
        for (let j = 0; j < minLen; j++) {
            if (words[i - 1][j] !== words[i][j]) {
                graph.get(words[i][j])!.add(words[i - 1][j]);
                break;
            }
        }
    }

    // Make String
    const memo: Map<string, boolean> = new Map();
    function dfs(curr: string): string {
        if (memo.has(curr)) {
            if (memo.get(curr)) return "";
            return "#";
        }
        memo.set(curr, false);

        let res: string = "";
        for (const s of graph.get(curr)!) {
            const pre: string = dfs(s);
            if (pre === "#") return "#";
            res += pre;
        }

        memo.set(curr, true);
        return res + curr;
    };

    let result: string = "";
    for (const s of graph.keys()) {
        const prefix: string = dfs(s);
        if (prefix === '#') return "";
        result += prefix;
    }
    return result;
};





console.log(alienOrder(["cbb", "cab", "cac", "cca"]));
console.log(alienOrder(["wrt","wrf","er","ett","rftt"]));
console.log(alienOrder(["z","x"]))
console.log(alienOrder(["abc", "ab"]))
console.log(alienOrder(["z", "x", "z"]))
console.log(alienOrder(["wrt", "wrf", "er", "ett", "rftt"]))
console.log(alienOrder(["abc", "abd"]))
console.log(alienOrder( ["za", "zb", "ca", "cb"]))
console.log(alienOrder(["abc", "abd", "acd"]))
console.log(alienOrder(["cbb", "cab", "cac", "cca"]))
console.log(alienOrder( ["wrt", "wrf", "er", "ett", "rftt"]))
console.log(alienOrder( ["cac", "caa", "caa"]))
console.log(alienOrder(["cca"]))