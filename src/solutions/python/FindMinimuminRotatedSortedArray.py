from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 5, 2025
 	- `Answer`: findMin / findMinAdvanced / findMinBest
'''

class Solution:

    '''
    # Option #1
    - O(n log n)
    '''
    def findMin(self, nums: List[int]) -> int:
        nums.sort()
        return nums[0]
    
    '''
    # Option #2
    - O(n)
    '''
    def findMinAdvanced(self, nums: List[int]) -> int:
        prev = nums[0]
        for num in nums[1:]:
            if num < prev:
                return num
            prev = num
        return nums[0]
    
    '''
    # Option #3
    - O(log n)
	- Binary Search
	- ref: https://www.youtube.com/watch?v=nIVW4P8b1VA
    '''
    def findMinBest(self, nums: List[int]) -> int:

        result = nums[0]
        left, right = 0, len(nums) - 1

        while left <= right:

            num_left = nums[left]
            num_right = nums[right]

            # When it's sorted array
            if num_left < num_right:
                return min(result, num_left)
            
            # If left subarray is sorted, check right subarray and vice versa.
            middle = (left + right) // 2
            result = min(result, nums[middle])
            if num_left <= nums[middle]:
                left = middle + 1
            else:
                right = middle - 1

        return result
    

if __name__ == "__main__":
    sol = Solution()
    print(sol.findMin([3,4,5,1,2])) # 1
    print(sol.findMin([4,5,6,7,0,1,2])) # 0
    print(sol.findMin([11,13,15,17])) # 11