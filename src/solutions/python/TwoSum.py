
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/two-sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 15, 2025
 	- `Answer`: twoSum
'''

from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dict_temp = {}
        for index, num in enumerate(nums):
            if num in dict_temp:
                return [dict_temp.get(num), index]
            dict_temp[target - num] = index
        

solution = Solution()
if __name__ == "__main__":
    print(solution.twoSum(nums=[2, 7, 11, 15], target=9))