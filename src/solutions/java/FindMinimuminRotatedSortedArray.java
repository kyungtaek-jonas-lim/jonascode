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
	- O(log n)
	- Binary Search
	 */
    public static int findMinBest(int[] nums) {
        int left = 0, right = nums.length - 1;
        int middle = 0;

        while (left < right) {
            middle = (left + right) / 2;
            
            if (nums[middle] > nums[right]) {
                left = middle + 1; // If middle is greater than right, the right subarray has the minimum value
            } else {
                right = middle; // Vice versa, but left could be the same as right, so not 'middle - 1'
            }
        }
        return nums[left];
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
