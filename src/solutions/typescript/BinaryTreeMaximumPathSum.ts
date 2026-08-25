
/*
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-maximum-path-sum/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: maxPathSumRecursiveDfs / maxPathSumWorstButWorks
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


/*
# Option #2
- DFS - Worst but it works
- O(n)
- August 22, 2026
*/
function maxPathSumWorstButWorks(root: TreeNode | null): number {
    
    let maxResult: number = root!.val;
    function dfs(node: TreeNode | null): number {
        if (node === null) return -1001;
        const leftVal: number = dfs(node.left), rightVal: number = dfs(node.right);

        let res: number = node.val;
        if (leftVal >= 0 && rightVal >= 0) {
            maxResult = Math.max(maxResult, node.val + leftVal + rightVal);
            res = Math.max(node.val + leftVal, node.val + rightVal);
        } else if (leftVal >= 0) {
            res = node.val + leftVal;
        } else if (rightVal >= 0) {
            res = node.val + rightVal;
        }
        maxResult = Math.max(maxResult, res);
        return res;
    }
    dfs(root);
    return maxResult;
};