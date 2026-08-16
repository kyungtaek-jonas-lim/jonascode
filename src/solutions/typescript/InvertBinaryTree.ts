
/*
# Problem
 	- `Link`: https://leetcode.com/problems/invert-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: invertTreeBfs / invertTreeRecursiveDfs / invertTreeIterativeDfs
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
// function invertTreeBfs(root: TreeNode | null): TreeNode | null {
//     if (root === null) return null;

//     const deque: (TreeNode | null)[] = [];
//     deque.push(root);

//     while (deque.length !== 0) {
//         const node: (TreeNode | null) = deque.shift()!;
//         if (node === null || node === undefined) continue;
//         const temp: (TreeNode | null) = node.left;
//         node.left = node.right;
//         node.right = temp;

//         deque.push(node.left);
//         deque.push(node.right);
//     }

//     return root;
// };
function invertTreeBfs(root: TreeNode | null): TreeNode | null {
    let queue: Array<TreeNode | null> = [root];

    while (queue.length > 0) {
        const next: Array<TreeNode | null> = [];
        const n: number = queue.length;

        for (let i = 0; i < n; i++) {
            const node: TreeNode | null = queue[i];
            if (node === null) continue;
            
            const left: TreeNode | null = node.left;
            node.left = node.right;
            node.right = left;

            next.push(node.left);
            next.push(node.right);
        }
        queue = next;
    }

    return root;
};


/*
# Option #2
- Recursive DFS(FILO)
- O(n)
*/
function invertTreeRecursiveDfs(root: TreeNode | null): TreeNode | null {
    if (root === null) return null;
    const temp: (TreeNode | null) = invertTreeRecursiveDfs(root.left);
    root.left = invertTreeRecursiveDfs(root.right);
    root.right = temp;
    return root;
};


/*
# Option #3
- Iterative(/Stack-based) DFS(FILO)
- O(n)
*/
function invertTreeIterativeDfs(root: TreeNode | null): TreeNode | null {
    if (root === null) return null;

    const deque: (TreeNode | null)[] = [];
    deque.push(root);

    while (deque.length !== 0) {
        const node: (TreeNode | null) = deque.pop()!;
        if (node === null || node === undefined) continue;
        const temp: (TreeNode | null) = node.left;
        node.left = node.right;
        node.right = temp;

        deque.push(node.left);
        deque.push(node.right);
    }

    return root;
};