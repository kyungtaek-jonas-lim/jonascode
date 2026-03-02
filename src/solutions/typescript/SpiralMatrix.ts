
/**
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 12, 2025
	- `Answer`: spiralOrder / spiralOrderDfs
 */


/*
# Option #1
- O(m * n)
*/
function spiralOrder(matrix: number[][]): number[] {
    let l: number = 0, r: number = matrix[0].length, t: number = 0, b: number = matrix.length;
    const result: number[] = [];

    while (l < r && t < b) {

        // right
        for (let j = l; j < r; j++) {
            result.push(matrix[t][j]);
        }
        t++;

        // down
        for (let i = t; i < b; i++) {
            result.push(matrix[i][r - 1]);
        }
        r--;

        // Check the condition to prevent the duplicates
        if (!(l < r && t < b)) break;

        // left
        for (let j = r - 1; j >= l; j--) {
            result.push(matrix[b - 1][j]);
        }
        b--;

        // up
        for (let i = b - 1; i >= t; i--) {
            result.push(matrix[i][l]);
        }
        l++;
    }

    return result;
};


/*
# Option #2
- DFS traversal with direction control
- O(m * n)
- Extra Space Complexity: O(m * n)
- visited matrix: O(m * n)
- recursion stack: O(m * n) in worst case
- A DFS version is implemented, but since spiral traversal is deterministic and does not require graph exploration, a boundary-based iterative solution is more optimal in Python due to lower overhead and O(1) extra space.
*/
function spiralOrderDfs(matrix: number[][]): number[] {

    const result: number[] = []
    const dx: number[] = [0, 1, 0, -1], dy: number[] = [1, 0, -1, 0];
    const m: number = matrix.length, n: number = matrix[0].length;
    // const done: Set<string> = new Set();
    const done: boolean[][] = Array.from({length: m}, () => Array(n).fill(false));
    
    function dfs(x: number, y: number, d: number, final: boolean): void {
        // if (x < 0 || y < 0 || x >= m || y >= n || done.has(`${x},${y}`)) {
        if (x < 0 || y < 0 || x >= m || y >= n || done[x][y]) {
            if (final) return;
            final = true;
            x -= dx[d];
            y -= dy[d];
            d = (d + 1) % 4;
        } else {
            final = false;
            // done.add(`${x},${y}`);
            done[x][y] = true;
            result.push(matrix[x][y]);
        }
        dfs(x + dx[d], y + dy[d], d, final);
    }
    
    dfs(0, 0, 0, false);
    return result;
};