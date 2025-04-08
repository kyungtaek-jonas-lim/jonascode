from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/maximum-product-subarray/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 28, 2025
 	- `Answer`: maxProduct / maxProductAdvanced
'''

class Solution:
    
    '''
    # Option #1
    - O(n)
    '''
    def maxProduct(self, nums: List[int]) -> int:
        max_val: int = nums[0]
        min_val: int = nums[0]
        result: int = nums[0]
        for num in nums[1:]:
            max_val_temp = max_val
            max_val = max(max(max_val * num, min_val * num), num)
            min_val = min(min(max_val_temp * num, min_val * num), num)

            if max_val > result:
                result = max_val
        return result
    

    '''
    # Option #2
    - O(n)
    '''
    def maxProductAdvanced(self, nums: List[int]) -> int:
        max_val = min_val = result = nums[0]
        
        for num in nums[1:]:
            if num < 0:
                max_val, min_val = min_val, max_val
            
            max_val = max(num, num * max_val)
            min_val = min(num, num * min_val)

            result = max(result, max_val)
        return result


if __name__ == "__main__":
    sol = Solution()
    print(sol.maxProduct([0,2,3,-2,4,0]))
    print(sol.maxProduct([2,3,-2,4]))
    print(sol.maxProduct([-2,0,-1]))
    print(sol.maxProduct([2,-5,-2,-4,3])) # 24

    print("---")
    print(sol.maxProductAdvanced([0,2,3,-2,4,0]))
    print(sol.maxProductAdvanced([2,3,-2,4]))
    print(sol.maxProductAdvanced([-2,0,-1]))
    print(sol.maxProductAdvanced([2,-5,-2,-4,3])) # 24
    # print(sol.maxProduct()) 