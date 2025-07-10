
/*
# Problem
	- `Link`: https://leetcode.com/problems/pacific-atlantic-water-flow/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 10, 2025
	- `Answer`: pacificAtlantic
*/

/*
# Option #1
- O(m * n)
- Start from ocean (Move from one ocean to the other ocean if the previous height is not taller)
*/
function pacificAtlantic(heights: number[][]): number[][] {

    const m: number = heights.length, n: number = heights[0].length;
    
    // From Pacific to Atlantic (Top, Left)
    const visitedPacific = new Set<string>();
    for (let i = 0; i < n; i++) {
        dfs(heights, 0, i, -1, visitedPacific);
    }
    for (let i = 0; i < m; i++) {
        dfs(heights, i, 0, -1, visitedPacific);
    }

    // From Atlantic to Pacific (Bottom, Right)
    const visitedAtlantic = new Set<string>();
    for (let i = 0; i < n; i++) {
        dfs(heights, m - 1, i, -1, visitedAtlantic);
    }
    for (let i = 0; i < m; i++) {
        dfs(heights, i, n - 1, -1, visitedAtlantic);
    }
    
    const result: number[][] = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            const key = `${i},${j}`;
            if (visitedPacific.has(key) && visitedAtlantic.has(key)) result.push([i, j]);
        }
    }

    return result;
};

function dfs(heights: number[][], x: number, y: number, prev: number, visited: Set<string>): void {
    // Validation
    const m: number = heights.length, n: number = heights[0].length;
    if (x < 0 || y < 0 || x >= m || y >= n) return;
    
    // Memoization
    const key = `${x},${y}`;
    if (visited.has(key)) return;
    
    // Compare to the previous value
    const curr = heights[x][y];
    if (prev > curr) return;

    visited.add(key);

    dfs(heights, x + 1, y, curr, visited)
    dfs(heights, x - 1, y, curr, visited)
    dfs(heights, x, y + 1, curr, visited)
    dfs(heights, x, y - 1, curr, visited)
}