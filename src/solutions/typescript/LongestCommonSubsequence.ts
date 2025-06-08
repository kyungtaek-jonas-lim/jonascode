
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-common-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 8, 2025
 	- `Answer`: longestCommonSubsequence
*/

function longestCommonSubsequence(text1: string, text2: string): number {
    
    const n: number = text1.length, m: number = text2.length;
    const dp: number[][] = Array.from({length: n + 1}, () => Array(m + 1).fill(0));
    
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (text1[i] === text2[j]) {
                dp[i + 1][j + 1] = dp[i][j] + 1;
            } else {
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
    }
    return dp[n][m];
};