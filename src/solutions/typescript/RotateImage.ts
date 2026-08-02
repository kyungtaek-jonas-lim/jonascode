
/**
# Problem
	- `Link`: https://leetcode.com/problems/rotate-image/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13, 2025
	- `Answer`: rotate
 */

/*
# Option #1
- O(n^2)
*/
function rotate(matrix: number[][]): void {
    
    const n: number = matrix.length;
    let min: number = 0, max: number = n - 1;

    while (min < max) {
        for (let i = 0; i < max - min; i++) {
            const topLeft = matrix[min][min + i];
            matrix[min][min + i] = matrix[max - i][min];
            matrix[max - i][min] = matrix[max][max - i];
            matrix[max][max - i] = matrix[min + i][max];
            matrix[min + i][max] = topLeft;
        }
        min++;
        max--;
    }

    // const n: number = matrix.length;
    // let s: number = 0, e: number = n - 1;
    // while (s < e) {
    //     for (let i = s; i < e; i++) {
    //         const temp: number = matrix[s][i];
    //         matrix[s][i] = matrix[n - 1 - i][s];
    //         matrix[n - 1 - i][s] = matrix[e][n - 1 - i];
    //         matrix[e][n - 1 - i] = matrix[i][e];
    //         matrix[i][e] = temp;
    //     }
    //     s++;
    //     e--;
    // }
};