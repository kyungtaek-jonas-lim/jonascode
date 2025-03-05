from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 5, 2025
 	- `Answer`: findMin
'''

class Solution:
    def findMin(self, nums: List[int]) -> int:
        nums.sort()
        return nums[0]

if __name__ == "__main__":
    sol = Solution()
    print(sol.findMin([3,4,5,1,2])) # 1
    print(sol.findMin([4,5,6,7,0,1,2])) # 0
    print(sol.findMin([11,13,15,17])) # 11