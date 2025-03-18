package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/3sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 18, 2025
 	- `Answer`: threeSum
 */
public class ThreeSum {

	// Time Limit Exceeded
//    public static List<List<Integer>> threeSum(int[] nums) {
//    	Set<List<Integer>> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (i == j) continue;
//                for (int k = 0; k < nums.length; k++) {
//                    if (k == i) continue;
//                    if (k == j) continue;
//                    if ((nums[i] + nums[j] + nums[k]) == 0) {
//                    	List<Integer> list = new ArrayList<>();
//                        for (int num : new int[] {nums[i], nums[j], nums[k]}) {
//                            list.add(num);
//                        }
//                        Collections.sort(list);
//                        set.add(list);
//                    }
//                }
//            }
//        }
//
//        return new ArrayList<>(set);
//    }

	
    public static List<List<Integer>> threeSum(int[] nums) {
    	// Step 1: Sort the array to make it easier to avoid duplicates and use the two-pointer technique
    	Arrays.sort(nums);
    	
    	// Step 2: Iterate through the array to pick the first element of the triplet
    	List<List<Integer>> result = new ArrayList<>();
    	for (int i = 0; i < nums.length - 2; i++) {
    		
    		// Edge Case: Skip duplicate values to avoid duplicate triplets
    		if (i > 0 && nums[i] == nums[i - 1]) continue;
    		
    		// Step 3: Initialize two pointers for the ramining two numbers
    		int left = i + 1, right = nums.length - 1;
    		
    		while (left < right) {
    			int sum = nums[i] + nums[left] + nums[right];
    			
    			if (sum == 0) {
    				// Step 4: IF the sum is zero, add the triplet to the result
    				result.add(Arrays.asList(nums[i], nums[left], nums[right]));
    				
    				// Move both pointers inward to find the next unique triplet
    				left++;
    				right--;
    				
    				// Skip duplicate values for the pointers
    				while (left < right && nums[left] == nums[left - 1])
    					left++;
    				while (left < right && nums[right] == nums[right + 1])
    					right--;
    			} else if (sum < 0) {
    				// Step 5: If the sum is too small, move the left pointer to increase the sum
    				left++;
    			} else {
    				// Step 6: If the sum is too large, move the right pointer to decrease the sum
    				right--;
    			}
    		}
    	}
    	return result;
    }

	
	public static void main(String[] args) {
	    System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4})); // [[-1,-1,2],[-1,0,1]]
	    System.out.println(threeSum(new int[] {0,1,1})); // []
	    System.out.println(threeSum(new int[] {0,0,0})); // [[0,0,0]]]
	}
}
