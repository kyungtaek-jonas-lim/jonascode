from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: rob / robAdvanced
'''
class Solution:
    '''
    # Option #1
    - Recursive method with Memoization
    - O(n × S): n is the number of indices, and S is the number of possible cumulative result values.
    '''
    def rob(self, nums: List[int]) -> int:
        memo = {}

        def process(index: int, result: int):
            if index >= len(nums):
                return result
            memoKey = f"{index},{result}"
            if memoKey in memo:
                return memo[memoKey]

            result1 = process(index + 2, result + nums[index])
            result2 = process(index + 3, result + nums[index])
            memo[memoKey] = max(result1, result2)
            return memo[memoKey]
        
        return max(process(0, 0), process(1, 0))
    
    '''
    # Option #2
    - Dynamic Programming
    - O(n)
    '''
    def robAdvanced(self, nums: List[int]) -> int:
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

if __name__ == '__main__':
    sol = Solution()
    print(sol.rob([1,2,3,1])) # 4
    print(sol.rob([2,7,9,3,1])) # 12