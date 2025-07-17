
/*
# Problem
 	- `Link`: https://leetcode.com/problems/same-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: isSameTreeBfs / isSameTreeRecursiveDfs / isSameTreeIterativeDfs
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
function isSameTreeBfs(p: TreeNode | null, q: TreeNode | null): boolean {

    const deque: Array<[(TreeNode | null), (TreeNode | null)]> = [];
    deque.push([p, q]);

    while (deque.length !== 0) {
        const [n1, n2] = deque.shift()!;
        if (n1 === null && n2 === null) continue;
        if (n1 === null || n2 === null) return false;
        if (n1.val !== n2.val) return false;

        deque.push([n1.left, n2.left]);
        deque.push([n1.right, n2.right]);
    }

    return true;
};


/*
# Option #2
- Recursive DFS(FILO)
- O(n)
*/
function isSameTreeRecursiveDfs(p: TreeNode | null, q: TreeNode | null): boolean {
    if (p === null && q === null) return true;
    if (p === null || q === null) return false;
    if (p.val !== q.val) return false;
    return isSameTreeRecursiveDfs(p.left, q.left) && isSameTreeRecursiveDfs(p.right, q.right);
};


/*
# Option #3
- Iterative(/Stack-based) DFS(FILO)
- O(n)
*/
function isSameTreeIterativeDfs(p: TreeNode | null, q: TreeNode | null): boolean {

    const deque: Array<[(TreeNode | null), (TreeNode | null)]> = [];
    deque.push([p, q]);

    while (deque.length !== 0) {
        const [n1, n2] = deque.pop()!;
        if (n1 === null && n2 === null) continue;
        if (n1 === null || n2 === null) return false;
        if (n1.val !== n2.val) return false;

        deque.push([n1.left, n2.left]);
        deque.push([n1.right, n2.right]);
    }

    return true;
};