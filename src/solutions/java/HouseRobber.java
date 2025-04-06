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
    - ref: https://www.youtube.com/watch?v=73r3KWiEvyk
     */
    public static int rob(int[] nums) {
    	return process(nums);
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
