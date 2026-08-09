package solutions.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/pacific-atlantic-water-flow/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 25
	- `Answer`: pacificAtlantic / pacificAtlanticAdvanced / pacificAtlanticDfs / pacificAtlanticBfs
 */
public class PacificAtlanticWaterFlow {

	/*
	# Option #1
	- Recursive DFS
    - O((m * n) ^ 2)
    - Start from each grid cell
	 */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        
        List<List<Integer>> result = new ArrayList<>();
        int m = heights.length, n = heights[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n];
                boolean[] oceans = new boolean[] {false, false};
                if (dfs(heights, i, j, Integer.MAX_VALUE, visited, oceans)) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    public boolean dfs(int[][] heights, int x, int y, int prev, boolean[][] visited, boolean[] oceans) {
        int m = heights.length, n = heights[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n) return false;

        if (visited[x][y]) return false;
        if (heights[x][y] > prev) return false;

        visited[x][y] = true;

        if (x == 0 || y == 0) oceans[0] = true; // pacific
        if (x == m - 1 || y == n - 1) oceans[1] = true; // atlantic
        if (oceans[0] && oceans[1]) return true;

        int[][] move = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] d: move) {
            if (dfs(heights, x + d[0], y + d[1], heights[x][y], visited, oceans)) return true;
        }

        return false;
    }
    
    

    /*
    # Option #2
    - Recursive DFS
    - O(m * n)
    - Start from ocean (Move from one ocean to the other ocean if the previous height is not taller)
     */
    public List<List<Integer>> pacificAtlanticAdvanced(int[][] heights) {
        
        List<List<Integer>> result = new ArrayList<>();
        int m = heights.length, n = heights[0].length;

        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];

        // From Pacific ocean to available-to-be-toched grid cells (Top & Left)
        for (int j = 0; j < n; j++) { // Top
            dfs(heights, 0, j, Integer.MIN_VALUE, pac);
        }
        for (int i = 0; i < m; i++) { // Left
            dfs(heights, i, 0, Integer.MIN_VALUE, pac);
        }

        // From Pacific ocean to available-to-be-toched grid cells (Bottom & Right)
        for (int j = 0; j < n; j++) { // Botoom
            dfs(heights, m - 1, j, Integer.MIN_VALUE, atl);
        }
        for (int i = 0; i < m; i++) { // Right
            dfs(heights, i, n - 1, Integer.MIN_VALUE, atl);
        }

        // Get Duplicate grid cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, int x, int y, int prev, boolean[][] visited) {
        int m = heights.length, n = heights[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n) return;
        if (heights[x][y] < prev) return;

        if (visited[x][y]) return;
        visited[x][y] = true;

        int[][] move = new int[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int[] d: move) {
            dfs(heights, x + d[0], y + d[1], heights[x][y], visited);
        }
    }



    /*
    # Option #3
    - O(m * n)
    - DFS - Basically, the same as Option #2
    - August 9, 2026
    */
    public List<List<Integer>> pacificAtlanticDfs(int[][] heights) {
        
        final int m = heights.length, n = heights[0].length;
        boolean[][] memo = new boolean[m][n];

        // Pacific
        for (int i = 0; i < m; i++) dfs(heights, memo, i, 0, 0);
        for (int j = 0; j < n; j++) dfs(heights, memo, 0, j, 0);
        boolean[][] pacific = memo;

        // Atlantic
        memo = new boolean[m][n];
        for (int i = 0; i < m; i++) dfs(heights, memo, i, n - 1, 0);
        for (int j = 0; j < n; j++) dfs(heights, memo, m - 1, j, 0);

        // Result
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && memo[i][j]) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    item.add(j);
                    result.add(item);
                }
            }
        }
        return result;
    }

    private void dfs(int[][] heights, boolean[][] memo, int x, int y, int prev) {
        if (x < 0 || y < 0 || x >= heights.length || y >= heights[0].length) return;
        if (memo[x][y] || heights[x][y] < prev) return;

        memo[x][y] = true;
        dfs(heights, memo, x + 1, y, heights[x][y]);
        dfs(heights, memo, x - 1, y, heights[x][y]);
        dfs(heights, memo, x, y + 1, heights[x][y]);
        dfs(heights, memo, x, y - 1, heights[x][y]);
    }

    

    /*
    # Option #4
    - O(m * n)
    - BFS - Basically, the same as Option #2, but BFS
    - August 9, 2026
    */
    public List<List<Integer>> pacificAtlanticBfs(int[][] heights) {
        final int m = heights.length, n = heights[0].length;
        final Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] memo = new boolean[m][n];

        // Pacific
        for (int i = 0; i < m; i++) {
            deque.add(new int[] {i, 0, 0});
            bfs(heights, memo, deque);
        }
        for (int j = 0; j < n; j++) {
            deque.add(new int[] {0, j, 0});
            bfs(heights, memo, deque);
        }
        boolean[][] pacific = memo;

        // Atlantic
        deque.clear();
        memo = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            deque.add(new int[] {i, n - 1, 0});
            bfs(heights, memo, deque);
        }
        for (int j = 0; j < n; j++) {
            deque.add(new int[] {m - 1, j, 0});
            bfs(heights, memo, deque);
        }

        // Result
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && memo[i][j]) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    item.add(j);
                    result.add(item);
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, boolean[][] memo, Deque<int[]> deque) {
        
        final int m = heights.length, n = heights[0].length;

        while (!deque.isEmpty()) {
            int[] item = deque.pollFirst();
            int x = item[0], y = item[1], prev = item[2];
            if (x < 0 || y < 0 || x >= m || y >= n) continue;
            if (memo[x][y] || heights[x][y] < prev) continue;

            memo[x][y] = true;
            deque.offer(new int[] {x + 1, y, heights[x][y]});
            deque.offer(new int[] {x - 1, y, heights[x][y]});
            deque.offer(new int[] {x, y + 1, heights[x][y]});
            deque.offer(new int[] {x, y - 1, heights[x][y]});
        }
    }
}
