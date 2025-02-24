from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/contains-duplicate/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 24, 2025
 	- `Answer`: containsDuplicate
'''

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        my_set = set()
        for num in nums:
            if num in my_set:
                return True
            my_set.add(num)
        return False
        
if __name__ == "__main__":
    sol = Solution()
    print(sol.containsDuplicate([1,2,3,1]))
    print(sol.containsDuplicate([1,2,3,4]))
    print(sol.containsDuplicate([1,1,1,3,3,4,3,2,4,2]))