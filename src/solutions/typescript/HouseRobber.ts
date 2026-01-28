
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: robRecursiveMemoization / robDpWithConstantSpace / robDpArray
 */

/*
# Option #1
- DP with Constant Space
- O(n)
- Space Complexity: O(1)
- ref: https://www.youtube.com/watch?v=73r3KWiEvyk
*/
function robRecursiveMemoization(nums: number[]): number {
    const n: number = nums.length;
    const map = new Map<number, number>();
    function dfs(index: number): number {
        if (index >= n) return 0;
        if (map.has(index)) return map.get(index)!;
        const res = Math.max(dfs(index + 1), dfs(index + 2) + nums[index]);
        map.set(index, res);
        return res;
    }
    return dfs(0);
};

/*
# Option #2
- Recursive + Memoization
- O(n)
- Space Complexity: O(n)
*/
function robDpWithConstantSpace(nums: number[]): number {
    
    const n: number = nums.length;
    let rob1: number = 0, rob2: number = 0;
    
    for (const num of nums) {
        const temp = Math.max(num + rob1, rob2);
        rob1 = rob2;
        rob2 = temp;
    }
    return rob2;
};

/*
# Option #3
- DP Array
- Common
- O(n)
- Space Complexity: O(n)
- Jan 27, 2026
*/
function robDpArray(nums: number[]): number {
    const n: number = nums.length;
    const dp: number[] = new Array(n + 1).fill(0);
    dp[1] = nums[0];

    for (let i = 1; i < n; i++) {
        dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
    }

    return dp[n];
};