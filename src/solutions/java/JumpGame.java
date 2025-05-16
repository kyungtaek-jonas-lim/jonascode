package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/jump-game/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: canJump / canJumpBetter / canJumpAdvanced
 */
public class JumpGame {


	public static void main(String[] args) {

	    System.out.println(canJump(new int[] {2,3,1,1,4})); // true
	    System.out.println(canJump(new int[] {3,2,1,0,4})); // false
	    
	    System.out.println("---");
	    System.out.println(canJumpAdvanced(new int[] {2,3,1,1,4})); // true
	    System.out.println(canJumpAdvanced(new int[] {3,2,1,0,4})); // false

	}
	

	/*
    # Option #1
    - Dynamic Programming
    - Bottom-Up
    - O(n^2)
	 */
    public static boolean canJump(int[] nums) {
    	
    	// Make the same length array and put the numbers of ways to each index.
    	int numsLength = nums.length;
    	boolean[] dp = new boolean[numsLength];
    	dp[0] = true; // Initial position (reached)
    	
    	for (int i = 0; i < numsLength; i++) {
    		
    		if (!dp[i]) continue; // If it this index is not reachable, skip it.
    		
    		// If it gets to the last index, return true
    		if (i + nums[i] >= numsLength - 1) return true;
    		
    		// Update the possibility
    		for (int j = i + nums[i]; j >= i; j--) {
				if (!dp[j]) dp[j] = true;
    		}
    	}
    	
    	// If the last index is not reachable, return false
    	return false;
    }

	/*
    # Option #2
    - Greedy
    - O(n)
    */
    public static boolean canJumpBetter(int[] nums) {
        
        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            if (i > maxLength) return false;
            maxLength = Math.max(nums[i] + i, maxLength);
        }

        return true;
    }

	/*
    # Option #3
    - Greedy
    - O(n)
    - ref: https://www.youtube.com/watch?v=Yan0cv2cLy8
	 */
    public static boolean canJumpAdvanced(int[] nums) {
    	
    	int numsLength = nums.length;
    	
    	// The goal is the last index.
    	int goal = numsLength - 1;
    	
    	// From the last index, let's move the goal forwards.
    	for (int i = numsLength - 1; i >= 0; i--) {
    		if (goal <= i + nums[i]) // If it can reach the goal, move the goal forward.
    			goal = i;
    	}
    	
    	// If the goal went all the way to the first, it means the first index can reach the last index.
    	return goal == 0;
    }
}
