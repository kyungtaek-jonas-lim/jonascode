package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange
 */
public class CoinChange {
	
	public static void main(String[] args) {
		System.out.println(coinChange(new int[] {1,2,5}, 11)); // 3
		System.out.println(coinChange(new int[] {2}, 3)); // -1
		System.out.println(coinChange(new int[] {1}, 0)); // 0
		System.out.println(coinChange(new int[] {1}, 2)); // 2
		System.out.println(coinChange(new int[] {186,419,83,408}, 6249)); // 20
		System.out.println(coinChange(new int[] {411,412,413,414,415,416,417,418,419,420,421,422}, 9864)); // 24
	}

    // Dynamic Programming
    // O(n * amount)
    public static int coinChange(int[] coins, int amount) {
        // Create a dp array where dp[i] represents the minimum number of coins needed to make amount i
    	int[] dp = new int[amount + 1];
    	
    	// Initialize the dp array with a large value (infinity)
    	Arrays.fill(dp, amount + 1);
    	dp[0] = 0; // Base case: 0 coins are needed to make an amount of 0
    	
    	// Iterate over all coins
    	for (int coin: coins) {
    		// For each coin, check all amounts from coin to the target amount
    		for (int i = coin; i <= amount; i++) {
    			// Update dp[i] with the minimum number of coins needed
    			dp[i] = Math.min(dp[i], dp[i - coin] + 1);
    		}
    	}
    	
    	// If dp[amount] is still infinity, return -1 as it is not possible to make that amount
    	return dp[amount] > amount ? -1 : dp[amount];
    }
}
