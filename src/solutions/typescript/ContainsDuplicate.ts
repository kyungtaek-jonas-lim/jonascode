
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/contains-duplicate/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 30, 2025
 	- `Answer`: containsDuplicate
*/

function containsDuplicate(nums: number[]): boolean {
    const set = new Set<number>();
    for (const num of nums) {
        if (set.has(num)) return true;
        set.add(num);
    }
    return false;
};