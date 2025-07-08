package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: (serializeBfs, deserializeBfs) / (serializeDfsRecursive, deserializeDfsRecursive)
 */
public class SerializeandDeserializeBinaryTree {
	
	public static class TreeNode {
	    int val;
	    TreeNode left;
	    TreeNode right;
	    TreeNode(int x) { val = x; }
	}
	
	public static class Codec {
		
		
		/*
		 * =================================================================
	    # Option #1
	    - BFS
	    - O(n)
		 */
		final static int INVALID_VAL = 1001;
		
	    // Encodes a tree to a single string.
	    public String serializeBfs(TreeNode root) {
	    	if (root == null) return "N";
	    	
	        StringBuilder result = new StringBuilder();

	        Deque<TreeNode> deque = new ArrayDeque<>();
	        deque.offer(root);
	        
	        while (!deque.isEmpty()) {
	        	TreeNode node = deque.pollFirst();
	        	if (node.val == INVALID_VAL) {
	        		result.append("N,");
	        	} else {
	        		result.append(node.val).append(",");
	        		if (node.left != null) deque.offer(node.left);
	        		else deque.offer(new TreeNode(INVALID_VAL));
	        		
	        		if (node.right != null) deque.offer(node.right);
	        		else deque.offer(new TreeNode(INVALID_VAL));
	        	}
	        }
	        
	        return result.toString();
	    }

	    // Decodes your encoded data to tree.
	    public TreeNode deserializeBfs(String data) {
	        
	    	String[] nodes = data.split(",");
	    	if ("N".equals(nodes[0])) return null;
	    	
	    	Deque<TreeNode> deque = new ArrayDeque<>();
	    	TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
	    	deque.offer(root);
	    	int i = 1;
	    	
	    	while (!deque.isEmpty()) {
	    		TreeNode node = deque.pollFirst();
	    		
	    		String left = nodes[i++];
	    		if (!left.isEmpty() && !"N".equals(left)) {
	    			node.left = new TreeNode(Integer.parseInt(left));
	    			deque.offer(node.left);
	    		}
	    		
	    		String right = nodes[i++];
	    		if (!left.isEmpty() &&!"N".equals(right)) {
	    			node.right = new TreeNode(Integer.parseInt(right));
	    			deque.offer(node.right);
	    		}
	    	}
	    	
	    	return root;
	    }

		
		/*
		 * =================================================================
	    # Option #2
	    - DFS
	    - O(n)
		 */
	    // Encodes a tree to a single string.
	    public String serializeDfsRecursive(TreeNode root) {
	    	StringBuilder result = new StringBuilder();
	    	dfsForSerializing(root, result);
	    	return result.toString();
	    }
	    
	    private void dfsForSerializing(TreeNode node, StringBuilder result) {
	    	if (node == null) {
	    		result.append("N,");
	    		return;
	    	}
	    	result.append(node.val).append(",");
	    	dfsForSerializing(node.left, result);
	    	dfsForSerializing(node.right, result);
	    }
	    
	    static int INDEX = 0;

	    // Decodes your encoded data to tree.
	    public TreeNode deserializeDfsRecursive(String data) {
	    	INDEX = 0;
	    	return dfsForDeserializing(data.split(","));
	    }
	    
	    private TreeNode dfsForDeserializing(String[] nodes) {
	    	if (INDEX >= nodes.length) return null;
	    	String nodeValue = nodes[INDEX++];
	    	if (nodeValue.isEmpty() || nodeValue.equals("N")) return null;
	    	
	    	TreeNode node = new TreeNode(Integer.parseInt(nodeValue));
	    	node.left = dfsForDeserializing(nodes);
	    	node.right = dfsForDeserializing(nodes);
	    	return node;
	    }
	}

	public static void main(String[] args) {
		
		 Codec ser = new Codec();
		 Codec deser = new Codec();
		 
		 // [1,2,3,null,null,4,5]
		 TreeNode root = new TreeNode(1);
		 root.left = new TreeNode(2);
		 root.right = new TreeNode(3);
		 root.right.left = new TreeNode(4);
		 root.right.right = new TreeNode(5);
		 
		 String serialized = ser.serializeBfs(root);
		 System.out.println(serialized);
		 
		 TreeNode ans = deser.deserializeBfs(serialized);
		 System.out.println(ans.val);
		 System.out.println(ans.left.val);
		 System.out.println(ans.right.val);
		 System.out.println(ans.right.left.val);
		 System.out.println(ans.right.right.val);
		 

		 
		 // [1,2,3,null,null,4,5]
		 root = new TreeNode(1);
		 root.left = new TreeNode(2);
		 root.right = new TreeNode(3);
		 root.right.left = new TreeNode(4);
		 root.right.right = new TreeNode(5);
		 
		 serialized = ser.serializeDfsRecursive(root);
		 System.out.println(serialized);
		 
		 ans = deser.deserializeDfsRecursive(serialized);
		 System.out.println(ans.val);
		 System.out.println(ans.left.val);
		 System.out.println(ans.right.val);
		 System.out.println(ans.right.left.val);
		 System.out.println(ans.right.right.val);
		 
		 
		 
		 // [1,2,3,null,null,4,5]
		 root = new TreeNode(1);
		 
		 serialized = ser.serializeDfsRecursive(root);
		 System.out.println(serialized);
		 
		 ans = deser.deserializeDfsRecursive(serialized);
		 System.out.println(ans.val);
	}
}
