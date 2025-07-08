package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: buildTree
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
	    - O(n)
		 */
	    private int[] preorder = null;
	    private Map<Integer, Integer> preIndex = new HashMap<>(), inIndex = new HashMap<>();
	    private int n = -1;
	
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	        
	        this.n = preorder.length;
	        this.preorder = preorder;
	
	        for (int i = 0; i < n; i++) {
	            preIndex.put(preorder[i], i);
	            inIndex.put(inorder[i], i);
	        }
	
	        return dfs(0, n, 0, n);
	    }
	
	    public TreeNode dfs(int pStart, int pEnd, int iStart, int iEnd) {
	        if (pStart < 0 || iStart < 0 || pEnd > n || iEnd > n) return null;
	        if (pStart >= pEnd || iStart >= iEnd) return null;
	        
	        int nodeValue = preorder[pStart];
	        TreeNode node = new TreeNode(nodeValue);
	        
	        
	        int iMid = inIndex.get(nodeValue);
	        
	        
	        // No need to do it cause now we know the left Size
//	        int rightStart = pStart;
//	        for (int i = pStart + 1; i < pEnd; i++) {
//	            int index = inIndex.get(preorder[i]);
//	            if (index > iMid && index < iEnd) {
//	                rightStart = i;
//	                break;
//	            }
//	        }
	        int leftSize = iMid - iStart;
	        int rightStart = pStart + 1 + leftSize;
	
	        node.left = dfs(pStart + 1, pEnd, iStart, iMid);
	        node.right = dfs(rightStart, pEnd, iMid + 1, iEnd);
	        return node;
	    }
	    
	}
    
    public static void main(String[] args) {
    	Solution sol = new Solution();
    	sol.buildTree(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7});
	}
}
