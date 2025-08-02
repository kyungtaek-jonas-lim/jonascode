
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-common-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 8, 2025
 	- `Answer`: longestCommonSubsequenceDfsMemo / longestCommonSubsequence2d
*/

/*
# Option #1
- DFS + Memoization
- O (n * m)
*/
function longestCommonSubsequenceDfsMemo(text1: string, text2: string): number {
    
    const m: number = text1.length, n: number = text2.length;

    // const memo: Map<string, number> = new Map();
    const memo: number[][] = Array.from({length: m}, () => Array(n).fill(-1));
    
    function dfs(i1: number, i2: number): number {
        if (i1 >= m || i2 >= n) return 0;
        
        // const key: string = `${i1},${i2}`;
        // if (memo.has(key)) return memo.get(key)!;
        if (memo[i1][i2] !== -1) return memo[i1][i2];

        let res: number = 0;
        if (text1[i1] === text2[i2]) {
            res = 1 + dfs(i1 + 1, i2 + 1);
        } else {
            res = Math.max(dfs(i1 + 1, i2), dfs(i1, i2 + 1));
        }

        // memo.set(key, res);
        memo[i1][i2] = res;
        return res;
    }

    return dfs(0, 0);
};


/*
# Option #2
- Dynamic Programming
- 2D Array
- O (n * m)
*/
function longestCommonSubsequence2d(text1: string, text2: string): number {
    
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