
/*
# Problem
 	- `Link`: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: kthSmallest
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
- DFS Recursive - Find the smallest and gets bigger
- O(H + k) (H = the height of the tree)
*/
function kthSmallest(root: TreeNode | null, k: number): number {
    
    let result: number = root!.val;
    let cnt: number = 0;

    function dfs(node: TreeNode | null): void {
        if (node === null) return;

        dfs(node.left);
        if (cnt === k) return;

        cnt++;
        if (cnt === k) {
            result = node.val;
            return;
        };

        dfs(node.right);
        if (cnt === k) return;
    }

    dfs(root);
    return result;
};