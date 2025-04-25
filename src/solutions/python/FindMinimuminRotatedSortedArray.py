from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 5, 2025
 	- `Answer`: findMin / findMinAdvanced
'''

class Solution:
    
    '''
    # Option #1
    '''
    def findMin(self, nums: List[int]) -> int:
        nums.sort()
        return nums[0]
    
    '''
    # Option #2
    '''
    def findMinAdvanced(self, nums: List[int]) -> int:
        prev = nums[0]
        for num in nums[1:]:
            if num < prev:
                return num
            prev = num
        return nums[0]

if __name__ == "__main__":
    sol = Solution()
    print(sol.findMin([3,4,5,1,2])) # 1
    print(sol.findMin([4,5,6,7,0,1,2])) # 0
    print(sol.findMin([11,13,15,17])) # 11