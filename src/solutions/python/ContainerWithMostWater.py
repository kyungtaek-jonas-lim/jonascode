from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/container-with-most-water/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 3, 2025
 	- `Answer`: maxArea / maxAreaSimple
'''

class Solution:

    # Not efficient (Time Limit Exceeded) (O(n^2))
    # def maxArea(self, height: List[int]) -> int:
    #     result = 0
    #     h = 0
    #     w = 0
    #     for i, v1 in enumerate(height):
    #         for j, v2 in enumerate(height[i + 1:], start=i + 1):
    #             w = j - i
    #             h = min(v1, v2)
    #             print(i, j, w, h)
    #             result = max(result, w * h)
    #     return result

    '''
    - Option #1 
    - Common way
	- O(n)
	- We move the smaller pointer inward at each step because the area is limited by the smaller height, and moving it gives a chance to find a taller boundary for a larger area.
    '''
    def maxArea(self, height: List[int]) -> int:
        left, right = 0, len(height) -1
        result, area = 0, 0
        
        while left < right:
            if height[left] < height[right]:
                area = height[left] * (right - left)
                left += 1
            else:
                area = height[right] * (right - left)
                right -= 1
            result = max(result, area)

        return result
    

    '''
    - Option #2
    - Simple way
	- O(n)
    - Jan 5, 2026
    '''
    def maxAreaSimple(self, height: List[int]) -> int:
        n = len(height)
        l, r = 0, n - 1
        result = 0

        while (l < r):
            result = max(result, (r - l) * min(height[l], height[r]))
            if height[l] < height[r]:
                l += 1
            else:
                r -= 1
        
        return result
    

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxArea([1,8,6,2,5,4,8,3,7]))
    print(sol.maxArea([1,1]))