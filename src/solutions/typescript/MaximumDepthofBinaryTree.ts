
/*
# Problem
 	- `Link`: https://leetcode.com/problems/maximum-depth-of-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: maxDepthBfs / maxDepthRecursiveDfs / maxDepthIterativeDfs
*/

class TreeNode {
    val: number
    left: TreeNode | null
    right: TreeNode | null
    constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
        this.val = (val===undefined ? 0 : val)
        this.left = (left===undefined ? null : left)
        this.right = (right===undefined ? null : right)
    }
}

/*
# Option #1
- BFS(FIFO)
- O(n)
*/
function maxDepthBfs(root: TreeNode | null): number {

    if (root === null) return 0;
    
    const deque: Array<[(TreeNode | null), number]> = [];
    deque.push([root, 1]);
    let result: number = 1;

    while (deque.length !== 0) {
        const [node, depth] = deque.shift()!;
        if (node === null || node === undefined) continue;

        result = Math.max(result, depth);

        deque.push([node.left, depth + 1]);
        deque.push([node.right, depth + 1]);
    }

    return result;
};


/*
# Option #2
- Recursive DFS(FILO)
- O(n)
*/
function maxDepthRecursiveDfs(root: TreeNode | null): number {
    if (root === null) return 0;
    return 1 + Math.max(maxDepthRecursiveDfs(root.left), maxDepthRecursiveDfs(root.right));
};


/*
# Option #3
- Iterative(/Stack-based) DFS(FILO)
- O(n)
*/
function maxDepthIterativeDfs(root: TreeNode | null): number {
    if (root === null) return 0;

    const stack: Array<[(TreeNode | null), number]> = []
    stack.push([root, 1]);

    let result: number = 0;
    while (stack.length !== 0) {
        const [node, depth] = stack.pop()!;
        if (node === null || node === undefined) continue;

        result = Math.max(result, depth);

        stack.push([node.left, depth + 1]);
        stack.push([node.right, depth + 1]);
    }

    return result;
}