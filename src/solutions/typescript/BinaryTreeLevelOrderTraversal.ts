
/*
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-level-order-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: levelOrder
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
- BFS
- O(n)
*/
function levelOrder(root: TreeNode | null): number[][] {
    
    const result: number[][] = [];
    if (root === null) return result;
    
    const deque: TreeNode[] = [];
    deque.push(root);

    while (deque.length !== 0) {
        
        const end: number = deque.length;
        const item: number[] = [];

        for (let i = 0; i < end; i++) {
            const node: TreeNode = deque.shift()!;
            item.push(node.val);
            if (node.left !== null) {
                deque.push(node.left);
            }
            if (node.right !== null) {
                deque.push(node.right);
            }
        }

        result.push(item);
    }

    return result;
};