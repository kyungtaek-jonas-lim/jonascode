package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/3sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 3, 2025
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
        List<List<Integer>> result = new ArrayList<>();
        
        // Sort the array to use the two-pointer technique
        Arrays.sort(nums);
        
        // Iterate through the array, treating each element as the fixed one
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate elements for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1; // Left pointer, starting just after the current element
            int right = nums.length - 1; // Right pointer, starting from the end of the array
            
            // Use two pointers to find the other two elements
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // Found a valid triplet, add it to the result list
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicate elements for the left and right pointers
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // Move both pointers inward after handling duplicates
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // Increase sum by moving the left pointer to the right
                } else {
                    right--; // Decrease sum by moving the right pointer to the left
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
