
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/unique-paths/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: uniquePaths / uniquePaths1dArray
 */

/*
# Option #1
- 2D Array
*/
function uniquePaths(m: number, n: number): number {
    
    if (m === 1 || n === 1) return 1;
    const dp: number[][] = Array.from({length: m}, () => Array(n).fill(0));

    for (let i = 0; i < m; i++) {
        dp[i][0] = 1;
        for (let j = 1; j < n; j++) {
            if (i === 0) dp[i][j] = 1;
            else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
    }
    return dp[m - 1][n - 1];
};



/*
# Option #2
- 1D Array
*/
function uniquePaths1dArray(m: number, n: number): number {
    
    const dp: number[] = new Array(n).fill(1);

    for (let i = 1; i < m; i++) {
        for (let j = 1; j < n; j++) {
            dp[j] += dp[j - 1];
        }
    }
    return dp[n - 1];
};