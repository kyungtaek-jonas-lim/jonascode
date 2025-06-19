from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/longest-consecutive-sequence/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 19
	- `Answer`: longestConsecutive
'''

class Solution:

    '''
    BAD EXAMPLE
    O(n log n)
    '''
    # def longestConsecutive(self, nums: List[int]) -> int:
        
    #     if not nums: return 0

    #     nums.sort()
    #     result = 1
    #     temp = 1
    #     for i in range(1, len(nums)):
    #         if nums[i - 1] + 1 == nums[i]:
    #             temp += 1
    #         elif nums[i - 1] < nums[i]:
    #             result = max(temp, result)
    #             temp = 1
        
    #     return max(temp, result)


    '''
    # Option #1
    - O(n)
    '''
    def longestConsecutive(self, nums: List[int]) -> int:
        
        my_set = set(nums)
        result = 0

        for num in my_set:

            # This 'if' sentence is not for every element and while loops only once for each consecutive sequence. So it's not O(n * n)
            if num - 1 not in my_set: # See if it's the beginning of a consecutive sequence 
                temp = 1
                
                while num + 1 in my_set: # To the end of the consecutive sequence
                    num += 1
                    temp += 1

                result = max(result, temp)
        
        return result