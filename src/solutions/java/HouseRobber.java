package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 6, 2025
 	- `Answer`: rob / robAdvanced
 */
public class HouseRobber {

	public static void main(String[] args) {
		System.out.println(rob(new int[] {1,2,3,1})); // 4
		System.out.println(rob(new int[] {2,7,9,3,1})); // 12
		
		System.out.println("---");
		System.out.println(robAdvanced(new int[] {1,2,3,1})); // 4
		System.out.println(robAdvanced(new int[] {2,7,9,3,1})); // 12
	}
    
    /*
    # Option #1
    - Dynamic Programming
    - O(n)
     */
    public static int rob(int[] nums) {
    	if (nums.length == 1) return nums[0];
    	
    	// The goal is the max value when you get to the last index.
    	// Find all the max values for each index
    	int prev_2steps = 0;
    	int prev_1step = nums[0];
    	int temp = 0;
    	for (int i = 1; i < nums.length; i++) {
    		temp = Math.max(prev_1step, prev_2steps + nums[i]); // compare 1 step before result + {2 steps before result + current result}
    		prev_2steps = prev_1step;
    		prev_1step = temp; // Now `prev_1step` becomes current
    	}
    	
    	return prev_1step;
    }
	
	/*
    # Option #2
    - Recursive method with Memoization
    - O(n)
	 */
    public static int robAdvanced(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
    	return process(nums, 0, map);
    }
    
    private static int process(int[] nums, int index, Map<Integer, Integer> map) {
    	// If it's eqaul or greater than the length of nums
    	if (index >= nums.length) return 0;
    	// Cache
    	if (map.containsKey(index)) return map.get(index);
    	
    	// Take this house
    	int take = nums[index] + process(nums, index + 2, map);
    	// Skip this house
    	int skip = process(nums, index + 1, map);
    	
    	// Get the maximum
    	map.put(index, Math.max(take, skip));
    	return map.get(index);
    }
}
