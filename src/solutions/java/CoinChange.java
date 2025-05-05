package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange / coinChangeAdvanced
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/CoinChange.md
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
	


	/*
	# Option #1
	- Dynamic Programming (Common)
	- O(amount × n) (n = the number of coins)
	- ref) https://www.youtube.com/watch?v=H9bfqozjoqs
	 */
    public static int coinChange(int[] coins, int amount) {
    	int[] dp = new int[amount + 1];
    	Arrays.fill(dp, amount + 1); // The Max Coin Value + 1
    	dp[0] = 0; // Initiate the first value (when the amount is 0)
    	
    	for (int a = 0; a <= amount; a++) { // Bottom Top Depending on the Amount
    		for (int coin: coins) {
    			int key = a - coin;
    			if (key < 0) continue;
    			dp[a] = Math.min(dp[a], dp[key] + 1);
    		}
    	}
    	
    	return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
	
	
	/*
	# Option #2
	- Dynamic Programming (Advanced)
	- O(amount × n) (n = the number of coins)
	 */
    public static int coinChangeAdvanced(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin: coins) {
        	for (int target = coin; target <= amount; target++) {
        		dp[target] = Math.min(dp[target], dp[target - coin] + 1);
        	}
        }
    	return dp[amount] > amount ? -1 : dp[amount];
    }
}
