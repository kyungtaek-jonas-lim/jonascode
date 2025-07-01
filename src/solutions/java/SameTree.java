package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/same-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 1
	- `Answer`: isSameTreeBfs / isSameTreeRecursiveDfs / isSameTreeIterativeDfs
 */
public class SameTree {
	
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
    public boolean isSameTreeBfs(TreeNode p, TreeNode q) {
    	
    	Deque<TreeNode[]> deque = new ArrayDeque<>();
    	deque.offer(new TreeNode[] {p, q} );
    	
    	while (!deque.isEmpty()) {
    		TreeNode[] item = deque.pollFirst();
    		TreeNode n1 = item[0], n2 = item[1];
    		
    		if (n1 == null && n2 == null) continue;
    		if (n1 == null || n2 == null || n1.val != n2.val) return false;
    		deque.offer(new TreeNode[] {n1.left, n2.left} );
    		deque.offer(new TreeNode[] {n1.right, n2.right} );
    	}
    	return true;
    }
	
	
	/*
    # Option #2
    - Recursive DFS(FILO)
    - O(n)
	 */
    public boolean isSameTreeRecursiveDfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTreeRecursiveDfs(p.left, q.left) && isSameTreeRecursiveDfs(p.right, q.right);
    }

	
	/*
    # Option #3
    - Iterative(/Stack-based) DFS(FILO)
    - O(n)
	 */
    public boolean isSameTreeIterativeDfs(TreeNode p, TreeNode q) {

    	Deque<TreeNode[]> deque = new ArrayDeque<>();
    	deque.offer(new TreeNode[] {p, q} );
    	
    	while (!deque.isEmpty()) {
    		TreeNode[] item = deque.pollLast();
    		TreeNode n1 = item[0], n2 = item[1];
    		
    		if (n1 == null && n2 == null) continue;
    		if (n1 == null || n2 == null || n1.val != n2.val) return false;
    		deque.offer(new TreeNode[] {n1.left, n2.left} );
    		deque.offer(new TreeNode[] {n1.right, n2.right} );
    	}
    	return true;
    }
}
