package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: rob / robAdvanced
 */
public class HouseRobber {

	public static void main(String[] args) {
		System.out.println(rob(new int[] {1,2,3,1})); // 4
		System.out.println(rob(new int[] {2,7,9,3,1})); // 12
	}
	
	/*
    # Option #1
    - Recursive method with Memoization
    - O(n × S): n is the number of indices, and S is the number of possible cumulative result values.
	 */
    public static int rob(int[] nums) {
    	Map<String, Integer> map = new HashMap<>();
    	return Math.max(process(nums, 0, 0, map), process(nums, 1, 0, map));
    }
    
    private static int process(int[] nums, int index, int result, Map<String, Integer> map) {
    	if (index >= nums.length) return result;
    	String mapKey = index + "," + result;
    	if (map.containsKey(mapKey)) return map.get(mapKey);
    	
    	
    	int result1 = process(nums, index + 2, result + nums[index], map);
    	int result2 = process(nums, index + 3, result + nums[index], map);
    	map.put(mapKey, Math.max(result1, result2));
    	return map.get(mapKey);
    }
    
    /*
    # Option #2
    - Dynamic Programming
    - O(n)
     */
    public static int robAdvanced(int[] nums) {
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
}
