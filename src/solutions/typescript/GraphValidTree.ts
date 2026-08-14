
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/graph-valid-tree/
        - `LintCode`: https://www.lintcode.com/problem/178/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 15, 2025
	- `Answer`: validTreeBfs / validTreeDfs / validTreeDfs2 / validTreeUnionFind
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




/*
# Option #3
- DFS (Basically, the same as Option #2)
- O(n + e)
- August 14, 2026
*/
function validTreeDfs2(n: number, edges: number[][]): boolean {
    if (n - 1 !== edges.length) return false;

    const graph: Map<number, Array<number>> = new Map();
    for (let i = 0; i < n; i++) graph.set(i, []);
    for (const e of edges) {
        graph.get(e[0])!.push(e[1]);
        graph.get(e[1])!.push(e[0]);
    }

    const visited: Set<number> = new Set();
    function hasCycle(curr: number, parent: number): boolean {
        visited.add(curr);
        for (const n of graph.get(curr)!) {
            if (n === parent) continue;
            if (visited.has(n) || hasCycle(n, curr)) return true;
        }
        return false;
    }

    if (hasCycle(0, -1)) return false;
    return n === visited.size;
};




/*
# Option #4
- Union-Find
- O(n + e)
- August 14, 2026
*/
function validTreeUnionFind(n: number, edges: number[][]): boolean {
    if (n - 1 !== edges.length) return false;

    const parents: Array<number> = new Array(n);
    for (let i = 0; i < n; i++) parents[i] = i;

    function find(curr: number): number { // Find the parent
        while (curr != parents[curr]) {
            parents[curr] = parents[parents[curr]];
            curr = parents[curr];
        }
        return curr;
    }

    for (const e of edges) {
        const p1: number = find(e[0]);
        const p2: number = find(e[1]);
        if (p1 === p2) return false; // Cycle
        parents[p1] = p2;
    }
    return true; // Survived all edges with no cycle, and had exactly n-1 of them
};



console.log(validTreeDfs2(5, [[0,1],[0,2],[0,3],[1,4]]));
// true

console.log(validTreeDfs2(5, [[0,1],[1,2],[2,3],[1,3],[1,4]]));
// false  // cycle: 1 -> 2 -> 3 -> 1

console.log(validTreeDfs2(4, [[1,0],[2,0],[3,0]]));
// true  // star tree, this is the case your ORIGINAL Java code (before the undirected fix) failed on

console.log(validTreeDfs2(4, [[0,1],[0,2],[3,1]]));
// true  // valid tree, edge written "backwards"

console.log(validTreeDfs2(5, [[0,1],[2,3]]));
// false // disconnected forest, also wrong edge count (2 edges, needs 4)

console.log(validTreeDfs2(2, []));
// false // disconnected, 0 edges but needs 1

console.log(validTreeDfs2(1, []));
// true  // single node, no edges needed, trivially a valid tree

console.log(validTreeDfs2(4, [[0,1],[1,2],[2,3]]));
// true  // straight line (path graph), still a valid tree