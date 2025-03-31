from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/combination-sum-iv/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: combinationSum4 / combinationSum4Advanced
'''

class Solution:

    '''
    # Option #1
    - Bottom-Up DP (Tabulation)
    - O(n × target) (n = len(nums))
    '''
    def combinationSum4(self, nums: List[int], target: int) -> int:
        dp = [0] * (target + 1)
        dp[0] = 1  # Base case: one way to make sum = 0

        for t in range(1, target + 1):
            for num in nums:
                if t - num >= 0:
                    dp[t] += dp[t - num]

        return dp[target]


    '''
    Option #2
    - Top-Down with Memoization
    - O(n × target) (n = len(nums))
    '''
    def combinationSum4Advanced(self, nums: List[int], target: int) -> int:
        memo = {}

        def process(t):
            if t < 0:
                return 0
            if t == 0:
                return 1
            if t in memo:
                return memo[t]
            
            result = 0
            for num in nums:
                result += process(t - num)
            memo[t] = result
            return result

        return process(target)


if __name__ == "__main__":
    sol = Solution()
    print(sol.combinationSum4([1, 2, 3], 4)) # 7
    print(sol.combinationSum4([9], 3)) # 0
    print(sol.combinationSum4([1, 2, 3], 32)) # 181997601

    print("---")
    print(sol.combinationSum4Advanced([1, 2, 3], 4)) # 7
    print(sol.combinationSum4Advanced([9], 3)) # 0
    print(sol.combinationSum4Advanced([1, 2, 3], 32)) # 181997601