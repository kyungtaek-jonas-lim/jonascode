
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
        - `LintCode`: https://www.lintcode.com/problem/3651/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 14, 2025
	- `Answer`: countComponentsBfs / countComponentsDfs / countComponentsUnionFind / countComponentsUnionFind2
*/

// 1 <= n <= 2000
// 0 <= edges.length <= n * (n - 1) / 2
// edges[i].length == 2
// 0 <= edges[i][j] < n


/*
# Option #1
- BFS
- O(n + e)
*/
function countComponentsBfs(n: number, edges: number[][]): number {

	// Put every number
	const map: Map<number, Set<number>> = new Map();
	for (let i = 0; i < n; i++) {
		map.set(i, new Set<number>());
	}

	// Make graphs
	for (const edge of edges) {
		map.get(edge[0])!.add(edge[1]);
		map.get(edge[1])!.add(edge[0]);
	}

	// Check the result
	let result: number = 0;
	const visited: Set<number> = new Set();
	const deque: number[] = [];

	for (let i = 0; i < n; i++) {
		if (visited.has(i)) continue;
		result++;

		deque.push(i);

		while (deque.length > 0) {
			const curr = deque.shift()!;
			if (visited.has(curr)) continue;
			visited.add(curr);

			const set: Set<number> = map.get(curr)!;
			for (const neighbor of set) {
				deque.push(neighbor);
			}
		}
	}

	return result;
}



/*
# Option #2
- DFS
- O(n + e)
*/
function countComponentsDfs(n: number, edges: number[][]): number {
    
	// Put every number
	const map: Map<number, Set<number>> = new Map();
	for (let i = 0; i < n; i++) {
		map.set(i, new Set<number>())
	}

	// Make graphs
	for (const edge of edges) {
		map.get(edge[0])!.add(edge[1]);
		map.get(edge[1])!.add(edge[0]);
	}

	// Check the result
	let result: number = 0;
	const visited: Set<number> = new Set();
	for (let i = 0; i < n; i++) {
		if (visited.has(i)) continue;
		dfs(map, i, visited);
		result++;
	}

	return result;
}

function dfs(map: Map<number, Set<number>>, curr: number, visited: Set<number>): void {
	if (visited.has(curr)) return;
	visited.add(curr);

	const set = map.get(curr)!;
	for (const neighbor of set) {
		dfs(map, neighbor, visited);
	}
}



/*
# Option #3
- Union-Find
- O(n + e)
- https://www.youtube.com/watch?v=8f1XPm4WOUc
*/
function countComponentsUnionFind(n: number, edges: number[][]): number {
    
    
    const parents: Array<number> = new Array(n);
    const ranks: Array<number> = new Array(n).fill(1);
    for (let i = 0; i < n; i++) parents[i] = i;

    function find(curr: number): number {
        while (curr !== parents[curr]) {
            parents[curr] = parents[parents[curr]];
            curr = parents[curr];
        }
        return curr;
    }

    function union(a: number, b: number): number {
        a = find(a);
        b = find(b);
        if (a === b) return 0;
        if (ranks[a] < ranks[b]) {
            parents[a] = b;
            ranks[b]++;
        } else {
            parents[b] = a;
            ranks[a]++;
        }
        return 1;
    }

    let result: number = n;
    for (const e of edges) {
        result -= union(e[0], e[1]);
    }
    return result;
};



/*
# Option #4
- Union-Find (Option #3 is better)
- O((n+e)·log n)
- August 15, 2026
*/
function countComponentsUnionFind2(n: number, edges: number[][]): number {
    
    const parents: Array<number> = new Array(n);
    for (let i = 0; i < n; i++) parents[i] = i;

    function find(curr: number): number {
        while (curr !== parents[curr]) {
            parents[curr] = parents[parents[curr]];
            curr = parents[curr];
        }
        return curr;
    }

    function union(a: number, b: number): void {
        a = find(a);
        b = find(b);
        
        if (a < b) parents[b] = a;
        else parents[a] = b;
    }

    for (const e of edges) {
        union(e[0], e[1]);
    }

    const result: Set<number> = new Set();
    for (let i = 0; i < n; i++) result.add(find(i));
    return result.size;
};