
/*
# Problem
 	- `Link`: https://leetcode.com/problems/validate-binary-search-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: isValidBST
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
function isValidBST(root: TreeNode | null): boolean {
    if (root === null) return true;
    
    function dfs(node: TreeNode | null, maxAmongSmaller: number, minAmongBigger): boolean {
        if (node === null) return true;
        if (node.val <= maxAmongSmaller || node.val >= minAmongBigger) return false;
        // return dfs(node.left, maxAmongSmaller, Math.min(minAmongBigger, node.val)) &&
        //     dfs(node.right, Math.max(maxAmongSmaller, node.val), minAmongBigger);
        return dfs(node.left, maxAmongSmaller, node.val) &&
            dfs(node.right, node.val, minAmongBigger);
    }

    return dfs(root.left, -Infinity, root.val) && dfs(root.right, root.val, Infinity);
};