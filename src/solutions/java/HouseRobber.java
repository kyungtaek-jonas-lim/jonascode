package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 6, 2025
 	- `Answer`: robDpWithConstantSpace / robRecursiveMemoization / robDpArray
 */
public class HouseRobber {

	public static void main(String[] args) {
		System.out.println(robDpWithConstantSpace(new int[] {1,2,3,1})); // 4
		System.out.println(robDpWithConstantSpace(new int[] {2,7,9,3,1})); // 12
		
		System.out.println("---");
		System.out.println(robRecursiveMemoization(new int[] {1,2,3,1})); // 4
		System.out.println(robRecursiveMemoization(new int[] {2,7,9,3,1})); // 12
	}

    /*
	# Option #1
	- DP with Constant Space
	- O(n)
	- Space Complexity: O(1)
	- ref: https://www.youtube.com/watch?v=73r3KWiEvyk
	*/
    public static int robDpWithConstantSpace(int[] nums) {
    	
    	int rob1 = 0, rob2 = 0;
    	int temp = 0;
    	
    	// [rob1, rob2, n, n+1, ...]
    	for (int num: nums) {
    		temp = Math.max(rob1 + num, rob2);
    		rob1 = rob2;
    		rob2 = temp;
    	}
    	
    	return rob2;
    }
	
	/*
	# Option #2
	- Recursive + Memoization
	- O(n)
	- Space Complexity: O(n)
	*/
    public static int robRecursiveMemoization(int[] nums) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(nums, 0, memo);
    }
    
    private static int dfs(int[] nums, int x, Map<Integer, Integer> memo) {
        if (x >= nums.length) return 0;
        if (memo.containsKey(x)) return memo.get(x);

        int res = Math.max(dfs(nums, x + 1, memo), dfs(nums, x + 2, memo) + nums[x]);
        memo.put(x, res);
        return res;
    }

	/*
	# Option #3
	- DP Array
	- Common
	- O(n)
	- Space Complexity: O(n)
	- Jan 27, 2026
	*/
    public int robDpArray(int[] nums) {
        
        final int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
        }

        return dp[n];
    }
}
