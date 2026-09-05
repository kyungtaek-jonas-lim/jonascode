package solutions.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: WordDictionaryDfs / WordDictionaryBfs / WordDictionaryDfs2
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
	- Prefix Tree (DFS)
	- addWord: O(L)                          (L = length of the word)
	- search:
		- Best case  (no '.'):  O(L)
		- Worst case (all '.'): O(26^L)       (26 = alphabet size)
	- Space:
		- Trie storage: O(N × L)              (N = number of words stored)
		- search recursion stack: O(L)         ← DFS backtracks, only 1 path alive at a time
	*/
	class WordDictionaryDfs {

	    TreeNode node;

	    public WordDictionaryDfs() {
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

	/*
	# Option #2
	- Prefix Tree (BFS)
	- addWord: O(L)                          (L = length of the word)
	- search:
		- Best case  (no '.'):  O(L)
		- Worst case (all '.'): O(26^L)
	- Space:
		- Trie storage: O(N × L)              (N = number of words stored)
		- search queue: O(26^L)  ← WORSE than Option #1!
								all nodes at current depth stay alive in the queue at once
	- Date:
		- September 5, 2026
	*/
	class WordDictionaryBfs {

		Map<Character, TreeNode> root;

		public WordDictionaryBfs() {
			this.root = new HashMap<>();
		}
		
		public void addWord(String word) {
			char[] chars = word.toCharArray();
			if (!this.root.containsKey(chars[0])) this.root.put(chars[0], new TreeNode());
			TreeNode node = this.root.get(chars[0]);
			final int n = chars.length;
			for (int i = 1; i < n; i++) {
				if (!node.children.containsKey(chars[i])) node.children.put(chars[i], new TreeNode());
				node = node.children.get(chars[i]);
			}
			node.end = true;
		}
		
		public boolean search(String word) {
			char[] chars = word.toCharArray();
			final int n = chars.length;
			Deque<Object[]> deque = new ArrayDeque<>();
			if (chars[0] == '.') {
				if (this.root.isEmpty()) return false;
				for (TreeNode nd: this.root.values()) deque.offer(new Object[] {0, nd});
			} else {
				if (!this.root.containsKey(chars[0])) return false;
				deque.offer(new Object[] {0, this.root.get(chars[0])});
			}


			while (!deque.isEmpty()) {
				Object[] obj = deque.pollFirst();
				int depth = (int)obj[0];
				TreeNode node = (TreeNode)obj[1];
				if (depth == n - 1) {
					if (node.end) return true;
					continue;
				}
				depth++;
				
				if (chars[depth] == '.') {
					if (node.children.isEmpty()) continue;
					for (TreeNode nd: node.children.values()) deque.offer(new Object[] {depth, nd});
				} else if (!node.children.containsKey(chars[depth])) continue;
				else {
					deque.offer(new Object[] {depth, node.children.get(chars[depth])});
				}
			}
			return false;
		}
	}

	/*
	# Option #3
	- Prefix Tree (DFS)
	- addWord: O(L)                          (L = length of the word)
	- search:
		- Best case  (no '.'):  O(L)
		- Worst case (all '.'): O(26^L)       (26 = alphabet size)
	- Space:
		- Trie storage: O(N × L)              (N = number of words stored)
		- search recursion stack: O(L)         ← DFS backtracks, only 1 path alive at a time
	- Date:
		- September 5, 2026
	*/
	class WordDictionaryDfs2 {

		Map<Character, TreeNode> root;

		public WordDictionaryDfs2() {
			this.root = new HashMap<>();
		}
		
		public void addWord(String word) {
			char[] chars = word.toCharArray();
			if (!this.root.containsKey(chars[0])) this.root.put(chars[0], new TreeNode());
			TreeNode node = this.root.get(chars[0]);
			final int n = chars.length;
			for (int i = 1; i < n; i++) {
				if (!node.children.containsKey(chars[i])) node.children.put(chars[i], new TreeNode());
				node = node.children.get(chars[i]);
			}
			node.end = true;
		}
		
		public boolean search(String word) {
			char[] chars = word.toCharArray();
			final int n = chars.length;
			if (chars[0] == '.') {
				for (TreeNode nd: this.root.values()) {
					if (dfs(chars, 0, nd)) return true;
				}
				return false;
			} else {
				if (!this.root.containsKey(chars[0])) return false;
				return dfs(chars, 0, this.root.get(chars[0]));
			}
		}

		private boolean dfs(char[] chars, int i, TreeNode node) {
			if (i++ == chars.length - 1) return node.end;
			if (chars[i] == '.') {
				for (TreeNode nd: node.children.values()) {
					if (dfs(chars, i, nd)) return true;
				}
				return false;
			} else {
				if (!node.children.containsKey(chars[i])) return false;
				return dfs(chars, i, node.children.get(chars[i]));
			}
		}
	}
}
