package solutions.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber-ii/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Aor 6, 2025
 	- `Answer`: rob / robDp / robDp2
 */
public class HouseRobberII {
	
	public static void main(String[] args) {

		System.out.println(rob(new int[] {2,3,2})); // 3
		System.out.println(rob(new int[] {1,2,3,1})); // 4
		System.out.println(rob(new int[] {1,2,3})); // 3
		
		System.out.println("---");
		System.out.println(robDp(new int[] {2,3,2})); // 3
		System.out.println(robDp(new int[] {1,2,3,1})); // 4
		System.out.println(robDp(new int[] {1,2,3})); // 3
	}
	

	/*
    # Option #1
    - Recursive method with Memoization
    - O(n)
	 */
    public static int rob(int[] nums) {
    	
    	// For short lengths
    	if (nums.length <= 3) {
    		int max = nums[0];
    		for (int i = 1; i < nums.length; i++) {
    			max = Math.max(max, nums[i]);
    		}
    		return max;
    	}
    	
        Map<Integer, Integer> map = new HashMap<>();
    	int left = process(Arrays.copyOfRange(nums, 0, nums.length - 1), 0, map);
    	map.clear();
    	int right = process(Arrays.copyOfRange(nums, 1, nums.length), 0, map);
    	return Math.max(left, right);
    }
    
    // [House Robber Logic](https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/python/HouseRobber.py)
    private static int process(int[] nums, int index, Map<Integer, Integer> map) {
    	if (index >= nums.length) return 0;
    	if (map.containsKey(index)) return map.get(index);
    	
    	int take = nums[index] + process(nums, index + 2, map);
    	int skip = process(nums, index + 1, map);
    	
    	map.put(index, Math.max(take, skip));
    	return map.get(index);
    }
	
	/*
	 # Option #2
	 - Dynamic Programming
	 - O(n)
	 - Space Complexity: O(1)
     - ref: https://www.youtube.com/watch?v=rWAJCfYYOvM
	 */
    public static int robDp(int[] nums) {
    	if (nums.length == 1) return nums[0];
    	return Math.max(process(Arrays.copyOfRange(nums, 0, nums.length - 1)), process(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    
    private static int process(int[] nums) {
    	
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
	 # Option #3
	 - Dynamic Programming
	 - O(n)
	 - Space Complexity: O(n)
	 */
    public int robDp2(int[] nums) {
        final int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(process(nums, 0, n - 1), process(nums, 1, n));
    }

    private int process(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 2];
        for (int i = start; i < end; i++) {
            dp[i - start + 2] = Math.max(dp[i - start + 1], dp[i - start] + nums[i]);
        }
        return dp[end - start + 1];
    }
}
