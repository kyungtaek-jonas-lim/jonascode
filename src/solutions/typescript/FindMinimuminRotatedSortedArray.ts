
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 3, 2025
 	- `Answer`: findMin / findMinAdvanced
     */


/**
# Option #1
O(n)
 */
function findMin(nums: number[]): number {
    return Math.min(...nums);
};


/**
# Option #2
- O(log n)
- Binary Search
 */
function findMinAdvanced(nums: number[]): number {
    
    let left: number = 0, right: number = nums.length - 1;

    while (left < right) {
        let middle: number = Math.floor((right + left) / 2);

        if (nums[middle] > nums[right]) { // If middle is greater than right, the right subarray has the minimum value
            left = middle + 1;
        } else {
            right = middle; // Vice versa, but left could be the same as right, so not 'middle - 1'
        }
    }
    return nums[left];
};