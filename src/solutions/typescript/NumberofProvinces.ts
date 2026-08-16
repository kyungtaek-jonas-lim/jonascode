/*
 # Problem
 	- `Link`: https://leetcode.com/problems/number-of-provinces/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: July 15, 2025
 	- `Answer`: findCircleNumUnionFind / findCircleNumDfs
*/

/*
# Option #1
- Union-find
- O(n^2)
*/
function findCircleNumUnionFind(isConnected: number[][]): number {
    
    const n: number = isConnected.length;
    const parents: number[] = new Array(n);
    for (let i = 0; i < n; i++) parents[i] = i;
    const ranks: number[] = new Array(n).fill(1);

    function find(curr: number): number {
        while (curr != parents[curr]) {
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
            ranks[b]++;
            parents[a] = b;
        } else {
            ranks[a]++;
            parents[b] = a;
        }
        return 1;
    }

    let result: number = n;
    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            if (isConnected[i][j] === 1) {
                result -= union(i, j);
            }
        }
    }
    return result;
};


/*
# Option #2
- DFS 'Option #1(Union-find)' is faster
- O(n^2)
- August 16, 2026
*/
function findCircleNumDfs(isConnected: number[][]): number {
    
    const n: number = isConnected.length;
    const graph: Map<number, Array<number>> = new Map();
    for (let i = 0; i < n; i++) graph.set(i, []);
    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            if (isConnected[i][j] === 1) {
                graph.get(i)!.push(j);
                graph.get(j)!.push(i);
            }
        }
    }

    const visited: Set<number> = new Set();
    function dfs(curr: number): void {
        if (visited.has(curr)) return;
        visited.add(curr);
        for (const nei of graph.get(curr)!) {
            dfs(nei);
        }
    };

    let result: number = 0;
    for (let i = 0; i < n; i++) {
        if (!visited.has(i)) {
            result++;
            dfs(i);
        }
    }
    return result;
};