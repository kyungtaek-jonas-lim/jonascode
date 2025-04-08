package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-product-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxProduct
 */

public class MaximumProductSubarray {
	
	public static void main(String[] args) {
		
		System.out.println(maxProduct(new int[] {2,3,-2,4}));
		System.out.println(maxProduct(new int[] {-2,0,-1}));
		System.out.println(maxProduct(new int[] {3,-1,4}));
	}

	/*
	 # Option #1 
     - O(n)
	 */
    public static int maxProduct(int[] nums) {
    	
    	int maxVal = nums[0];
    	int minVal = maxVal;
    	int result = maxVal;
    	
    	for (int i = 1; i < nums.length; i++) {
    		int num = nums[i];
    		
    		if (num < 0) {
    			int temp = maxVal;
    			maxVal = minVal;
    			minVal = temp;
    		}
    		
    		maxVal = Math.max(num, maxVal * num);
    		minVal = Math.min(num, minVal * num);
    		
    		result = Math.max(result, maxVal);
    	}
    	
    	return result;
    }
}
