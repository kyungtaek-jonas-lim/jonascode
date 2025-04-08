from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxSubArray
'''

class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        sum = nums[0]
        result = sum
        for num in nums[1:]:
            if num > sum + num:
                sum = num
            else:
                sum += num
            result = max(result, sum)
        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxSubArray([-2,1,-3,4,-1,2,1,-5,4]))
    print(sol.maxSubArray([1]))
    print(sol.maxSubArray([5,4,-1,7,8]))
    # print(sol.maxSubArray())