
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/jump-game/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: canJump
*/

/*
# Option #1
- Greedy
*/
function canJump(nums: number[]): boolean {
    
    const n: number = nums.length;
    
    let standard: number = n - 1;
    for (let i = n - 2; i >= 0; i--) {
        if (nums[i] + i >= standard) {
            standard = i;
        }
    }
    return standard === 0;
};