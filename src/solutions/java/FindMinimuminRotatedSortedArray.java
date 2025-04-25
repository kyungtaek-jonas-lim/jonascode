package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 5, 2025
 	- `Answer`: findMin / findMinAdvanced / findMinBest
*/
public class FindMinimuminRotatedSortedArray {

	/*
	# Option #1
	- O(n log n)
	 */
    public static int findMin(int[] nums) {
    	Arrays.sort(nums);
//    	Arrays.parallelSort(nums); // Multi-Thread
    	return nums[0];
    }

	/*
	# Option #2
	- O(n)
	 */
    public static int findMinAdvanced(int[] nums) {
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < prev) return nums[i];
            prev = nums[i];
        }
        return nums[0];
    }

	/*
	# Option #3
	- Binary Search
	- Binary Search
	- ref: https://www.youtube.com/watch?v=nIVW4P8b1VA
	 */
    public static int findMinBest(int[] nums) {
    	
    	int result = nums[0];
    	int left = 0, right = nums.length - 1, middle = 0;
    	int num_left = 0, num_right = 0;
    	
    	while (left <= right) {
    		num_left = nums[left];
    		num_right = nums[right];
    		
    		// When it's sorted array
    		if (num_left < num_right) return Math.min(result, num_left);
    		
    		// If left subarray is sorted, check right subarray and vice versa.
    		middle = (left + right) / 2;
    		result = Math.min(result, nums[middle]);
    		if (num_left <= nums[middle]) left = middle + 1;
    		else right = middle - 1;
    	}
    	
    	return result;
    }
    
	public static void main(String[] args) {
		int[] target = new int[] {3,4,5,1,2};
		System.out.println(findMin(target)); // 1
		System.out.println(findMinAdvanced(target)); // 1
		
		target = new int[] {4,5,6,7,0,1,2};
		System.out.println(findMin(target)); // 0
		System.out.println(findMinAdvanced(target)); // 0
		
		target = new int[] {11,13,15,17};
		System.out.println(findMin(target)); // 11
		System.out.println(findMinAdvanced(target)); // 11
	}
}
