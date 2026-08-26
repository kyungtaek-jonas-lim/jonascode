package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/subtree-of-another-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: isSubtreeBfs / isSubtreeBfsAndDfs / isSubtreeDfs
 */
public class SubtreeofAnotherTree {
	
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
    - BFS
    - O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
	 */
    public boolean isSubtreeBfs(TreeNode root, TreeNode subRoot) {

        if (root == null && subRoot == null) return true;
        else if (root == null || subRoot == null) return false;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while (!deque.isEmpty()) {

            // Pop
            TreeNode node = deque.pollFirst();
            
            // Compare
            Deque<TreeNode[]> temp = new ArrayDeque<>();
            temp.offer(new TreeNode[] {node, subRoot});

            boolean success = true;
            while (!temp.isEmpty()) {
                TreeNode[] ns = temp.pollFirst();
                TreeNode n1 = ns[0], n2 = ns[1];
                if (n1 == null && n2 == null) continue;
                if ((n1 == null || n2 == null) || (n1.val != n2.val)) {
                    success = false;
                    break;
                }
                temp.offer(new TreeNode[] {n1.left, n2.left});
                temp.offer(new TreeNode[] {n1.right, n2.right});
            }
            if (success) return true;


            // Put left & right nodes
            if (node.left != null) deque.offer(node.left);
            if (node.right != null) deque.offer(node.right);
        }
        return false;
    }
	
	
	/*
    # Option #2
    - DFS Recursive
    - O(m * n) (m = the number of root nodes, n = the number of subRoot nodes) 
	 */
    public boolean isSubtreeBfsAndDfs(TreeNode root, TreeNode subRoot) {

        if (root == null && subRoot == null) return true;
        else if (root == null || subRoot == null) return false;
        
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);

        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            if (dfs(node, subRoot)) return true;
            if (node.left != null) deque.offer(node.left);
            if (node.right != null) deque.offer(node.right);
        }
        return false;
    }

    private boolean dfs(TreeNode node, TreeNode subNode) {
        if (node == null && subNode == null) return true;
        if (node == null || subNode == null) return false;
        if (node.val != subNode.val) return false;
        return dfs(node.left, subNode.left) && dfs(node.right, subNode.right);
    }
	
	
	/*
    # Option #3
    - DFS Recursive
    - O(m * n) (m = the number of root nodes, n = the number of subRoot nodes) 
    - August 26, 2026
	 */
    public boolean isSubtreeDfs(TreeNode root, TreeNode subRoot) {
        return dfs2(root, subRoot);
    }

    private boolean dfs2(TreeNode node, TreeNode subRoot) {
        if (node == null) return false;
        if (isSame(node, subRoot)) return true;
        return dfs2(node.left, subRoot) || dfs2(node.right, subRoot);
    }

    private boolean isSame(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        if (n1.val == n2.val) {
            if (isSame(n1.left, n2.left) && isSame(n1.right, n2.right)) return true;
        }
        return false;
    }
    
}
