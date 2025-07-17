
/*
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-maximum-path-sum/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: maxPathSumRecursiveDfs
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
- DFS
- O(n)
*/
function maxPathSumRecursiveDfs(root: TreeNode | null): number {
    
    let result: number = -Infinity;
    
    function dfs(node: TreeNode | null): number {
        if (node === null) return 0;
        
        const leftMax: number = Math.max(dfs(node.left), 0), rightMax: number = Math.max(dfs(node.right), 0);
        result = Math.max(result, leftMax + rightMax + node.val);
        return Math.max(leftMax, rightMax) + node.val;
    }
    dfs(root);  
    return result;
};