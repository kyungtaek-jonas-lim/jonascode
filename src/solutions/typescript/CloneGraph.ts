
/*
# Problem
	- `Link`: https://leetcode.com/problems/clone-graph/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: cloneGraph
*/

class _Node {
    val: number
    neighbors: _Node[]

    constructor(val?: number, neighbors?: _Node[]) {
        this.val = (val===undefined ? 0 : val)
        this.neighbors = (neighbors===undefined ? [] : neighbors)
    }
}

/*
# Option #1
- O(N + E) (N == the number of Nodes, E == the number of Edges)
*/
function cloneGraph(node: _Node | null): _Node | null {
    if (node === null) return null;
	const memo = new Map<_Node, _Node>();
    return dfs(node, memo);
};

function dfs(node: _Node, memo: Map<_Node, _Node>): _Node{
    if (memo.has(node)) return memo.get(node)!;
    const newNode: _Node = new _Node(node.val);
    memo.set(node, newNode);
    for (const neighbor of node.neighbors) {
        newNode.neighbors.push(dfs(neighbor, memo));
    }
    return newNode;
}