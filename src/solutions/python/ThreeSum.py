from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/3sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 18, 2025
 	- `Answer`: threeSum
'''

class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        # Step 1: Sort the array to enable the two-pointer technique and easy duplicate handling
        my_list = list()
        nums.sort()

        # Step 2: Iterate through the array, selecting the first number in the triplet
        for i in range(len(nums) - 2):

            # Edge case: If the current number is the same as the previous one, skip to avoid duplicates
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            # Step 3: Use the two-pointer approach to find two numbers that sum to -nums[i]
            left, right = i + 1, len(nums) - 1

            while left < right:
                # Step 4: If the sum is zero, store the triplet
                sum = nums[i] + nums[left] + nums[right]
                if sum < 0:
                    # If the sum is too small, move the left pointer to the right to increase the sum
                    left += 1
                elif sum > 0:
                    # If the sum is too large, move the right pointer to the left to decrease the sum
                    right -= 1
                else:
                    # Step 4: If the sum is zero, store the triplet
                    my_list.append([nums[i], nums[left], nums[right]])
                    
                    # Move both pointers to find the next unique triplet
                    left += 1
                    right -= 1

                    # Skip duplicate values on the pointers
                    while left < right and nums[left] == nums[left - 1]:
                        left += 1
                    while left < right and nums[right] == nums[right + 1]:
                        right -= 1
        return my_list

if __name__ == "__main__":
    sol = Solution()
    print(sol.threeSum([-1,0,1,2,-1,-4])) # [[-1,-1,2],[-1,0,1]]
    print(sol.threeSum([0,1,1])) # []
    print(sol.threeSum([0,0,0])) # [[0,0,0]]]
