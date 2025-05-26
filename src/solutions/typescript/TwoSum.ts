/*
 # Problem
 	- `Link`: https://leetcode.com/problems/two-sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 26, 2025
 	- `Answer`: twoSum
*/
function twoSum(nums: number[], target: number): number[] {
    const map = new Map<number, number>();
    let n = nums.length;
    for (let i = 0; i < n; i++) {
        let num = nums[i];
        if (map.has(num)) {
            return [map.get(num)!, i];
        }
        map.set(target - num, i);
    }
    return [];
};

console.log(twoSum([2, 7, 11, 15], 9)); // [0, 1]
console.log(twoSum([3, 2, 4], 6));      // [1, 2]
console.log(twoSum([3, 3], 6));         // [0, 1]
console.log(twoSum([1, 2, 3], 10));     // []