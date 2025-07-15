/*
 # Problem
 	- `Link`: https://leetcode.com/problems/number-of-provinces/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: July 15, 2025
 	- `Answer`: findCircleNum
*/

/*
# Option #1
- Union-find
- O(n^2)
*/
function findCircleNum(isConnected: number[][]): number {
    
    const n: number = isConnected.length;
    const parents: number[] = [];
    const ranks: number[] = new Array(n).fill(1);

    for (let i = 0; i < n; i++) {
        parents.push(i);
    }

    let result = n;
    for (let i = 0; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            if (isConnected[i][j] === 1) {
                result -= union(parents, ranks, i, j);
            }
        }
    }

    return result;
};

function union(parents: number[], ranks: number[], n1: number, n2: number): number {
    const p1 = find(parents, n1), p2 = find(parents, n2);

    if (p1 === p2) return 0;
    
    if (ranks[p1] >= ranks[p2]) {
        parents[p2] = p1;
        ranks[p1] += ranks[p2];
    } else {
        parents[p1] = p2;
        ranks[p2] += ranks[p1];
    }

    return 1;
}

function find(parents: number[], n: number): number {
    let parent = n;
    while (parents[parent] !== parent) {
        parents[parent] = parents[parents[parent]];
        parent = parents[parent];
    }
    return parent;
}