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
    '''
    def rob(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return nums[0]
        
    	# The goal is the max value when you get to the last index.
    	# Find all the max values for each index
        prev_2steps: int = 0
        prev_1step: int = nums[0]
        temp: int = 0
        for i in range(1, len(nums)):
            temp = max(prev_1step, prev_2steps + nums[i]) # compare 1 step before result + {2 steps before result + current result}
            prev_2steps = prev_1step
            prev_1step = temp # Now `prev_1step` becomes current
        return prev_1step
    

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