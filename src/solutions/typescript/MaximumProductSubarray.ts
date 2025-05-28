/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-product-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 28, 2025
 	- `Answer`: maxProduct / maxProductAdvanced
*/


/*
    # Option #1
    - O(n)
*/
function maxProduct(nums: number[]): number {
    let min: number = nums[0], max: number = nums[0];
    let result: number = nums[0];
    for (const num of nums.slice(1)) {
        let temp: number = min;
        min = Math.min(min * num, num, max * num);
        max = Math.max(max * num, num, temp * num);
        result = Math.max(result, max);
    }
    return result;
};


/*
    # Option #2
    - O(n)
*/
function maxProductAdvanced(nums: number[]): number {
    let min: number = nums[0], max: number = nums[0];
    let result: number = nums[0];
    for (const num of nums.slice(1)) {
        let temp: number = min;
        if (num < 0) {
            min = Math.min(num, max * num);
            max = Math.max(num, temp * num);
        } else {
            min = Math.min(min * num, num);
            max = Math.max(max * num, num);
        }
        result = Math.max(result, max);
    }
    return result;
}