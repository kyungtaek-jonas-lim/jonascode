
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
// function levelOrder(root: TreeNode | null): number[][] {

//     if (root === null) return [];
    
//     const deque: Array<TreeNode | null> = [root];
//     const result: number[][] = [];
//     let pointer: number = 0;
    
//     while (deque.length - pointer !== 0) {

//         const existingSize = deque.length;
//         const item: number[] = []

//         while (existingSize - pointer !== 0) {

//             const node: TreeNode | null = deque[pointer++];
//             if (node === null) continue;
//             item.push(node.val);
            
//             deque.push(node.left);
//             deque.push(node.right);
//         }

//         if (item.length !== 0) result.push(item);
//     }
//     return result;
// };
function levelOrder(root: TreeNode | null): number[][] {
    const result: Array<Array<number>> = [];
    if (root === null) return result;

    let deque: TreeNode[] = [root];
    while (deque.length !== 0) {
        const n: number = deque.length;
        const item: Array<number> = [];
        const next: TreeNode[] = [];

        for (let i = 0; i < n; i++) {
            item.push(deque[i].val);
            if (deque[i].left !== null) next.push(deque[i].left!);
            if (deque[i].right !== null) next.push(deque[i].right!);
        }

        result.push(item);
        deque = next;
    }
    return result;
};
