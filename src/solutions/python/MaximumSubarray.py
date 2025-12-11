from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxSubArray / maxSubArray2 / maxSubArray3
'''

class Solution:

    '''
    # Option #1
    - O(n)
	'''
    def maxSubArray(self, nums: List[int]) -> int:
        curr_max, global_max = nums[0], nums[0]
        for num in nums[1:]:
            curr_max = max(curr_max + num, num)
            global_max = max(curr_max, global_max)
        return global_max


    '''
    # Option #2
    - O(n)
	'''
    def maxSubArray2(self, nums: List[int]) -> int:
        curr_sum = nums[0]
        result = curr_sum
        for num in nums[1:]:
            if num > curr_sum + num:
                curr_sum = num
            else:
                curr_sum += num
            result = max(result, curr_sum)
        return result
    

    '''
    # Option #3
    - O(n)
	'''
    def maxSubArray3(self, nums: List[int]) -> int:
        curr_sum, result = 0, nums[0]
        for num in nums:
            if curr_sum < 0:
                curr_sum = 0
            curr_sum += num
            result = max(result, curr_sum)
        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxSubArray([-2,1,-3,4,-1,2,1,-5,4]))
    print(sol.maxSubArray([1]))
    print(sol.maxSubArray([5,4,-1,7,8]))
    # print(sol.maxSubArray())