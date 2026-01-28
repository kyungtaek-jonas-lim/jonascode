from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 6, 2025
 	- `Answer`: robDpWithConstantSpace / robRecursiveMemoization / robDpArray
'''
class Solution:
    
    '''
    # Option #1
    - DP with Constant Space
    - O(n)
    - Space Complexity: O(1)
    - ref: https://www.youtube.com/watch?v=73r3KWiEvyk
    '''
    def robDpWithConstantSpace(self, nums: List[int]) -> int:

        def process(nums: List[int]) -> int:
            
            rob1, rob2 = 0, 0
            
            # [rob1, rob2, n, n+1, ...]
            for num in nums:
                temp = max(rob1 + num, rob2)
                rob1 = rob2
                rob2 = temp
            
            return rob2

        return process(nums)
    

    '''
    # Option #2
    - Recursive + Memoization
    - O(n)
    - Space Complexity: O(n)
    '''
    def robRecursiveMemoization(self, nums: List[int]) -> int:
        
        n, memo = len(nums), {}
        def dfs(x: int):
            if x >= n:
                return 0
            if x in memo:
                return memo[x]
            
            res = max(dfs(x + 2) + nums[x], dfs(x + 1))
            memo[x] = res
            return res
        
        return dfs(0)
    

    '''
    # Option #3
    - DP Array
    - Common
    - O(n)
    - Space Complexity: O(n)
	- Jan 27, 2026
    '''
    def robDpArray(self, nums: List[int]) -> int:
        
        n = len(nums)
        dp = [0] * (n + 1)
        dp[1] = nums[0]

        for i in range(1, n):
            dp[i + 1] = max(dp[i - 1] + nums[i], dp[i])
        
        return dp[n]
if __name__ == '__main__':
    sol = Solution()
    print(sol.rob([1,2,3,1])) # 4
    print(sol.rob([2,7,9,3,1])) # 12

    print("---")
    print(sol.robAdvanced([1,2,3,1])) # 4
    print(sol.robAdvanced([2,7,9,3,1])) # 12