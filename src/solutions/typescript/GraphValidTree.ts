
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/graph-valid-tree/
        - `LintCode`: https://www.lintcode.com/problem/178/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 15, 2025
	- `Answer`: validTreeBfs / validTreeDfs
*/

/*
# Option #1
- BFS
- O(n + e)
*/
function validTreeBfs(n: number, edges: number[][]): boolean {
    
    if (edges.length !== n - 1) return false;

    const map: Map<number, Set<number>> = new Map();
    for (let i = 0; i < n; i++) {
        map.set(i, new Set());
    }

    for (const edge of edges) {
        map.get(edge[0])!.add(edge[1]);
        map.get(edge[1])!.add(edge[0]);
    }

    const deque: Array<[number, number]> = [];
    deque.push([0, -1]);
    const visited: Set<number> = new Set();

    while (deque.length !== 0) {
        const [curr, parent]: [number, number] = deque.shift()!;
        if (visited.has(curr)) return false;
        visited.add(curr);

        const neighbors: Set<number> = map.get(curr)!;
        for (const neighbor of neighbors) {
            if (neighbor === parent) continue;
            deque.push([neighbor, curr]);
        }
    }

    return visited.size === n;
}




/*
# Option #2
- DFS
- O(n + e)
*/
function validTreeDfs(n: number, edges: number[][]): boolean {

    // Edge case: Tree requirement
    if (edges.length !== n - 1) return false;

    const map: Map<number, Set<number>> = new Map();
    
    for (let i = 0; i < n; i++) {
        map.set(i, new Set<number>());
    }
    
    for (const edge of edges) {
        map.get(edge[0])!.add(edge[1]);
        map.get(edge[1])!.add(edge[0]);
    }

    const visited: Set<number> = new Set<number>;
    if (!dfs(map, visited, -1, 0)) return false;

    return visited.size === n;
}

function dfs(map: Map<number, Set<number>>, visited: Set<number>, parent: number, curr: number): boolean {
    if (visited.has(curr)) return false;

    visited.add(curr);

    const neighbors = map.get(curr)!;
    for (const neighbor of neighbors) {
        if (parent === neighbor) continue;
        if (!dfs(map, visited, curr, neighbor)) return false;
    }

    return true;
}