package solutions.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 8, 2025
	- `Answer`: (serializeBfs, deserializeBfs) / (serializeBfsBetter, deserializeBfsBetter) / (serializeDfsRecursive, deserializeDfsRecursive) / (serializeDfsBetter, deserializeDfsBetter)
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
	    - Bfs Better
	    - O(n)
		- August 25, 2026
		 */
		// Encodes a tree to a single string.
		public String serializeBfsBetter(TreeNode root) {
			if (root == null) return "null";
			
			Deque<Object> deque = new ArrayDeque<>();
			deque.offer(root);
			List<String> result = new ArrayList<>();

			while (!deque.isEmpty()) {
				int n = deque.size();
				for (int i = 0; i < n; i++) {
					Object obj = deque.pollFirst();
					if (obj instanceof Integer) {
						result.add("null");
					} else {
						TreeNode node = (TreeNode)obj;
						result.add("" + node.val);
						deque.offer(node.left == null ? 0 : node.left);
						deque.offer(node.right == null ? 0 : node.right);
					}
				}
			}

			return String.join(",", result);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserializeBfsBetter(String data) {
			if (data.equals("null")) return null;
			
			String[] strings = data.split(",");
			List<TreeNode> list = new ArrayList<>();
			TreeNode root = new TreeNode(Integer.valueOf(strings[0]));
			list.add(root);
			int head = 0;

			for (int i = 1; i < strings.length; i++) {

				TreeNode node = null;
				if (!strings[i].equals("null")) {
					node = new TreeNode(Integer.valueOf(strings[i]));
					list.add(node);
				}
				
				if (i % 2 == 1) {
					list.get(head).left = node;
				} else {
					list.get(head++).right = node;
				}
			}

			return root;
		}


		
		/*
		 * =================================================================
	    # Option #3
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


		
		/*
		 * =================================================================
	    # Option #4
	    - DFS Better
	    - O(n)
		 */
		// Encodes a tree to a single string.
		public String serializeDfsBetter(TreeNode root) {
			if (root == null) return "null";
			String left = serializeDfsBetter(root.left);
			String right = serializeDfsBetter(root.right);
			return root.val + "," + left + "," + right;
		}

		// Decodes your encoded data to tree.
		public TreeNode deserializeDfsBetter(String data) {
			if (data.equals("null")) return null;
			String[] strings = data.split(",");
			int[] i = new int[] {0};
			return dfs(strings, i);
		}

		private TreeNode dfs(String[] strings, int[] i) {
			if (i[0] >= strings.length) return null;
			if (strings[i[0]].equals("null")) {
				i[0]++;
				return null;
			}
			TreeNode node = new TreeNode(Integer.valueOf(strings[i[0]]));
			i[0]++;
			node.left = dfs(strings, i);
			node.right = dfs(strings, i);
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
