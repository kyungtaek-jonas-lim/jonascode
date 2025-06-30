package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/maximum-depth-of-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 30
	- `Answer`: maxDepthBfs / maxDepthRecursiveDfs / maxDepthIterativeDfs
 */
public class MaximumDepthofBinaryTree {
	
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
    public int maxDepthBfs(TreeNode root) {
        if (root == null) return 0;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);

        int result = 0;
        while (!deque.isEmpty()) {
            
            int cnt = deque.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = deque.poll();
                
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
            }

            result++;
        }

        return result;
    }
	
    
	/*
    # Option #2
    - Recursive DFS(FILO)
    - O(n)
	 */
    public int maxDepthRecursiveDfs(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepthRecursiveDfs(root.left), maxDepthRecursiveDfs(root.right));
    }
    
    
    /*
    # Option #3
    - Iterative(/Stack-based) DFS(FILO)
    - O(n)
     */
    public int maxDepth(TreeNode root) {
        Deque<Object[]> deque = new ArrayDeque<>();
        deque.offer(new Object[] {root, 1 });
        
        int result = 0;

        while (!deque.isEmpty()) {
            Object[] item = deque.pollLast();
            if (item[0] == null) continue;
            
            TreeNode node = (TreeNode) item[0];
            int depth = (int) item[1];
            result = Math.max(result, depth);
            
            deque.offer(new Object[] { node.left, depth + 1});
            deque.offer(new Object[] { node.right, depth + 1});
        }

        return result;
    }
    
}
