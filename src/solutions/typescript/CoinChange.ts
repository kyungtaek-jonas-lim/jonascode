
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange / coinChangeAdvanced
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/CoinChange.md
 */


/**
# Option #1
- O(amount × n)
 */
function coinChange(coins: number[], amount: number): number {
    coins.sort((a, b) => b - a);
    const n = coins.length;
    let minCount = Number.MAX_VALUE;
    const memo = new Map<string, number>();

    function dfs(cnt: number, target: number, start: number): void {
        if (target < 0) return;
        if (cnt >= minCount) return;
        if (target === 0) {
            minCount = cnt;
            return;
        }

        const key = `${target},${start}`;
        if (memo.has(key) && memo.get(key)! <= cnt) return;
        memo.set(key, cnt);

        for (let i = start; i < n; i++) {
            dfs(cnt + 1, target - coins[i], i);
        }
    }

    dfs(0, amount, 0);
    return minCount === Number.MAX_VALUE ? -1 : minCount;
}


/**
# Option #2
- O(amount × n)
 */
function coinChangeAdvanced(coins: number[], amount: number): number {
    
    const dp: number[] = new Array(amount + 1).fill(amount + 1);
    dp[0] = 0;

    for (let target = 1; target <= amount; target++) {
        for (const coin of coins) {
            if (coin > target) continue;
            dp[target] = Math.min(dp[target], dp[target - coin] + 1);
        }
    }

    return dp[amount] === amount + 1 ? -1 : dp[amount];
};