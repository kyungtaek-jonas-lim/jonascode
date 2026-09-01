package solutions.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: buildTreeSimple / buildTreeUsingInorderRangeAndIncreasingPreorderIndex
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {
	
	public static class TreeNode {
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
	

	static class Solution {
	
		/*
		# Option 1
		- DFS Recurisve
		- O(n^2)
		- https://www.youtube.com/watch?v=ihj4IQGZ2zc
		 */
		public TreeNode buildTreeSimple(int[] preorder, int[] inorder) {
			if (preorder.length == 0 || inorder.length == 0) return null;
			TreeNode root = new TreeNode(preorder[0]);
			int mid = 0;
			for (int i = 0; i < inorder.length; i++) {
				if (preorder[0] == inorder[i]) {
					mid = i;
					break;
				}
			}
			
			root.left = buildTreeSimple(Arrays.copyOfRange(preorder, 1, mid + 1), Arrays.copyOfRange(inorder, 0, mid));
			root.right = buildTreeSimple(Arrays.copyOfRange(preorder, mid + 1, preorder.length), Arrays.copyOfRange(inorder, mid + 1, inorder.length));
			return root;
		}

		
		/*
		# Option 2
		- DFS Recurisve Using Inorder Range & Increasing Preorder Index
		- O(n)
		*/
		private int currentPreorderIndex = 0;

		public TreeNode buildTreeUsingInorderRangeAndIncreasingPreorderIndex(int[] preorder, int[] inorder) {
			
			final int n = preorder.length;
			Map<Integer, Integer> inorderIndexMap = new HashMap<>();
			for (int i = 0; i < n; i++) {
				inorderIndexMap.put(inorder[i], i);
			}

			return dfs(preorder, inorderIndexMap, 0, n);
		}

		private TreeNode dfs(int[] preorder, Map<Integer, Integer> inorder, int left, int right) {
			if (left >= right) return null;
			
			TreeNode node = new TreeNode(preorder[this.currentPreorderIndex++]);
			final int mid = inorder.get(node.val);
			
			node.left = dfs(preorder, inorder, left, mid);
			node.right = dfs(preorder, inorder, mid + 1, right);
			return node;
		}
	    
	}
    
    public static void main(String[] args) {
    	Solution sol = new Solution();
    	sol.buildTreeSimple(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7});
	}
}
