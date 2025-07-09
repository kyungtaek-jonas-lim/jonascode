package solutions.java;

import java.util.HashMap;
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
}
