from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 6, 2025
 	- `Answer`: rob / robAdvanced
'''
class Solution:
    
    '''
    # Option #1
    - Dynamic Programming
    - O(n)
    - ref: https://www.youtube.com/watch?v=73r3KWiEvyk
    '''
    def rob(self, nums: List[int]) -> int:

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
    - Recursive method with Memoization
    - O(n)
    '''
    def robAdvanced(self, nums: List[int]) -> int:
        
        memo = {}

        def process(index: int) -> int:

            # If it's eqaul or greater than the length of nums
            if index >= len(nums):
                return 0
            
            # Cache
            if index in memo:
                return memo[index]
            
            # Take this house
            take = nums[index] + process(index + 2)
            
            # Skip this house
            skip = process(index + 1)

            # Get the maximum
            memo[index] = max(take, skip)
            return memo[index]

        return process(0)
    

if __name__ == '__main__':
    sol = Solution()
    print(sol.rob([1,2,3,1])) # 4
    print(sol.rob([2,7,9,3,1])) # 12

    print("---")
    print(sol.robAdvanced([1,2,3,1])) # 4
    print(sol.robAdvanced([2,7,9,3,1])) # 12