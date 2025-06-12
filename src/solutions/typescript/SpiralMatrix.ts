
/**
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 12, 2025
	- `Answer`: spiralOrder
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