package solutions.java;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: lowestCommonAncestorDfsRecursive / lowestCommonAncestorDfsRecursiveSimple / lowestCommonAncestorSearch
 */
public class LowestCommonAncestorofaBinarySearchTree {
	
	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}

	/*
    # Option #1
    - DFS Recursive
    - O(H) (H = The height of the tree) (O(log n) - O(n))
	 */
    public TreeNode lowestCommonAncestorDfsRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val == p.val || root.val == q.val) return root;
        if (root.val < p.val) {
            if (root.val > q.val) return root;
            else return lowestCommonAncestorDfsRecursive(root.right, p, q);
        } else {
            if (root.val < q.val) return root;
            else return lowestCommonAncestorDfsRecursive(root.left, p, q);
        }
    }
    


	/*
    # Option #2
    - DFS Recursive Simple
    - O(H) (H = The height of the tree) (O(log n) - O(n))
	 */
    public TreeNode lowestCommonAncestorDfsRecursiveSimple(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val < p.val && root.val < q.val) {
        	return lowestCommonAncestorDfsRecursiveSimple(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
        	return lowestCommonAncestorDfsRecursiveSimple(root.left, p, q);
        }
        return root;
    }
    

	/*
    # Option #3
    - Search
    - O(H) (H = The height of the tree) (O(log n) - O(n))
	 */
    public TreeNode lowestCommonAncestorSearch(TreeNode root, TreeNode p, TreeNode q) {
    	int min = Math.min(p.val, q.val);
    	int max = Math.max(p.val, q.val);
    	
    	while (root != null) {
    		if (root.val > max) {
    			root = root.left;
    		} else if (root.val < min) {
    			root = root.right;
    		} else {
    			break;
    		}
    	}
    	return root;
    }
}
