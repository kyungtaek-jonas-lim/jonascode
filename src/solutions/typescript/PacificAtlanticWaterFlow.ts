
/*
# Problem
	- `Link`: https://leetcode.com/problems/pacific-atlantic-water-flow/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 10, 2025
	- `Answer`: pacificAtlantic / pacificAtlanticAdvanced
*/

/*
# Option #1
- Recursive DFS
- O((m * n) ^ 2)
- Start from each grid cell
*/
function pacificAtlantic(heights: number[][]): number[][] {
    
    const m: number = heights.length, n: number = heights[0].length;
    const move: number[][] = [[1, 0], [-1, 0], [0, 1], [0, -1]];
    const result: number[][] = [];

    function dfs(x: number, y: number, prev: number, ocean: boolean[], visited: boolean[][]): boolean {
        if (x < 0 || y < 0 || x >= m || y >= n) return false;
        if (prev < heights[x][y]) return false;
        if (visited[x][y]) return false;
        visited[x][y] = true;

        if (x === 0 || y === 0) ocean[0] = true;
        if (x === m - 1 || y === n - 1) ocean[1] = true;
        if (ocean[0] && ocean[1]) return true;

        let res: boolean = false;
        for (const d of move) {
            if (dfs(x + d[0], y + d[1], heights[x][y], ocean, visited)) {
                res = true;
                break;
            } 
        }
        return res;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const ocean: boolean[] = new Array(2).fill(false);
            const visited: boolean[][] = Array.from({length: m}, () => Array(n).fill(false));
            if (dfs(i, j, Number.MAX_VALUE, ocean, visited)) {
                result.push([i, j]);
            }
        }
    }

    return result;
};

/*
# Option #2
- O(m * n)
- Start from ocean (Move from one ocean to the other ocean if the previous height is not taller)
*/
function pacificAtlanticAdvanced(heights: number[][]): number[][] {
    
    const m: number = heights.length, n: number = heights[0].length;
    const pac: boolean[][] = Array.from({length: m}, () => Array(n).fill(false));
    for (let i = 0; i < m; i++) dfs(i, 0, 0, pac);
    for (let i = 0; i < n; i++) dfs(0, i, 0, pac);

    const atl: boolean[][] = Array.from({length: m}, () => Array(n).fill(false));
    for (let i = 0; i < m; i++) dfs(i, n - 1, 0, atl);
    for (let i = 0; i < n; i++) dfs(m - 1, i, 0, atl);

    function dfs(x: number, y: number, prev: number, visited: boolean[][]): void {
        if (x < 0 || y < 0 || x >= m || y >= n) return;
        if (prev > heights[x][y]) return;
        if (visited[x][y]) return;
        visited[x][y] = true;

        dfs(x - 1, y, heights[x][y], visited);
        dfs(x + 1, y, heights[x][y], visited);
        dfs(x, y - 1, heights[x][y], visited);
        dfs(x, y + 1, heights[x][y], visited);
    }

    const result: number[][] = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (pac[i][j] && atl[i][j]) result.push([i, j]);
        }
    }
    return result;
};