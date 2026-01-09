
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange / coinChangeDp1 / coinChangeDp2 / coinChangeDfs / coinChangeBfs
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/CoinChange.md
 */


/**
# Option #1
- DFS + Memoization
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
};


/**
# Option #2
- Dynamic Programming #1
- O(amount × n)
- ref) https://www.youtube.com/watch?v=H9bfqozjoqs
 */
function coinChangeDp1(coins: number[], amount: number): number {
    
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


/**
# Option #3
- Dynamic Programming #2
- O(amount × n)
- https://www.youtube.com/watch?v=KnWorqyDSLA
 */
function coinChangeDp2(coins: number[], amount: number): number {
    const dp: Array<number> = new Array(amount + 1).fill(amount + 1);
    dp[0] = 0;
    for (const coin of coins) {
        for (let i = coin; i <= amount; i++) {
            dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
    }
    return dp[amount] == amount + 1 ? -1 : dp[amount];
};


/**
# Option #4
- DFS + Memoization
- O(amount × n)
 */
function coinChangeDfs(coins: number[], amount: number): number {
    
    const memo: Map<number, number> = new Map();

    function dfs(goal: number): number {
        if (goal === 0) return 0;
        if (goal < 0) return Number.MAX_VALUE;
        if (memo.has(goal)) return memo.get(goal)!;

        let res: number = Number.MAX_VALUE;
        for (const coin of coins) {
            const tmp: number = dfs(goal - coin);
            if (tmp < res) {
                res = tmp + 1;
            }
        }
        memo.set(goal, res);
        return res;
    }

    const result: number = dfs(amount);
    return result === Number.MAX_VALUE ? -1 : result;
};


/**
# Option #5
- BFS + Memoization
- O(amount × n)
 */
function coinChangeBfs(coins: number[], amount: number): number {
    
    if (amount === 0) return 0;
    const deque: Array<Array<number>> = [];
    coins.sort((a, b) => b - a);

    for (const coin of coins) {
        const diff: number = amount - coin;
        if (diff < 0) continue;
        if (diff === 0) return 1;
        deque.push([diff, 2]);
    }

    const memo: Set<number> = new Set();
    while (deque.length !== 0) {
        const item: Array<number> = deque.shift()!;
        const goal: number = item[0], cnt: number = item[1];
        if (memo.has(goal)) continue;
        memo.add(goal);

        for (const coin of coins) {
            const diff: number = goal - coin;
            if (diff < 0) continue;
            if (diff === 0) return cnt;
            deque.push([diff, cnt + 1]);
        }
    }

    return -1;
};