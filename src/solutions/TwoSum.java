package solutions;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/two-sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 4, 2025
 	- `Answer`: twoSum / twoSumAdvanced 
 */

class TwoSum {
	public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Input the size of the array
        System.out.print("Enter the size of the array: ");
        int sizeOfArray = scanner.nextInt();
        
        // Input the array elements
        int[] nums = new int[sizeOfArray];
        System.out.println("Enter the element of the array");
        for (int i = 0 ; i < sizeOfArray; i++) {
            System.out.print(i+1 + ": ");
        	nums[i] = scanner.nextInt();
        }
        
        // Input the target
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();
        
        // Get the result and print the indices
//        int[] result = twoSum(nums, target);
        int[] result = twoSumAdvanced(nums, target);
        if(result != null) {
        	System.out.println("[RESULT] target: " + target + ", result: ["
        			+ result[0] + ", " + result[1] + "]");
        } else {
        	System.out.println("No solution found");
        }
        
        scanner.close();
        
	}
	
	/*
	 * Option #1 
	 * Common way
	 * O(n^2)
	 */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0 ; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    /*
     * Option #2
     * Advanced way
     * O(n)
     */
    public static int[] twoSumAdvanced(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < nums.length; i ++) {
    		if (!map.containsKey(nums[i])) {
	    		int difference = target - nums[i];
	    		map.put(difference, i);
    		} else {
    			return new int[] {map.get(nums[i]), i};
    		}
    	}
        return null;
    }
}















