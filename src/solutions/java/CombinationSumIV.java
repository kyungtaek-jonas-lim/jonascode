package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/combination-sum-iv/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: combinationSum4 / combinationSum4Advanced
 */
public class CombinationSumIV {
	
	public static void main(String[] args) {
	    System.out.println(combinationSum4(new int[] {1, 2, 3}, 4)); // 7
	    System.out.println(combinationSum4(new int[] {9}, 3)); // 0
	    System.out.println(combinationSum4(new int[] {1, 2, 3}, 32)); // 181997601

	    System.out.println("---");
	    System.out.println(combinationSum4Advanced(new int[] {1, 2, 3}, 4)); // 7
	    System.out.println(combinationSum4Advanced(new int[] {9}, 3)); // 0
	    System.out.println(combinationSum4Advanced(new int[] {1, 2, 3}, 32)); // 181997601
	}
	
	
	/*
	 # Option #
     - Bottom-Up DP (Tabulation)
     - O(n × target) (n = len(nums))
	 */
    public static int combinationSum4(int[] nums, int target) {
        
    	int[] dp = new int[target + 1];
    	dp[0] = 1; // Base case: one way to make sum = 0

    	for (int t = 1; t <= target; t++) {
    		for (int num : nums) {
    			if ((t - num) >= 0) {
    				dp[t] += dp[t - num];
    			}
    		}
    	}
    	return dp[target];
    }
    
    /*
     # Option #2
   	 - Top-Down with Memoization
     - O(n × target) (n = len(nums))
     */
    public static int combinationSum4Advanced(int[] nums, int target) {
    	Map<Integer, Integer> map = new HashMap<>();
    	return process(map, nums, target);
    }
    
    private static int process(Map<Integer, Integer> map, int[] nums, int target) {
    	if (target < 0) return 0;
    	if (target == 0) return 1;
    	if (map.containsKey(target)) return map.get(target);
    	
    	int result = 0;
    	for (int num: nums) {
    		result += process(map, nums, target - num);
    	}
    	
    	map.put(target, result);
    	return result;
    }
}
