
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/combination-sum-iv/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 9, 2025
 	- `Answer`: combinationSum4 / combinationSum4DP
 */

/*
# Option #1
- Top-down
- O(target × n)
*/
function combinationSum4(nums: number[], target: number): number {
    
    const n: number = nums.length;
    const map = new Map<number, number>();
    function dfs(t: number) {
        if (t === 0) return 1;
        if (t < 0) return 0;
        if (map.has(t)) return map.get(t);

        let res: number = 0;
        for (const num of nums) {
            res += dfs(t - num)!;
        }

        map.set(t, res);
        return res;
    }
    
    return dfs(target)!;
};

/*
# Option #2
- Bottom-up
- O(target × n)
*/
function combinationSum4DP(nums: number[], target: number): number {
    
    const n: number = nums.length;
    const dp: number[] = new Array(target + 1).fill(0);
    dp[0] = 1;

    for (let i = 1; i <= target; i++) {
        for (const num of nums) {
            if (i - num < 0) continue;
            dp[i] += dp[i - num];
        }
    }
    return dp[target];
};