/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 28, 2025
 	- `Answer`: maxSubArray / maxSubArray2
*/

/*
* Option #1
* O(n)
*/
function maxSubArray(nums: number[]): number {
    let currSum: number = 0, result: number = nums[0]
    for (const num of nums) {
        if (currSum < 0) currSum = 0;
        currSum += num;
        result = Math.max(result, currSum);
    }
    return result
};

/*
* Option #2
* O(n)
*/
function maxSubArray2(nums: number[]): number {
    let globalMax = nums[0];
    let currentMax = nums[0];
    for (const num of nums.slice(1)) {
        currentMax = Math.max(currentMax + num, num);
        globalMax = Math.max(globalMax, currentMax);
    }
    return globalMax;
};