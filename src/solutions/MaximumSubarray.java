package solutions;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 18, 2025
 	- `Answer`: maxSubArray
 */

public class MaximumSubarray {
	
	public static void main(String[] args) {
		
		System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
		System.out.println(maxSubArray(new int[] {1}));
		System.out.println(maxSubArray(new int[] {5,4,-1,7,8}));
		
	}

	/*
	 * Option #1 
	 * Common way
	 * O(n)
	 */
    public static int maxSubArray(int[] nums) {
        int pending = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (pending < 0 && nums[i] > pending) {
                pending = nums[i];
            } else pending += nums[i];
            if (max < pending) {
                max = pending;
            }
        }
        return max;
    }
}
