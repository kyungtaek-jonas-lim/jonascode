package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/invert-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 1, 2025
	- `Answer`: invertTreeBfs / invertTreeRecursiveDfs / invertTreeIterativeDfs
 */
public class InvertBinaryTree {
	

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
    - BFS(FIFO)
    - O(n)
	 */
    public TreeNode invertTreeBfs(TreeNode root) {
    	
        if (root == null) return null;
        
    	Deque<TreeNode> deque = new ArrayDeque<>();
    	deque.offer(root);
    	
    	while (!deque.isEmpty()) {
    		TreeNode node = deque.pollFirst();
    		if (node == null) continue;
    		
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            if (node.left != null) deque.offer(node.left);
            if (node.right != null) deque.offer(node.right);
    	}
    	return root;
	}
    

	/*
    # Option #2
    - Recursive DFS(FILO)
    - O(n)
	 */
    public TreeNode invertTreeRecursiveDfs(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTreeRecursiveDfs(root.right);
        root.right = invertTreeRecursiveDfs(temp);
        return root;
    }

	/*
    # Option #3
    - Iterative(/Stack-based) DFS(FILO)
    - O(n)
	 */
    public TreeNode invertTreeIterativeDfs(TreeNode root) {
    	
        if (root == null) return null;
        
    	Deque<TreeNode> deque = new ArrayDeque<>();
    	deque.offer(root);
    	
    	while (!deque.isEmpty()) {
    		TreeNode node = deque.pollLast();
    		if (node == null) continue;
    		
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            
            if (node.left != null) deque.offer(node.left);
            if (node.right != null) deque.offer(node.right);
    	}
    	return root;
	}
}
