
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/decode-ways/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: numDecodings / numDecodingsDP
 */


/*
# Option #1
- Top-down
- O(n)
*/
function numDecodings(s: string): number {
    const n: number = s.length;
    const memo = new Map<number, number>();

    function dfs(i: number): number {
        if (i === n) return 1;
        if (s[i] === '0') return 0;
        if (memo.has(i)) return memo.get(i)!;

        let res: number = dfs(i + 1);

        if (i + 1 < n) {
            const num: number = Number(s[i] + s[i + 1]);
            if (num >= 10 && num <= 26) {
                res += dfs(i + 2);
            }
        }

        memo.set(i, res);
        return res;
    }

    return dfs(0);
}




/*
# Option #2
- Bottom-up
- O(n)
*/
function numDecodingsDP(s: string): number {
    
    const n: number = s.length;
    if (s[0] === '0') return 0;
    
    const dp: number[] = new Array(n + 1).fill(0);
    dp[0] = 1;
    dp[1] = 1;
    
    for (let i = 2; i <= n; i++) {
        if (s[i - 1] === '0') dp[i] = 0;
        else dp[i] += dp[i - 1];

        const num: number = Number(s[i - 2] + s[i - 1]);
        if (num >= 10 && num <= 26) dp[i] += dp[i - 2];
    }
    return dp[n];
};