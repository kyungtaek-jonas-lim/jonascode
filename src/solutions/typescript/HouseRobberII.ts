
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber-ii/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: rob / robDP
 */

/*
# Option #1
- Top-down
- O(n)
*/
function rob(nums: number[]): number {
    
    const n: number = nums.length;
    if (n === 1) return nums[0];

    const map = new Map<number, number>();

    function dfs(index: number, end: number): number {
        if (index >= end) return 0;
        if (map.has(index)) return map.get(index)!;
        
        const res: number = Math.max(nums[index] + dfs(index + 2, end), dfs(index + 1, end));
        map.set(index, res);
        return res;
    }

    const res1 = dfs(0, n - 1);
    map.clear();
    const res2 = dfs(1, n);

    return Math.max(res1, res2);
};


/*
# Option #2
- Bottom-up
- O(n)
*/
function robDP(nums: number[]): number {
    
    const n: number = nums.length;
    if (n === 1) return nums[0];
    
    function dp(start: number, end: number) {
        let rob1: number = 0, rob2: number = 0;
        for (let i = start; i < end; i++) {
            const temp = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    return Math.max(dp(0, n - 1), dp(1, n));
};