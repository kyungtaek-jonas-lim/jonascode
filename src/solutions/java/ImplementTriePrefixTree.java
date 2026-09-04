package solutions.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/implement-trie-prefix-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: Trie / TriePrefixTree
 */
public class ImplementTriePrefixTree {
	
	class TreeNode {
	    Map<Character, TreeNode> children;
	    boolean end;

	    public TreeNode() {
	        this.children = new HashMap<>();
	        this.end = false;
	    }
	}

	
	/*
	# Option #1
	- map
	- O(K), O(1), O(1)
	 */
	class Trie {

	    Map<String, Boolean> map;

	    public Trie() {
	        map = new HashMap<>();
	    }
	    
	    // O(k) (k == the length of the word)
	    public void insert(String word) {
	        char[] chars = word.toCharArray();
	        StringBuilder sb = new StringBuilder();
	        String curr = "";
	        for (char c: chars) {
	            sb.append(c);
	            curr = sb.toString();
	            if (!map.containsKey(curr)) {
	                map.put(curr, false);
	            }
	        }
	        map.put(curr, true);
	    }
	    
	    // O(1)
	    public boolean search(String word) {
	        return map.containsKey(word) ? map.get(word) : false;
	    }
	    
	    // O(1)
	    public boolean startsWith(String prefix) {
	        return map.containsKey(prefix);
	    }
	}

	
	/*
	# Option #2
	- Trie (Prefix Tree)
	- O(L), O(L), O(P) (L = The length of the words, P = the length of the prefixes)
	- ref) https://www.youtube.com/watch?v=oobqoCJlHA0
	 */
	class TriePrefixTree {

	    TreeNode node;

	    public TriePrefixTree() {
	        this.node = new TreeNode();
	    }
	    
	    // O(L) (L = The length of the words)
	    public void insert(String word) {
	        char[] chars = word.toCharArray();
	        TreeNode curr = this.node;
	        for (char c: chars) {
	            if (!curr.children.containsKey(c)) {
	                curr.children.put(c, new TreeNode());
	            }
	            curr = curr.children.get(c);
	        }
	        curr.end = true;
	    }
	    
	    // O(L) (L = The length of the words)
	    public boolean search(String word) {
	        char[] chars = word.toCharArray();
	        TreeNode curr = this.node;
	        for (char c: chars) {
	            if (!curr.children.containsKey(c)) return false;
	            curr = curr.children.get(c);
	        }
	        return curr.end;
	    }
	    
	    // O(P) (P = The length of the prefixes)
	    public boolean startsWith(String prefix) {
	        char[] chars = prefix.toCharArray();
	        TreeNode curr = this.node;
	        for (char c: chars) {
	            if (!curr.children.containsKey(c)) return false;
	            curr = curr.children.get(c);
	        }
	        return true;
	    }
	}


	/*
	# Option #3
	- Trie (Prefix Tree)
	- O(L), O(W·L), O(W·L) (L = length of the word/prefix you're searching for, W = number of stored words that start with the same first letter as your query (worst case))
	- Slower because the root has all the words even though they start with the same alphabet (e.g., 'app' and 'apple' have separate nodes not even partially.)
	- September 4, 2026
	*/
    class Node {
        char val;
        Node next;

        Node(char val, Node next) {
            this.val = val;
            this.next = next;
        }
    }


    class Trie {

        Map<Character, List<Node>> dict;
        // Map<Character, Set<Node>> dict;

        public Trie() {
            dict = new HashMap<>();
        }
        
        public void insert(String word) {
            char[] chars = word.toCharArray();
            final int n = chars.length;
            Node node = null;
            for (int i = n - 1; i >= 0; i--) {
                node = new Node(chars[i], node);
            }
            if (!dict.containsKey(chars[0])) dict.put(chars[0], new ArrayList<>());
            // if (!dict.containsKey(chars[0])) dict.put(chars[0], new HashSet<>());
            dict.get(chars[0]).add(node);
        }
        
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            final int n = chars.length;
            if (!dict.containsKey(chars[0])) return false;
            List<Node> children = dict.get(chars[0]);
            // Set<Node> children = dict.get(chars[0]);
            for (Node node: children) {
                node = node.next;
                boolean success = true;
                for (int i = 1; i < n; i++) {
                    if (node == null || node.val != chars[i]) {
                        success = false;
                        break;
                    }
                    node = node.next;
                }
                if (success && node == null) return true;
            }
            return false;
        }
        
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            final int n = chars.length;
            if (!dict.containsKey(chars[0])) return false;
            List<Node> children = dict.get(chars[0]);
            // Set<Node> children = dict.get(chars[0]);
            for (Node node: children) {
                node = node.next;
                boolean success = true;
                for (int i = 1; i < n; i++) {
                    if (node == null || node.val != chars[i]) {
                        success = false;
                        break;
                    }
                    node = node.next;
                }
                if (success) return true;
            }
            return false;
        }
    }


	/*
	# Option #4
	- Trie (Prefix Tree) - The same as Option #2
	- O(L), O(L), O(P) (L = The length of the words, P = the length of the prefixes)
	- September 4, 2026
	*/
	class Node {
		Map<Character, Node> next;
		boolean end;

		Node() {
			this.next = new HashMap<>();
			this.end = false;
		}
	}


	class Trie {

		Map<Character, Node> root;

		public Trie() {
			root = new HashMap<>();
		}
		
		public void insert(String word) {
			char[] chars = word.toCharArray();
			final int n = chars.length;

			if (!this.root.containsKey(chars[0])) this.root.put(chars[0], new Node());
			Node node = this.root.get(chars[0]);

			for (int i = 1; i < n; i++) {
				if (!node.next.containsKey(chars[i])) node.next.put(chars[i], new Node());
				node = node.next.get(chars[i]);
			}

			node.end = true;
		}
		
		public boolean search(String word) {
			char[] chars = word.toCharArray();
			final int n = chars.length;
			
			if (!this.root.containsKey(chars[0])) return false;
			Node node = this.root.get(chars[0]);

			for (int i = 1; i < n; i++) {
				if (!node.next.containsKey(chars[i])) return false;
				node = node.next.get(chars[i]);
			}
			return node.end;
		}
		
		public boolean startsWith(String prefix) {
			char[] chars = prefix.toCharArray();
			final int n = chars.length;
			
			if (!this.root.containsKey(chars[0])) return false;
			Node node = this.root.get(chars[0]);

			for (int i = 1; i < n; i++) {
				if (!node.next.containsKey(chars[i])) return false;
				node = node.next.get(chars[i]);
			}
			return true;
		}
	}
}
