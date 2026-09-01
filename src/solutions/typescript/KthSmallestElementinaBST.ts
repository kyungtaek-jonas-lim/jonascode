
/*
# Problem
 	- `Link`: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: kthSmallestDfsRecursive / kthSmallestDfsRecursive2 / kthSmallestDfsIterative
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
function kthSmallestDfsRecursive(root: TreeNode | null, k: number): number {
    
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

/*
# Option #2
- DFS Recursive - Find the smallest and gets bigger
- O(H + k) (H = the height of the tree)
*/
function kthSmallestDfsRecursive2(root: TreeNode | null, k: number): number {

    let result: number = 0;
    
    function dfs(node: TreeNode | null, curr: number): number {
        if (node === null) return curr;
        let res: number = dfs(node.left, curr) + 1;
        if (res === 0) return -1;
        if (res === k) {
            result = node.val;
            return -1;
        }

        res = dfs(node.right, res);
        if (res === -1) return -1;
        if (res === k) {
            result = node.val;
            return -1;
        }
        return res;
    }

    dfs(root, 0);
    return result;
};

/*
# Option #3
- DFS Iterative - Find the smallest and gets bigger
- O(H + k) (H = the height of the tree)
*/
function kthSmallestDfsIterative(root: TreeNode | null, k: number): number {
    const stack: TreeNode[] = [];
    let node: TreeNode | null = root;
    
    while (node !== null || stack.length !== 0) {
        while (node !== null) {
            stack.push(node);
            node = node.left;
        }
        node = stack.pop()!;
        if (--k === 0) return node.val;
        node = node.right;
    }
    return -1;
};