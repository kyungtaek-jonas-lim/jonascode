from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber-ii/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: rob
'''
class Solution:

    '''
    # Option #1
    - Dynamic Programming
    - O(n)
    '''
    def rob(self, nums: List[int]) -> int:
        len_nums = len(nums)

        # For short lengths
        if len_nums == 1:
            return nums[0]
        if len_nums <= 3:
            return max(nums)
        
        # [House Robber Logic](https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/python/HouseRobber.py)
        def process(ns: List[int]) -> int:
            prev_2steps: int = 0
            prev_1step: int = ns[0]
            temp: int = 0

            for i in range(1, len(ns)):
                temp = max(prev_1step, prev_2steps + ns[i])
                prev_2steps = prev_1step
                prev_1step = temp

            return prev_1step
        
        # Devide the array into two -> (0 ~ n-2), (1 ~ n-1)
        # And use the same logic as `House Robber`
        return max(process(nums[:-1]), process(nums[1:]))


if __name__ == '__main__':
    sol = Solution()
    print(sol.rob([2,3,2])) # 3
    print(sol.rob([1,2,3,1])) # 4
    print(sol.rob([1,2,3])) # 3
    print(sol.rob([1,3,1,3,100])) # 103