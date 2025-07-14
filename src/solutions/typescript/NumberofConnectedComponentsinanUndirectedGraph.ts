
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
        - `LintCode`: https://www.lintcode.com/problem/3651/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 14, 2025
	- `Answer`: countComponentsBfs / countComponentsDfs / countComponentsAdvanced
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
function countComponentsAdvanced(n: number, edges: number[][]): number {
	
	// Put every number
	const parents: number[] = [];
	const ranks: number[] = new Array<number>().fill(1);
	for (let i = 0; i < n; i++) {
		parents[i] = i;
	}

	// Union-Find
	let result: number = n;
	for (const edge of edges) {
		result -= union(parents, ranks, edge[0], edge[1]);
	}
	return result;
}


function union(parents: number[], ranks: number[], n1: number, n2: number): number { // Return 1 if there's union else return 0
	// Find parents of each
	const p1: number = find(parents, n1), p2: number = find(parents, n2);

	if (p1 === p2) return 0; // If it's already merged, return 0

	if (ranks[p1] >= ranks[p2]) { // The less score number is merged into the bigger one ("rank" is for this condition)
		parents[p2] = p1;
		ranks[p1] += ranks[p2];
	} else {
		parents[p1] = p2;
		ranks[p2] += ranks[p1];
	}

	return 1;
}

function find(parents: number[], child: number): number {
	let parent = child;
	while (parent !== parents[parent]) { // Find the root parents
		parents[parent] = parents[parents[parent]];
		parent = parents[parent];
	}
	return parent;
}