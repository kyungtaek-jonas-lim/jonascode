
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 6, 2025
 	- `Answer`: rob / robDP
 */

/*
# Option #1
- Top-down
- O(n)
*/
function rob(nums: number[]): number {
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
- Bottom-Up
- O(n)
*/
function robDP(nums: number[]): number {
    
    const n: number = nums.length;
    let rob1: number = 0, rob2: number = 0;
    
    for (const num of nums) {
        const temp = Math.max(num + rob1, rob2);
        rob1 = rob2;
        rob2 = temp;
    }
    return rob2;
};