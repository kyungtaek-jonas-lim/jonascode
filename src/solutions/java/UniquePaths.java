package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/unique-paths/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: uniquePaths / uniquePathsAdvanced
 */
public class UniquePaths {
	
	public static void main(String[] args) {
		System.out.println(uniquePaths(3, 7)); // 28
		System.out.println(uniquePaths(3, 2)); // 3
		
		System.out.println("---");
		System.out.println(uniquePathsAdvanced(3, 7)); // 28
		System.out.println(uniquePathsAdvanced(3, 2)); // 3
	}
	

    /*
    # Option #1
    - O (m * n)
    - 2D Array
     */
    public static int uniquePaths(int m, int n) {
        
    	// Initialize m * n 2d array
    	int[][] dp = new int[m][n];
    	
    	for (int i = 0; i < m; i++) {
    		dp[i][0] = 1; // To get to (i, 0), there's only one way. (set to 1)
    		for (int j = 1; j < n; j++) {
    			if (i == 0) {
    				dp[i][j] = 1; // To get to (0, j), there's only one way. (set to 1)
    			} else { // To get to (i, j), the number of way will be the way to get to (i - 1, j) and (i, j - 1) as the robot only can move either right or down at any point in time.
    				dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
    			}
    		}
    	}
    	
    	// The number of ways to bottom right corner
    	return dp[m - 1][n - 1];
    }
    
    /*
    # Option #2
    - O (m * n)
    - 1D Array
     */
    public static int uniquePathsAdvanced(int m, int n) {
    	
    	// Initialize a 1D DP array with size n
    	int[] dp = new int[n];
    	
    	// For the first row, all values are 1 (only one way: move right)
    	Arrays.fill(dp, 1);
    	
    	// Start from the second row
    	for (int i = 1; i < m; i++) {
    		for (int j = 1; j < n; j++) {
    			// The number of ways to reach (i, j) is the sum of:
    			//	- ways to reach (i-1, j)
    			//	- ways to reach (i, j-1)
    			dp[j] += dp[j - 1];
    		}
    	}
    	
    	// The number of ways to bottom right corner
    	return dp[n - 1];
    }
}
