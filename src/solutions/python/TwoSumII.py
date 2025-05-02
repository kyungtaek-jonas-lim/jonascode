from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: May 2, 2025
	- `Answer`: twoSum
'''
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        
        n = len(numbers)
        
        left, right = 0, n - 1
        while left < right:
            s = numbers[left] + numbers[right]
            if s < target:
                left += 1
            elif s == target:
                return [left + 1, right + 1]
            else:
                right -= 1
        return None