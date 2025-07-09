package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: WordDictionary
 */
public class DesignAddandSearchWordsDataStructure {

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
	- Prefix Tree
	- addWord: O(L) (L = The length of the word)
	- search: Worst O(26^L), Average O(L) (L = The length of the word)
	 */
	class WordDictionary {

	    TreeNode node;

	    public WordDictionary() {
	        node = new TreeNode();
	    }
	    
	    // O(L) (L = The length of the word)
	    public void addWord(String word) {
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
	    
	    // Worst O(26^L), Average O(L) (L = The length of the word)
	    public boolean search(String word) {
	        char[] chars = word.toCharArray();
	        return dfs(chars, 0, this.node);
	    }

	    private boolean dfs(char[] chars, int index, TreeNode node) {
	        int n = chars.length;
	        TreeNode curr = node;
	        for (int i = index; i < n; i++) {
	            char c = chars[i];
	            if (c == '.') {
	                for (TreeNode child: curr.children.values()) {
	                    if (dfs(chars, i + 1, child)) return true;
                    }
                    return false;
	            }
	            if (!curr.children.containsKey(c)) {
                    return false;
	            }
	            curr = curr.children.get(c);
	        }
	        return curr.end;
	    }
	}
}
