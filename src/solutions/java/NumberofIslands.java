package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/number-of-islands/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 25
	- `Answer`: numIslands
 */
public class NumberofIslands {
	
	/*
    # Option #1
    - O(m × n)
	 */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int result = 0;
        int[][] move = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, move);
                    result++;
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int x, int y, int[][] move) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != '1') return;
        grid[x][y] = '#';
        for (int[] d: move) {
            dfs(grid, x + d[0], y + d[1], move);
        }
    }
}
