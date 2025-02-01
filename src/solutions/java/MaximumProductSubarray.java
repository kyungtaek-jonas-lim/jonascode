package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-product-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 17, 2025
 	- `Answer`: maxProduct
 */

public class MaximumProductSubarray {
	
	public static void main(String[] args) {
		
		System.out.println(maxProduct(new int[] {2,3,-2,4}));
		System.out.println(maxProduct(new int[] {-2,0,-1}));
		System.out.println(maxProduct(new int[] {3,-1,4}));
	}

	/*
	 * Option #1 
	 * Common way
	 * O(n^2)
	 */
    public static int maxProduct(int[] nums) {
    	int max = nums[0];
    	int min = nums[0]; // for negative numbers (even if it's negative, still important)
    	int result = nums[0];
    	
    	for (int i = 1; i < nums.length; i++) {
    		int current = nums[i]; // Using a variable like `current` reduces repeated array access, improving performance and enhancing code readability.
    		
    		// Get the min and max value upto this `i`th item.
    		int previousMax = max; // store the current max
    		max = Math.max(current, Math.max(max * current, min * current)); // `min` is still important to calculate the maximum value
    		min = Math.min(current, Math.min(previousMax * current, min * current));
    		
    		// Compare current item to consider the possibility that the current element could serve as the starting point of a new subarray.
    		if (result < max) result = max;
    	}
    	return result;
    }
}
