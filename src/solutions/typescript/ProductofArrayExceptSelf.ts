/*
# Problem
	- `Link`: https://leetcode.com/problems/product-of-array-except-self/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 2, 2025
	- `Answer`: productExceptSelf
*/

function productExceptSelf(nums: number[]): number[] {
    
    const n: number = nums.length;
    const result: number[] = new Array(n);

    // Left to right
    let value: number = 1;
    for (let i = 0; i < n; i++) {
        result[i] = value;
        value *= nums[i];
    }

    // Right to left
    value = 1;
    for (let i = n - 1; i >= 0; i--) {
        result[i] *= value;
        value *= nums[i];
    }

    return result;
};