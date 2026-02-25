
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/jump-game/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: canJump / canJumpDfs
*/

/*
# Option #1
- Greedy
- O(n)
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


/*
# Option #2
- DFS + Memoization
- O(n^2)
*/
function canJumpDfs(nums: number[]): boolean {
    const n: number = nums.length;
    
    const memo: Map<number, boolean> = new Map();
    
    function dfs(index: number): boolean {
        if (index + nums[index] >= n - 1) return true;
        if (memo.has(index)) return memo.get(index)!;

        for (let i = index + 1; i <= index + nums[index]; i++) {
            if (dfs(i)) return true;
        }
        memo.set(index, false);
        return false;
    }

    return dfs(0);
};