
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
    
    const m: number = matrix.length, n: number = matrix[0].length;
    let minHeight: number = 0, maxHeight: number = m - 1;
    let minWidth: number = 0, maxWidth: number = n - 1;

    const result: number[] = [];
    while (minHeight <= maxHeight && minWidth <= maxWidth) {
        
        for (let j = minWidth; j <= maxWidth; j++) {
            result.push(matrix[minHeight][j]);
        }
        minHeight++;
        
        for (let i = minHeight; i <= maxHeight; i++) {
            result.push(matrix[i][maxWidth]);
        }
        maxWidth--;

        if (!(minHeight <= maxHeight && minWidth <= maxWidth)) break;
        
        for (let j = maxWidth; j >= minWidth; j--) {
            result.push(matrix[maxHeight][j]);
        }
        maxHeight--;
        
        for (let i = maxHeight; i >= minHeight; i--) {
            result.push(matrix[i][minWidth]);
        }
        minWidth++;
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