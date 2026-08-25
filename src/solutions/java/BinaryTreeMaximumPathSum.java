package solutions.java;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-maximum-path-sum/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 1, 2025
	- `Answer`: maxPathSum / maxPathSumSimpler / maxPathSumWorstButWorks
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

	/*
    # Option #3
    - DFS - Worst but it works
    - O(n)
    - August 22, 2026
	 */
    private int max = -1001;

    public int maxPathSumWorstButWorks(TreeNode root) {
        dfs(root);
        return max;
    }
    private int dfs(TreeNode node) {
        if (node == null) return -1001;
        int leftVal = dfs(node.left), rightVal = dfs(node.right);

        int result = node.val;
        
        if (leftVal >= 0 && rightVal >= 0) {
            this.max = Math.max(max, node.val + leftVal + rightVal);
            result = leftVal > rightVal ? node.val + leftVal : node.val + rightVal;
        } else if (leftVal >= 0) result = node.val + leftVal;
        else if (rightVal >= 0) result = node.val + rightVal;

        this.max = Math.max(result, this.max);
        return result;
    }
}
