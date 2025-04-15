package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/word-search/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 15, 2025
	- `Answer`: exist
 */
public class WordSearch {
	
	/*
    # Option #1
    - O (m * n * 3^L) (m = row, n = col, L = the length of word)
	 */
    public static boolean exist(char[][] board, String word) {
        
    	int m = board.length;
    	int n = board[0].length; 
    	
    	char startChar = word.charAt(0);
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (board[i][j] == startChar) {
    				if (search(board, word, m, n, i, j)) return true;
    			}
    		}
    	}
    	return false;
    }
    
    // Move and search for a route (Recursive)
    public static boolean search(char[][] board, String word, int m, int n, int x, int y) {
    	
    	// Success
    	if (word.isEmpty()) return true;
    	
    	// Edge Case
    	if (x < 0 || y < 0 || x >= m || y >= n) return false;
    	if (board[x][y] == '0') return false;
    	
    	// Matches
    	char startChar = word.charAt(0);
    	if (board[x][y] != startChar) return false;
    	word = word.substring(1); 
    	
    	// Mark
    	board[x][y] = '0';
    	
    	// Move
    	if (search(board, word, m, n, x + 1, y)) return true;
    	if (search(board, word, m, n, x - 1, y)) return true;
    	if (search(board, word, m, n, x, y + 1)) return true;
    	if (search(board, word, m, n, x, y - 1)) return true;
    	
    	// Unmark
    	board[x][y] = startChar;
    	return false;
    }
}
