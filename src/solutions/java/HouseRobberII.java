package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber-ii/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: rob
 */
public class HouseRobberII {
	
	public static void main(String[] args) {

		System.out.println(rob(new int[] {2,3,2})); // 3
		System.out.println(rob(new int[] {1,2,3,1})); // 4
		System.out.println(rob(new int[] {1,2,3})); // 3
	}
	
	/*
	 # Option #1
	 - Dynamic Programming
	 - O(n)
	 */
    public static int rob(int[] nums) {
    	int numsLen = nums.length;
    	
    	// Edge Cases
        if (numsLen == 1) return nums[0];
        if (numsLen <= 3) {
        	int max = nums[0];
        	for (int i = 1; i < numsLen; i++) {
        		max = Math.max(max, nums[i]);
        	}
        	return max;
        }
        
        // Devide the array into two -> (0 ~ n-2), (1 ~ n-1)
        // And use the same logic as `House Robber`
        return Math.max(process(Arrays.copyOfRange(nums, 0, numsLen - 1)), process(Arrays.copyOfRange(nums, 1, numsLen)) );
    }
    
    // [House Robber Logic](https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/java/HouseRobber.java)
    private static int process(int[] nums) {
    	int prev_2steps = 0;
    	int prev_1step = nums[0];
    	int temp = 0;
    	for (int i = 1; i < nums.length; i++) {
    		temp = Math.max(prev_1step, prev_2steps + nums[i]);
    		prev_2steps = prev_1step;
    		prev_1step = temp;
    	}
    	return prev_1step;
    }
}
