/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 28, 2025
 	- `Answer`: maxSubArray
*/
function maxSubArray(nums: number[]): number {
    let globalMax = nums[0];
    let currentMax = nums[0];
    for (const num of nums.slice(1)) {
        currentMax = Math.max(currentMax + num, num);
        globalMax = Math.max(globalMax, currentMax);
    }
    return globalMax;
};