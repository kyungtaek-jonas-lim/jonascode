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
    '''
    def findMinBest(self, nums: List[int]) -> int:
        left, right = 0, len(nums) - 1
        
        while left < right:
            mid = (left + right) // 2
            
            # If middle is greater than right, the right subarray has the minimum value
            if nums[mid] > nums[right]:
                left = mid + 1
            else:
                right = mid # Vice versa, but left could be the same as right, so not 'middle - 1'
        
        return nums[left]
    

if __name__ == "__main__":
    sol = Solution()
    print(sol.findMin([3,4,5,1,2])) # 1
    print(sol.findMin([4,5,6,7,0,1,2])) # 0
    print(sol.findMin([11,13,15,17])) # 11