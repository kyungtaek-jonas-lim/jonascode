
/*
# Problem
	- `Link`: https://leetcode.com/problems/set-matrix-zeroes/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: setZeroes / setZeroesAdvanced
*/


/*
# Option #1
- O(m * n)
- Space Complexity: O(m + n)
*/
function setZeroes(matrix: number[][]): void {
    const m: number = matrix.length, n: number = matrix[0].length;

    // Put 0 rows & cols in Sets
    const rows: Set<number> = new Set(), cols: Set<number> = new Set();
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                rows.add(i);
                cols.add(j);
            }
        }
    }

    // Replace values based on the Sets
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (rows.has(i) || cols.has(j)) {
                matrix[i][j] = 0;
            }
        }
    }
};


/*
# Option #2
- O(m * n)
- Space Complexity: O(1)
*/
function setZeroesAdvanced(matrix: number[][]): void {
    
    const m: number = matrix.length, n: number = matrix[0].length;

    // Check the first row & col
    let row: boolean = false, col: boolean = false;
    for (let j = 0; j < n; j++) {
        if (matrix[0][j] === 0) {
            row = true;
            break;
        }
    }
    for (let i = 0; i < m; i++){
        if (matrix[i][0] === 0) {
            col = true;
            break;
        }
    }

    // Put 0 on the first row & col
    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            if (matrix[i][j] === 0) {
                matrix[0][j] = 0;
                matrix[i][0] = 0;
            }
        }
    }

    // Replace value based on the first row & col
    for (let j = 1; j < n; j++) {
        if (matrix[0][j] === 0) {
            for (let i = 1; i < m; i++) {
                matrix[i][j] = 0;
            }
        }
    }
    for (let i = 1; i < m; i++) {
        if (matrix[i][0] === 0) {
            for (let j = 1; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    // Replace value on the first rolw & col
    if (row) {
        for (let j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
    if (col) {
        for (let i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
};