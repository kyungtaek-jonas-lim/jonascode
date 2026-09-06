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
	- `Answer`: findWords / findWordsTrie
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
	

	/*
	# Option #2
	- Prefix Node (Trie)
	- O((W * L) + m * n * 4^L) (W = The length of words list, L = the longest length of all the words)
	- September 6, 2026
	 */
    Map<Character, TreeNode> root;
    List<String> result;

    WordSearchII() {
        this.root = new HashMap<>();
        this.result = new ArrayList<>();
    }

    public List<String> findWordsTrie(char[][] board, String[] words) {

        for (String word: words) addWord(word);

        final int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.root.containsKey(board[i][j])) {
                    TreeNode node = this.root.get(board[i][j]);
                    if (node.word != null) {
                        this.result.add(node.word);
                        node.word = null;
                    }
                    char temp = board[i][j];
                    board[i][j] = '#';
                    dfs(board, i + 1, j, node);
                    dfs(board, i - 1, j, node);
                    dfs(board, i, j + 1, node);
                    dfs(board, i, j - 1, node);
                    board[i][j] = temp;
                }
            }
        }

        return this.result;
    }

    private void addWord(String word) {
        char[] chars = word.toCharArray();
        final int n = chars.length;
        if (!this.root.containsKey(chars[0])) this.root.put(chars[0], new TreeNode());
        TreeNode node = this.root.get(chars[0]);
        for (int i = 1; i < n; i++) {
            if (!node.children.containsKey(chars[i])) node.children.put(chars[i], new TreeNode());
            node = node.children.get(chars[i]);
        }
        node.word = word;
    }

    private void dfs(char[][] board, int x, int y, TreeNode node) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || !node.children.containsKey(board[x][y])) return;

        node = node.children.get(board[x][y]);
        if (node.word != null) {
            this.result.add(node.word);
            node.word = null;
        }
        char temp = board[x][y];
        board[x][y] = '#';
        dfs(board, x + 1, y, node);
        dfs(board, x - 1, y, node);
        dfs(board, x, y + 1, node);
        dfs(board, x, y - 1, node);
        board[x][y] = temp;
    }
}
