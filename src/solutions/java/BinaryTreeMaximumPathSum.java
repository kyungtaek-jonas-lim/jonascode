package solutions.java;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-maximum-path-sum/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 1
	- `Answer`: maxPathSum / maxPathSumSimpler
 */
public class BinaryTreeMaximumPathSum {

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
    - DFS - Complicated
    - O(n)
	 */
    public int maxPathSum(TreeNode root) {
        int[] result = new int[] {root.val};
        dfs(root, result);
        return result[0];
    }

    public int dfs(TreeNode node, int[] max) {
        if (node == null) return 0;
        int current = node.val, left = dfs(node.left, max), right = dfs(node.right, max);
        int result = Math.max(current, Math.max(current + left, current + right));
        max[0] = Math.max(Math.max(max[0], current + left + right), result);
        return result;
    }
    
    
    /*
    # Option #2
    - DFS - Simpler
    - O(n)
     */
    public int maxPathSumSimpler(TreeNode root) {
        int[] result = new int[] {Integer.MIN_VALUE};
        dfsSimpler(root, result);
        return result[0];
    }

    public int dfsSimpler(TreeNode node, int[] max) {
        if (node == null) return 0;

        int current = node.val, left = Math.max(0, dfsSimpler(node.left, max)), right = Math.max(0, dfsSimpler(node.right, max));
        max[0] = Math.max(max[0], current + left + right);
        return current + Math.max(left, right);
    }
}
