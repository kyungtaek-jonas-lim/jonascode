package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: kthSmallestDfsIterative / kthSmallestDfsRecursive
 */
public class KthSmallestElementinaBST {
	
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
    - DFS Iterative - Find the smallest and gets bigger
    - O(H + k) (H = the height of the tree)
	 */
    public int kthSmallestDfsIterative(TreeNode root, int k) {

        Deque<TreeNode> deque = new ArrayDeque<>();
        
        TreeNode curr = root;
        int cnt = 1;
        while (curr != null || !deque.isEmpty()) {
            
            while (curr != null) {
                deque.offer(curr);
                curr = curr.left;
            }

            curr = deque.pollLast();
            if (cnt == k) return curr.val;
            cnt++;
            
            curr = curr.right;
        }

        return -1;
    }

	

    /*
    # Option #2
    - DFS Recursive - Find the smallest and gets bigger
    - O(H + k) (H = the height of the tree)
     */
    public int kthSmallestDfsRecursive(TreeNode root, int k) {
        int[] result = new int[] {-1};
        dfs(root, k, 0, result);
        return result[0];
    }
    
    private int dfs(TreeNode node, int k, int curr, int[] result) {
        if (node == null) return curr;
        int res = dfs(node.left, k, curr, result) + 1;
        if (res == 0) return -1;
        if (res == k) {
            result[0] = node.val;
            return -1;
        }

        res = dfs(node.right, k, res, result);
        if (res == -1) return -1;
        if (res == k) {
            result[0] = node.val;
            return -1;
        }
        return res;
    }
}
