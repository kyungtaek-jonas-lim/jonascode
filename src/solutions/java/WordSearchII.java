package solutions.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
# Problem
 	- `Link`: https://leetcode.com/problems/word-search-ii/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: Solution
 */
public class WordSearchII {
	
	class TreeNode {
	    Map<Character, TreeNode> children;
	    String word;

	    public TreeNode() {
	        this.children = new HashMap<>();
	        this.word = null;
	    }
	}
	
	/*
	# Option #1
	- Prefix Node (Trie)
	- O((W * L) + m * n * 4^L) (W = The length of words list, L = the longest length of all the words)
	 */
	class Solution {

	    char[][] board;
	    int m, n;

	    public List<String> findWords(char[][] board, String[] words) {
	        this.board = board;
	        this.m = board.length;
	        this.n = board[0].length;

	        // Insert
	        TreeNode root = new TreeNode();
	        for (String word: words) {
	            char[] chars = word.toCharArray();
	            TreeNode curr = root;
	            for (char c : chars) {
	                if (!curr.children.containsKey(c)) {
	                    curr.children.put(c, new TreeNode());
	                }
	                curr = curr.children.get(c);
	            }
	            curr.word = word;
	        }

	        // Search
	        List<String> result = new ArrayList<>();
	        for (int i = 0; i < this.m; i++) {
	            for (int j = 0; j < this.n; j++) {
	                if (root.children.containsKey(board[i][j])) {
	                    dfs(root, i, j, result);
	                }
	            }
	        }
	        return result;
	    }

	    private void dfs(TreeNode curr, int x, int y, List<String> result) {
	        if (x < 0 || y < 0 || x >= this.m || y >= this.n || this.board[x][y] == '#') return;

	        char c = this.board[x][y];
	        if (!curr.children.containsKey(c)) return;
	        curr = curr.children.get(c);

	        if (curr.word != null) {
	            result.add(curr.word);
	            curr.word = null;
	        } 

	        this.board[x][y] = '#';

	        dfs(curr, x + 1, y, result);
	        dfs(curr, x - 1, y, result);
	        dfs(curr, x, y + 1, result);
	        dfs(curr, x, y - 1, result);
	        
	        this.board[x][y] = c;
	    }
	}
}
