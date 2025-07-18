
/*
# Problem
 	- `Link`: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: buildTree
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
# Option 1
- DFS
- O(n)
*/
function buildTree(preorder: number[], inorder: number[]): TreeNode | null {
    
    // Preorder: center -> left -> right
    // Inorder: left -> center -> right

    const n: number = preorder.length;
    const map: Map<number, number> = new Map();
    for (let i = 0; i < n; i++) {
        map.set(inorder[i], i);
    }

    let localPreorderIndex: number = 0;

    function dfs(left: number, right: number): TreeNode | null {
        if (left >= right) return null;

        const node: TreeNode = new TreeNode(preorder[localPreorderIndex++]);

        const mid: number = map.get(node.val)!;

        node.left = dfs(left, mid);
        node.right = dfs(mid + 1, right);

        return node
    }

    return dfs(0, n);
};