
/**
# Problem
	- `Link`: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13, 2025
	- `Answer`: twoSum
 */

/*
# Option #1
- O(n)
*/
function twoSum(numbers: number[], target: number): number[] {
    
    const n: number = numbers.length;
    let left: number = 0, right: number = n - 1;

    while (left < right) {
        const sum = numbers[left] + numbers[right];
        if (sum === target) {
            return [left + 1, right + 1];
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return [];
};