package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: kthSmallest
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
    public int kthSmallest(TreeNode root, int k) {

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
}
