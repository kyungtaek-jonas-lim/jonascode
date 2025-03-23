package solutions.java;

import java.util.Scanner;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/climbing-stairs/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 5, 2025
 	- `Answer`: climbStairs / climbStairsAdvanced
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/ClimbingStairs.md
 */

public class ClimbingStairs {
	
	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the stairs to the top: ");
        int top = scanner.nextInt();
		
//        final int output = climbStairs(top);        
        final int output = climbStairsAdvanced(top);        
		System.out.println("[RESULT] The number of distinct ways is `" + output + "`");
		
		scanner.close();
	}

	/*
	 * Option #1 
	 * Common way
	 * O(2^n)
	 */
    // Time Limit Exceeded
    public static int climbStairs(int n) {
        return move(0, 0, n);
    }
    
    private static int move(int current, int cnt, int target) {
        // Validation
        if (current > target) return cnt;
        if (current == target) return cnt + 1;

        int resultCnt = cnt;
        resultCnt += move(current + 1, cnt, target);
        resultCnt += move(current + 2, cnt, target);

        // Result
        return resultCnt;
    }

	/*
	 * Option #2
	 * Advanced way
	 * O(n)
	 */
    public static int climbStairsAdvanced(int n) {

        // Base cases:
        // If there is only 1 step, there's only 1 way to climb it (just take 1 step).
        // If there are 2 steps, there are 2 ways:
        // 1. Take 1 step twice (1 + 1)
        // 2. Take 2 steps at once
        if (n <= 2) return n;

        // Initialize variables:
        // 'waysToTwoStepsBefore' represents the total number of ways to reach the step two levels below the current step (f(n-2)).
        // 'waysToOneStepBefore' represents the total number of ways to reach the step one level below the current step (f(n-1)).
        int waysToTwoStepsBefore = 1; // Equivalent to f(1)
        int waysToOneStepBefore = 2;  // Equivalent to f(2)

        // Variable to store the total number of ways to reach the current step
        int waysToCurrentStep = 0;

        // Iterate through steps from 3 to n
        for (int currentStep = 3; currentStep <= n; currentStep++) {
            // The total ways to reach the current step is the sum of:
            // 1. Ways to reach the step two levels below (f(n-2))
            // 2. Ways to reach the step one level below (f(n-1))
            waysToCurrentStep = waysToTwoStepsBefore + waysToOneStepBefore;

            // Update variables for the next iteration:
            // Move the previous step counts forward:
            // - 'waysToOneStepBefore' becomes 'waysToTwoStepsBefore'
            // - 'waysToCurrentStep' becomes 'waysToOneStepBefore'
            waysToTwoStepsBefore = waysToOneStepBefore;
            waysToOneStepBefore = waysToCurrentStep;
        }

        // After the loop, 'waysToOneStepBefore' contains the result for n steps
        return waysToOneStepBefore;

    }
        
}











