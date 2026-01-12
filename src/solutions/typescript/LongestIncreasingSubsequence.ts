
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-increasing-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 6, 2025
 	- `Answer`: lengthOfLIS / lengthOfLISAdvanced / lengthOfLISMap / lengthOfLISDp
*/

/**
# Option #1
- DFS
- O(n^2)
- Time Limit Exceeded
 */
function lengthOfLIS(nums: number[]): number {

    const n: number = nums.length;
    const map = new Map<number, Map<number, number>>();

    function dfs(index: number, prevIndex: number): number {
        if (index >= n) return 0;
        if (!map.has(index)) map.set(index, new Map<number, number>());
        else if (map.get(index)!.has(prevIndex)) return map.get(index)!.get(prevIndex)!;
        if (prevIndex !== -1 && nums[prevIndex] >= nums[index]) return dfs(index + 1, prevIndex);
        const res = Math.max(1 + dfs(index + 1, index), dfs(index + 1, prevIndex));
        map.get(index)!.set(prevIndex, res);
        return res;
    }
    return dfs(0, -1);
};



/**
# Option #2
- DP
- O(n^2)
 */
function lengthOfLISAdvanced(nums: number[]): number {
    const n: number = nums.length;
    const dp: number[] = new Array(n).fill(1);

    // Way #1
    // for (let i = n - 1; i > 0; i--) { // Standard (from back to the front)
    //     for (let j = i - 1; j >= 0; j--) { // Comparison Value
    //         if (nums[i] > nums[j]) {
    //             dp[j] = Math.max(dp[j], dp[i] + 1);
    //         }
    //     }
    // }

    // Way #2 (FASTER)
    // for (let i = 1; i < n; i++) { // current target element (from the beginning)
    //     for (let j = 0; j < i; j++) { // previous elements to compare with nums[i]
    //         if (nums[i] > nums[j]) { // if increasing subsequence condition is met
    //             dp[i] = Math.max(dp[i], dp[j] + 1); // update dp[i] based on dp[j] + 1
    //         }
    //     }
    // }

    // Way #3 (FASTER)
    for (let i = n - 2; i >= 0; i--) { // i means start index and it starts from the back (Standard)
        for (let j = i + 1; j < n; j++) { // From the start, it goes to the back
            if (nums[i] < nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    
    return Math.max(...dp);
};



/**
# Option #3
- Map
- O(n^2)
- Jan 12, 2026
 */
function lengthOfLISMap(nums: number[]): number {
    
    const memo: Map<number, number> = new Map(); // Key: past numbers, Value: Count from the past
    let result: number = 0;

    for (const num of nums) {
        for (const n of memo.keys()) {
            if (num <= n) continue;
            const tmpCnt = memo.get(n)!;
            const existingCnt = memo.get(num) ?? 0;
            result = Math.max(result, tmpCnt + 1);
            if (existingCnt <= tmpCnt) {
                memo.set(num, tmpCnt + 1);
            }
        }
        if (!memo.has(num)) {
            memo.set(num, 1);
        }
    }

    return result == 0 ? 1 : result;
};



/**
# Option #4
- Dynamic Programming
- O(n^2)
- Jan 12, 2026
 */
function lengthOfLISDp(nums: number[]): number {

    const n: number = nums.length;
    const dp: Array<number> = new Array(n).fill(1);

    for (let i = n - 1; i >= 1; i--) { // Standard
        for (let j = i - 1; j >= 0; j--) { // To the left
            if (nums[j] >= nums[i]) continue;
            dp[j] = Math.max(dp[j], dp[i] + 1);
        }
    }

    return Math.max(...dp);
};