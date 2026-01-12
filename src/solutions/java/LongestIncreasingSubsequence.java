package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-increasing-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 18, 2025
 	- `Answer`: lengthOfLIS / lengthOfLISAdvanced / lengthOfLISMap / lengthOfLISDp
 */
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		
		System.out.println(lengthOfLIS(new int[] {10,9,2,5,3,7,101,18})); // 4
		System.out.println(lengthOfLIS(new int[] {0,1,0,3,2,3})); // 4
		System.out.println(lengthOfLIS(new int[] {7,7,7,7,7,7,7})); // 1
		
		System.out.println("---");
		System.out.println(lengthOfLISAdvanced(new int[] {10,9,2,5,3,7,101,18})); // 4
		System.out.println(lengthOfLISAdvanced(new int[] {0,1,0,3,2,3})); // 4
		System.out.println(lengthOfLISAdvanced(new int[] {7,7,7,7,7,7,7})); // 1
	}

    // Dynamic Programming
    // O(n^2)
    public static int lengthOfLIS(int[] nums) {
    	
    	int numsLen = nums.length;
    	
    	// dp[i] represents the length of the longest increasing subsequence ending at index i
    	int[] dp = new int[numsLen];
    	
    	// Initialize the dp array where each element is 1, since every number is at least a subsequence of length 1
    	Arrays.fill(dp, 1);

    	int result = 0;
    	// Iterate over each element in the array
    	for (int i = 0; i < numsLen; i++) {
    		
    		// For each element nums[i], check all previous elements nums[j] (where j < i)
    		for (int j = 0; j < i; j++) {

    			// If nums[j] < nums[i], then we can extend the subsequence ending at j
    			if (nums[j] < nums[i]) {
    				
    				// Update dp[i] to the maximum of its current value and dp[j] + 1
    				dp[i] = Math.max(dp[i], dp[j] + 1);
    			}
    		}
			result = Math.max(dp[i], result);
    	}
    	
    	// The result is the maximum value in dp which gives us the length of the longest subsequence
    	return result;
    }
    

    // Binary Search with Dynamic Programming
    // O(n log n)
    public static int lengthOfLISAdvanced(int[] nums) {
    	// tails will store the smallest possible tail value for increasing subsequences of different lengths
    	List<Integer> tails = new ArrayList<>();
    	
    	// Iterate through each number in the nums array
    	for (int num : nums) {
			// Find the index in tails where num can replace or extend the current subsequence
    		int idx = Collections.binarySearch(tails, num); // Binary search for the index of the smallest value >= num
    		
    		if (idx < 0) {
    			idx = -(idx + 1); // Convert negative index to the insertion point
    		}
    		
    		if (idx == tails.size()) { // If num is larger than all elements in tails, append it
    			tails.add(num);
    		} else { // Otherwise, replace the element at idx with num, as it would form a smaller tail
    			tails.set(idx,  num);
    		}
    	}
    	
    	// The length of the tails list represents the length of the longest increasing subsequence
    	return tails.size();
    }

    
    // Map
    // O(n^2)
	// Jan 12, 2026
    public int lengthOfLISMap(int[] nums) {
        
        Map<Integer, Integer> memo = new HashMap<>();
        int result = 0;

        for (int num: nums) {
            List<Integer> keys = new ArrayList<>(memo.keySet());
            for (int n: keys) { // Use 'list' to prevent from changing the size of the dictionary during iteration such as 'ConcurrentModificationException'
                if (n >= num) continue;
                int currCnt = memo.get(n), existingCnt = memo.getOrDefault(num, 0);
                result = Math.max(currCnt + 1, result);
                if (currCnt >= existingCnt) {
                    memo.put(num, currCnt + 1);
                }
            }
            if (!memo.containsKey(num)) {
                memo.put(num, 1);
            }
        }

        return result == 0 ? 1 : result;
    }

    
    // Dynamic Programming
    // O(n^2)
	// Jan 12, 2026
    public int lengthOfLISDp(int[] nums) {
        final int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] >= nums[i]) continue;
                dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int result = 1;
        for (int num: dp) {
            result = Math.max(num, result);
        }
        return result;
    }
    
}
