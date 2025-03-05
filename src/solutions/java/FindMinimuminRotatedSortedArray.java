package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 5, 2025
 	- `Answer`: findMin / findMinAdvanced
*/
public class FindMinimuminRotatedSortedArray {

    public static int findMin(int[] nums) {
    	Arrays.sort(nums);
//    	Arrays.parallelSort(nums); // Multi-Thread
    	return nums[0];
    }

    public static int findMinAdvanced(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(nums[i], min);
        }
        return min;
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
