
/*
# Problem
	- `Link`: https://leetcode.com/problems/number-of-islands/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 9, 2025
	- `Answer`: numIslandsDfs / numIslandsBfs
*/

/*
# Option #1
- DFS
- O(m × n)
*/
function numIslandsDfs(grid: string[][]): number {
    const m: number = grid.length, n: number = grid[0].length;

    let result: number = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === "1") {
                dfs(grid, i, j);
                result++;
            }
        }
    }
    return result;
};

function dfs(grid: string[][], x: number, y: number): void {
    const m: number = grid.length, n: number = grid[0].length;
    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] !== '1') return;

    // Mark visited cell
    const curr = grid[x][y];
    grid[x][y] = '#';

    dfs(grid, x + 1, y);
    dfs(grid, x - 1, y);
    dfs(grid, x, y + 1);
    dfs(grid, x, y - 1);
}


/*
# Option #2
- BFS
- O(m × n)
*/
function numIslandsBfs(grid: string[][]): number {
    
    const m: number = grid.length, n: number = grid[0].length;
    const deque: number[][] = [];

    let result: number = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === "1") {
                result++;
                deque.push([i, j]);

                while (deque.length > 0) {
                    const location: number[] = deque.shift()!; // Could be slower in TypeScript
                    const x: number = location[0], y: number = location[1];

                    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] !== '1') continue;
                    grid[x][y] = '#';
                    
                    deque.push([x + 1, y]);
                    deque.push([x - 1, y]);
                    deque.push([x, y + 1]);
                    deque.push([x, y - 1]);
                    
                }
            }
        }
    }
    return result;
};