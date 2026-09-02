
/*
# Problem
 	- `Link`: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: lowestCommonAncestorDfsRecursiveAdvanced / lowestCommonAncestorDfsRecursive / lowestCommonAncestorDfsRecursive2 / lowestCommonAncestorSearch / lowestCommonAncestorSearch2
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
- DFS Recursive
- O(log N) ~ O(N)
*/
function lowestCommonAncestorDfsRecursive(root: TreeNode | null, p: TreeNode | null, q: TreeNode | null): TreeNode | null {
	
    if (root === null) return null;
    if (p === null || q === null) return null; // For IDE

    if ((root.val >= p.val && root.val < q.val) || (root.val > p.val && root.val <= q.val)
        || (root.val <= p.val && root.val > q.val) || (root.val < p.val && root.val >= q.val)) return root;

    let result: TreeNode | null = null;
    if (root.val > p.val && root.val > q.val) {
        result = lowestCommonAncestorDfsRecursive(root.left, p, q);
    } else {
        result = lowestCommonAncestorDfsRecursive(root.right, p, q);
    }

    return result === null ? root : result;
};



/*
# Option #2
- DFS Recursive Advanced
- O(log N) ~ O(N)
*/
function lowestCommonAncestorDfsRecursiveAdvanced(root: TreeNode | null, p: TreeNode | null, q: TreeNode | null): TreeNode | null {
	
    let result: TreeNode | null = null;

    function dfs(node: TreeNode | null): void {
        if (node === null) return;
        if (p === null || q === null) return; // For IDE
        if (node.val > p.val && node.val > q.val) {
            result = node;
            dfs(node.left);
        } else if (node.val < p.val && node.val < q.val) {
            result = node;
            dfs(node.right);
        } else {
            result = node;
        }
    }

    dfs(root);
    return result;
};



/*
# Option #3
- DFS Recursive
- O(log N) ~ O(N)
- September 2, 2026
*/
function lowestCommonAncestorDfsRecursive2(root: TreeNode | null, p: TreeNode | null, q: TreeNode | null): TreeNode | null {
	if (root === null) return null;
    if ((root.val >= p!.val && root.val <= q!.val) || (root.val <= p!.val && root.val >= q!.val)) return root;
    
    if (root.val < p!.val && root.val < q!.val) return lowestCommonAncestorDfsRecursive2(root.right, p, q);
    return lowestCommonAncestorDfsRecursive2(root.left, p, q);
};



/*
# Option #4
- Search
- O(log N) ~ O(N)
*/
function lowestCommonAncestorSearch(root: TreeNode | null, p: TreeNode | null, q: TreeNode | null): TreeNode | null {
	
    let result: TreeNode | null = null;

    while (root !== null) {
        if (p === null || q === null) continue; // For IDE
        if (root.val < p.val && root.val < q.val) {
            result = root;
            root = root.right;
        } else if (root.val > p.val && root.val > q.val) {
            result = root;
            root = root.left;
        } else {
            result = root;
            break;
        }
    }
    
    return result;
};



/*
# Option #5
- Search
- O(log N) ~ O(N)
- September 2, 2026
*/
function lowestCommonAncestorSearch2(root: TreeNode | null, p: TreeNode | null, q: TreeNode | null): TreeNode | null {
	while (root !== null) {
        if ((root.val >= p!.val && root.val <= q!.val) || (root.val <= p!.val && root.val >= q!.val)) return root;
        
        if (root.val < p!.val && root.val < q!.val) root = root.right;
        else root = root.left;
    }
    return root;
};