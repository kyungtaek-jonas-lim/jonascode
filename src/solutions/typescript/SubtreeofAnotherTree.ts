
/*
# Problem
 	- `Link`: https://leetcode.com/problems/subtree-of-another-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: isSubtreeBfs / isSubtreeDfsRecursive / isSubtreeDfsRecursiveAdvanced / isSubtreeBfsAndDfs
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
- O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
*/
// function isSubtreeBfs(root: TreeNode | null, subRoot: TreeNode | null): boolean {

//     if (root === null && subRoot === null) return true;
//     if (root === null || subRoot === null) return false;
    
//     const deque: (TreeNode | null)[] = [root];

//     while (deque.length !== 0) {

//         const node: TreeNode | null = deque.shift()!;
//         if (node === null) continue;
//         deque.push(node.left);
//         deque.push(node.right);

//         if (node.val === subRoot.val) {
//             const temp: Array<[(TreeNode | null), (TreeNode | null)]> = [[node, subRoot]];
//             let res: boolean = true;
//             while (temp.length !== 0) {
//                 const [n1, n2] = temp.shift()!;
//                 if (n1 === null && n2 === null) continue;
//                 if (n1 === null || n2 === null || n1.val !== n2.val) {
//                     res = false;
//                     break;
//                 }
//                 temp.push([n1.left, n2.left]);
//                 temp.push([n1.right, n2.right]);
//             }

//             if (res) return true;
//         }
//     }

//     return false;
// }

// August 26, 2026
function isSubtreeBfs(root: TreeNode | null, subRoot: TreeNode | null): boolean {
    
    const q: Array<TreeNode> = [root!];
    let head: number = 0;
    while (head < q.length) {
        const node: TreeNode = q[head++]!;
        if (node.val === subRoot!.val) {
            const q2: [TreeNode | null, TreeNode | null][] = [[node.left, subRoot!.left], [node.right, subRoot!.right]];
            let head2: number = 0;
            let same: boolean = true;
            while (head2 < q2.length) {
                const [n1, n2]: [TreeNode | null, TreeNode | null] = q2[head2++];
                if (n1 === null && n2 === null) continue;
                if (n1 === null || n2 === null || n1.val != n2.val) {
                    same = false;
                    break;
                }
                q2.push([n1.left, n2.left]);
                q2.push([n1.right, n2.right]);
            }
            if (same) return true;
        }
        if (node.left !== null) q.push(node.left);
        if (node.right !== null) q.push(node.right);
    }
    return false;
};




/*
# Option #2
- DFS Recursive
- With only one function
- O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
*/
function isSubtreeDfsRecursive(root: TreeNode | null, subRoot: TreeNode | null): boolean {

    let isOn: boolean = false;

    function dfs(node: TreeNode | null, subNode: TreeNode | null): boolean {

        if (node === null && subNode === null) return true;
        if (node === null || subNode === null) return false;
        if (isOn && node.val !== subNode.val) return false;

        if (node.val === subNode.val) {
            const wasOn = isOn;
            isOn = true;
            if (dfs(node.left, subNode.left) && dfs(node.right, subNode.right)) {
                return true;
            } else if (wasOn) {
                return false;
            } else {
                isOn = false;
            }
        }

        return dfs(node.left, subNode) || dfs(node.right, subNode);
    }

    return dfs(root, subRoot);
};




/*
# Option #3
- DFS Recursive (Advanced)
- With two function
- O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
*/
function isSubtreeDfsRecursiveAdvanced(root: TreeNode | null, subRoot: TreeNode | null): boolean {

    function isSame(n1: TreeNode | null, n2: TreeNode | null): boolean {
        if (n1 === null && n2 === null) return true;
        if (n1 === null || n2 === null) return false;
        if (n1.val !== n2.val) return false;
        return isSame(n1.left, n2.left) && isSame(n1.right, n2.right);
    }

    function dfs(n1: TreeNode | null): boolean {
        if (isSame(n1, subRoot)) return true;
        if (n1 === null) return false;

        return dfs(n1!.left) || dfs(n1!.right);
    }

    return dfs(root);
}




/*
# Option #4
- Bfs + DFS Recursive
- With two function
- O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
- August 26, 2026
*/
function isSubtreeBfsAndDfs(root: TreeNode | null, subRoot: TreeNode | null): boolean {
    
    function dfs(n1: TreeNode | null, n2: TreeNode | null): boolean {
        if (n1 === null && n2 === null) return true;
        if (n1 === null || n2 === null) return false;
        if (n1.val === n2.val) {
            if (dfs(n1.left, n2.left) && dfs(n1.right, n2.right)) return true;
        }
        return false;
    }

    const q: Array<TreeNode | null> = [root];
    let head: number = 0;
    while (head < q.length) {
        const node: TreeNode | null = q[head++]!;
        if (node === null) continue;
        if (dfs(node, subRoot)) return true;
        q.push(node.left);
        q.push(node.right);
    }
    return false;
};

