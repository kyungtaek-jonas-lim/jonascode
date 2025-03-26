from typing import List
'''
# Problem
	- `Link`: https://leetcode.com/problems/product-of-array-except-self/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 26, 2025
	- `Answer`: productExceptSelf
'''
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        
        len_nums = len(nums)
        result = [1] * len_nums
        standard = 1

        for i in range(0, len_nums):
            result[i] = standard
            standard *= nums[i]
        
        standard = 1
        for i in range(len_nums - 1, -1, -1):
            result[i] *= standard
            standard *= nums[i]
            
        return result

if __name__ == '__main__':
    sol = Solution()
    print(sol.productExceptSelf([1,2,3,4])) # [24,12,8,6]
    print(sol.productExceptSelf([-1,1,0,-3,3])) # [0,0,9,0,0]