from typing import List
import collections
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/coin-change/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 17, 2025
 	- `Answer`: coinChange / coinChangeAdvanced / coinChangeBfs / coinChangeDfs
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

    '''
	# Option #1
	- Dynamic Programming (Common)
	- O(amount × n) (n = the number of coins)
	- ref) https://www.youtube.com/watch?v=H9bfqozjoqs
    '''
    def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [amount + 1] * (amount + 1) # The Max Coin Value + 1
        dp[0] = 0 # Initiate the first value (when the amount is 0)

        for a in range(amount + 1): # Bottom Top Depending on the Amount
            for coin in coins:
                key = a - coin
                if key < 0:
                    continue
                dp[a] = min(dp[a], dp[key] + 1)
        
        return dp[amount] if dp[amount] != amount + 1 else -1
    

    '''
	# Option #2
	- Dynamic Programming (Advanced)
	- O(amount × n) (n = the number of coins)
	- ref)  https://www.youtube.com/watch?v=KnWorqyDSLA
    '''
    def coinChangeAdvanced(self, coins: List[int], amount: int) -> int:
        dp = [float('inf')] * (amount + 1)
        dp[0] = 0
        for coin in coins:
            for i in range(coin, amount + 1):
                dp[i] = min(dp[i], dp[i - coin] + 1)
        return -1 if dp[amount] == float('inf') else dp[amount]
    
    
    '''
	# Option #3
	- BFS + Memoization
    - O(amount × n)
    '''
    def coinChangeBfs(self, coins: List[int], amount: int) -> int:
        
        if amount == 0: return 0
        coins.sort(reverse=True)

        deque = collections.deque()
        for coin in coins:
            diff = amount - coin
            if diff < 0: continue
            if diff == 0: return 1
            deque.append((diff, 2))

        memo = set()
        while deque:
            goal, cnt = deque.popleft()
            if goal in memo:
                continue
            memo.add(goal)
            for coin in coins:
                diff = goal - coin
                if diff < 0: continue
                if diff == 0: return cnt
                deque.append((diff, cnt + 1))
        return -1

        
    '''
	# Option #4
	- DFS + Memoization
    - O(amount × n)
    '''
    def coinChangeDfs(self, coins: List[int], amount: int) -> int:
        
        memo = {}
        coins.sort(reverse=True)
        _MAX_NUMBER = float('inf')

        def dfs(goal: int) -> int:
            if goal == 0:
                return 0
            if goal in memo:
                return memo[goal]
            
            res = _MAX_NUMBER
            if goal < 0:
                return res
            
            for coin in coins:
                tmp = dfs(goal - coin)
                if tmp + 1 >= res: continue
                res = tmp + 1
            
            memo[goal] = res
            return res
        
        result = dfs(amount)
        return -1 if result == _MAX_NUMBER else result


if __name__ == "__main__":
    sol = Solution()
    print(sol.coinChange([1,2,5], 11)) # 3
    print(sol.coinChange([2], 3)) # -1
    print(sol.coinChange([1], 0)) # 0
    print(sol.coinChange([1], 2)) # 2
    print(sol.coinChange([186,419,83,408], 6249)) # 20
    print(sol.coinChange([411,412,413,414,415,416,417,418,419,420,421,422], 9864)) # 24