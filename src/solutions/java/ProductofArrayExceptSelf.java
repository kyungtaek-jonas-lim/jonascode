package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/product-of-array-except-self/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Feb 2, 2025
	- `Answer`: productExceptSelf
*/

public class ProductofArrayExceptSelf {

	public static void main(String[] args) {
		int[] result = null;
		result = productExceptSelf(new int[] {1, 2, 3, 4});
		print(result);
		result = productExceptSelf(new int[] {-1, -1, 0, -3, 3});
		print(result);
	}

	public static void print(int[] result) {
		System.out.print("[ ");
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println("]");
	}
	
	/**
	 * @option 1
	 * @description Common way
	 * @timeComplexity O(n)
	 * @param nums
	 * @return
	 */
    public static int[] productExceptSelf(int[] nums) {

        // ---------------------------
    	// Step 1: Compute the product from the beginning
    	int[] result = new int[nums.length];
    	int product = 1;
        for (int i = 0; i < nums.length; i++) {
        	result[i] = product; // Skip multiplication for its own index
        	product *= nums[i];
        }
        
        // ---------------------------
        // Step 2: Compute the product from the end
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
        	// Multiply with the current value
        	result[i] = result[i] * product; // Skip multiplication for its own index
        	product *= nums[i];
        }
        
        return result;
    }
}
