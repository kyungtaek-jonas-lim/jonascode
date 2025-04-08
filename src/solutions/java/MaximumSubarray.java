package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 18, 2025 (maxSubArray) / Apr 8, 2025 (maxSubArraySomeOtherWay)
 	- `Answer`: maxSubArray / maxSubArraySomeOtherWay
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
    

	/*
	 * Option #2
	 * Some other way
	 * O(n)
	 */
    public int maxSubArraySomeOtherWay(int[] nums) {
        
    	int sum = nums[0];
    	int result = sum;
    	
    	for (int i = 1; i < nums.length; i++) {
    		int num = nums[i];
    		
    		if (num > sum + num) {
    			sum = num;
    		} else {
    			sum += num;
    		}
    		result = Math.max(sum, result);
    	}
    	
    	return result;
    }
}
