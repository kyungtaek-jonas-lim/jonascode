package solutions.java;


/*
# Problem
 	- `Link`: https://leetcode.com/problems/validate-binary-search-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: isValidBST
 */
public class ValidateBinarySearchTree {
	
	public class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode() {}
	    TreeNode(int val) { this.val = val; }
	    TreeNode(int val, TreeNode left, TreeNode right) {
	        this.val = val;
	        this.left = left;
	        this.right = right;
	    }
	}
	
	/*
    # Option #1
    - DFS
    - O(n)
	 */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode node, long shouldBeBiggerThanThis, long shouldBeSmallerThanThis) {
        if (node == null) return true;
        if (node.val <= shouldBeBiggerThanThis || node.val >= shouldBeSmallerThanThis) return false;
        return dfs(node.left, shouldBeBiggerThanThis, Math.min(node.val, shouldBeSmallerThanThis))
            && dfs(node.right, Math.max(shouldBeBiggerThanThis, node.val), shouldBeSmallerThanThis);
    }
}
