package solutions.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-level-order-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 1, 2025
	- `Answer`: levelOrderBfs / levelOrderBfsAdvanced / levelOrderBfsAdvanced
 */
public class BinaryTreeLevelOrderTraversal {

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
	- Bfs
	- O(n)
	*/
    public List<List<Integer>> levelOrderBfs(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<Object[]> deque = new ArrayDeque<>();
        deque.offer(new Object[] {root, 0});

        while (!deque.isEmpty()) {
            Object[] item = deque.pollFirst();
            if (item[0] == null) continue;

            TreeNode node = (TreeNode) item[0];
            int index = (int) item[1];

            if (result.size() <= index) result.add(new ArrayList<>());
            result.get(index).add(node.val);

            deque.offer(new Object[] {node.left, index + 1} );
            deque.offer(new Object[] {node.right, index + 1} );
        }

        return result;
    }
    

	/*
	# Option #2
	- Bfs + Variables
	- O(n)
	*/
    public List<List<Integer>> levelOrderBfsAdvanced(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        int index = 0, cnt = 1;

        while (!deque.isEmpty()) {

            result.add(new ArrayList<>());
            int temp = cnt;
            cnt = 0;

            for (int i = 0; i < temp; i++) {
                TreeNode node = deque.pollFirst();
                result.get(index).add(node.val);
                if (node.left != null) {
                    deque.offer(node.left);
                    cnt++;
                }
                if (node.right != null) {
                    deque.offer(node.right);
                    cnt++;
                }
            }

            index++;
        }

        return result;
    }
    

	/*
	# Option #3
	- Recursive Dfs
	- O(N)
	*/
    public List<List<Integer>> levelOrderRecursiveDfs(TreeNode root) {   
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        dfs(result, root, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, TreeNode node, int depth) {
        if (node == null) return;
        if (result.size() <= depth) result.add(new ArrayList<>());
        
        result.get(depth).add(node.val);
        dfs(result, node.left, depth + 1);
        dfs(result, node.right, depth + 1);
    }

}
