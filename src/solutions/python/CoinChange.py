from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/CoinChange.md
'''
class Solution:
    
    # # Greedy with Backtracking
    # # Time Limit Exceeded
    # def coinChange(self, coins: List[int], amount: int) -> int:
    #     # 1. Edge Case
    #     if amount == 0:
    #         return 0 # If the amount is 0, no coins are needed, so return 0
        
    #     # 2. Preparation
    #     coins.sort(reverse=True) # Sort coins in descending order to prioritize larger coins
    #     min_coins = float('inf') # Variable to store the minimum number of coins (initialized to infinity)

    #     # 3. Define Search Inner Function
    #     def dfs(index, remaining, count):
    #         '''
    #         DFS (Depth-First Search) function to find the minimum number of coins.
    #         index: Current index in the coins list
    #         remaining: Remaining amount to be made up
    #         count: Number of coins used so far
    #         '''
    #         nonlocal min_coins # Use the min_coins variable from the outer function
    #         if remaining == 0: # If the remaining amount is 0, update the minimum coin count
    #             min_coins = min(min_coins, count)
    #             return
    #         if index >= len(coins): # If all coins have been checked and no solution is found, return
    #             return
            
    #         max_use = remaining // coins[index] # Maximum number of times the current coin can be used
    #         for i in range(max_use, -1, -1): # Try using the most coins first (backtracking optimization)
    #             if count + i >= min_coins: # Pruning: Stop if count already exceeds min_couns
    #                 break
    #             dfs(index + 1, remaining - coins[index] * i, count + i) # Move to the next coin
        
    #     # 4. Call Search Function
    #     dfs(0, amount, 0) # Start DFS traversal
    #     return min_coins if min_coins != float('inf') else -1 # Return -1 if no valid combination is found
    
    # Dynamic Programming
    # O(n * amount)
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [0] + [float('inf')] * amount
        for coin in coins:
            for target in range(coin, amount + 1):
                dp[target] = min(dp[target], dp[target - coin] + 1)
        return dp[amount] if dp[amount] != float('inf') else -1


if __name__ == "__main__":
    sol = Solution()
    print(sol.coinChange([1,2,5], 11)) # 3
    print(sol.coinChange([2], 3)) # -1
    print(sol.coinChange([1], 0)) # 0
    print(sol.coinChange([1], 2)) # 2
    print(sol.coinChange([186,419,83,408], 6249)) # 20
    print(sol.coinChange([411,412,413,414,415,416,417,418,419,420,421,422], 9864)) # 24