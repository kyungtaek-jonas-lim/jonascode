package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/word-search/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: May 23, 2025
	- `Answer`: exist
 */
public class WordSearch {
	
	/*
    # Option #1
    - O (m * n * 3^L) (m = row, n = col, L = the length of word)
	 */
    public boolean exist(char[][] board, String word) {

        int m = board.length, n = board[0].length;
        char[] words = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, words, 0, i, j)) return true;
            }
        }
        return false;
    }

    // Move and search for a route (Recursive)
    private boolean dfs(char[][] board, char[] word, int index, int x, int y) {

        if (index == word.length) return true;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return false;
        if (board[x][y] != word[index]) return false;

        char temp = board[x][y];
        board[x][y] = '0';

        if (dfs(board, word, index + 1, x + 1, y) ||
            dfs(board, word, index + 1, x, y + 1) ||
            dfs(board, word, index + 1, x - 1, y) ||
            dfs(board, word, index + 1, x, y - 1))
            return true;

        board[x][y] = temp;
        return false;
    }
}
