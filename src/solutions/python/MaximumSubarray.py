from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 23, 2025
 	- `Answer`: maxSubArray
'''

class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        result = -10 **4
        accumulated_num = 0
        for num in nums:
            if accumulated_num + num < num:
                accumulated_num = num
            else:
                accumulated_num += num
            result = max(result, accumulated_num)
        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxSubArray([-2,1,-3,4,-1,2,1,-5,4]))
    print(sol.maxSubArray([1]))
    print(sol.maxSubArray([5,4,-1,7,8]))
    # print(sol.maxSubArray())