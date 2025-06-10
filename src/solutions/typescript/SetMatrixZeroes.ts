
/*
# Problem
	- `Link`: https://leetcode.com/problems/set-matrix-zeroes/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: setZeroes
*/


/*
# Option #1
- O(m * n)
*/
function setZeroes(matrix: number[][]): void {
    
    const m: number = matrix.length, n: number = matrix[0].length;
    let zeroRow: boolean = false;
    
    for (let i = 0; i < n; i++) {
        if (matrix[0][i] === 0) {
            zeroRow = true;
            break;
        }
    }

    for (let i = 1; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (matrix[i][j] === 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }

    for (let i = 1; i < m; i++) {
        if (matrix[i][0] === 0) {
            matrix[i] = new Array(n).fill(0);
        }
    }
    for (let j = 0; j < n; j++) {
        if (matrix[0][j] === 0) {
            for (let i = 1; i < m; i++) {
                matrix[i][j] = 0;
            }
        }
    }
    if (zeroRow) {
        matrix[0] = new Array(n).fill(0);
    }

};