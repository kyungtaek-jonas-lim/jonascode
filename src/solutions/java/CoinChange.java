package solutions.java;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange / coinChangeAdvanced / coinChangeDfs / coinChangeBfs
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
	- Dynamic Programming (Common) (Bottom-Up)
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
    - https://www.youtube.com/watch?v=KnWorqyDSLA
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
    	return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

	
	/*
	# Option #3
	- DFS + Memoization
    - O(amount × n)
	 */
    public int coinChangeDfs(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins);
        Map<Integer, Integer> memo = new HashMap<>();
        int result = dfs(coins, amount, memo);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int dfs(int[] coins, int goal, Map<Integer, Integer> memo) {
        if (goal == 0) return 0;
        if (memo.containsKey(goal)) return memo.get(goal);
        int result = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int diff = goal - coins[i];
            if (diff < 0) continue;
            else {
                int temp = dfs(coins, diff, memo);
                if (temp == Integer.MAX_VALUE) continue;
                result = Math.min(result, temp + 1);
            }
        }
        memo.put(goal, result);
        return result;
    }


	/*
	# Option #4
	- BFS + Memoization
    - O(amount × n)
	 */
    public int coinChangeBfs(int[] coins, int amount) {

        if (amount == 0) return 0;
        int n = coins.length;
        Arrays.sort(coins);

        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int coin = coins[i];
            if (amount == coin) return 1;
            if (amount < coin) continue;
            deque.offer(new int[] {amount - coin, 2});
        }

        Set<Integer> memo = new HashSet<>();
        while (!deque.isEmpty()) {
            int[] item = deque.pollFirst();
            int goal = item[0], cnt = item[1];
            if (memo.contains(goal)) continue;
            memo.add(goal);

            for (int i = n - 1; i >= 0; i--) {
                int coin = coins[i];
                int diff = goal - coin;
                if (diff < 0) continue;
                if (diff == 0) {
                    return cnt;
                }
                deque.offer(new int[] {diff, cnt + 1});
            }

        }

        return -1;
    }
}
