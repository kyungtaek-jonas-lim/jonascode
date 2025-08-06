
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/decode-ways/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: numDecodings / numDecodingsDP
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/reference/DecodeWays.png
 */


/*
# Option #1
- Top-down
- O(n)
*/
function numDecodings(s: string): number {

    const n: number = s.length;
    const memo: Map<number, number> = new Map();
    
    function dfs(index: number): number {
        if (index >= n) return 1;
        if (s[index] === '0') return 0;
        if (memo.has(index)) return memo.get(index)!;
        let result: number = 0;

        // Choose first one character
        result += dfs(index + 1);

        // Choose first two characters
        if (index !== n - 1) {
            const twoDigits: number = Number(s[index] + s[index + 1]);
            if (twoDigits <= 26) result += dfs(index + 2);
        }

        memo.set(index, result);
        return result;
    }
    
    return dfs(0);
};




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