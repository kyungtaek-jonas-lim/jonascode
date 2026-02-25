from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/jump-game/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: canJump / canJumpBetter / canJumpAdvanced / canJumpDfs
'''
class Solution:
    
    '''
    # Option #1
    - Dynamic Programming
    - Bottom-Up
    - O(n^2)
    '''
    def canJump(self, nums: List[int]) -> bool:
        
        # Make the same length array and put the numbers of ways to each index.
        len_nums = len(nums)
        dp = [False] * len_nums
        dp[0] = True # Initial position (reached)

        for i in range(0, len_nums):
            if not dp[i]: # If it this index is not reachable, skip it.
                continue
            
            # If it gets to the last index, return true
            if i + nums[i] >= len_nums - 1:
                return True
            
            # Update the possibility
            for j in range(i + nums[i], i - 1, -1):
                if not dp[j]:
                    dp[j] = True

        # If the last index is not reachable, return false
        return False


    '''
    # Option #2
    - Greedy
    - O(n)
    '''
    def canJumpBetter(self, nums: List[int]) -> bool:
        n = len(nums)
        max_length = 0

        for i in range(n):
            if i > max_length:
                return False
            max_length = max(max_length, i + nums[i])

        return True
    

    '''
    # Option #3
    - Greedy
    - O(n)
    - ref: https://www.youtube.com/watch?v=Yan0cv2cLy8
    '''
    def canJumpAdvanced(self, nums: List[int]) -> bool:

        len_nums = len(nums)

        # The goal is the last index.
        goal = len_nums - 1

        # From the last index, let's move the goal forwards.
        for i in range(len_nums - 1, -1, -1):
            if goal <= i + nums[i]: # If it can reach the goal, move the goal forward.
                goal = i
        
        # If the goal went all the way to the first, it means the first index can reach the last index.
        return goal == 0
    

    '''
    # Option #4
    - DFS + Memoization
    - O(n^2)
    '''
    def canJumpDfs(self, nums: List[int]) -> bool:
        
        n, memo = len(nums), {}

        def dfs(index: int) -> bool:
            if index + nums[index] >= n - 1:
                return True
            if index in memo:
                return memo[index]

            for i in range(index + 1, index + nums[index] + 1):
                if dfs(i):
                    return True
            memo[index] = False
            return False

        return dfs(0)

if __name__ == '__main__':
    sol = Solution()
    print(sol.canJump([2,3,1,1,4])) # True
    print(sol.canJump([3,2,1,0,4])) # False

    print("--")
    print(sol.canJumpAdvanced([2,3,1,1,4])) # True
    print(sol.canJumpAdvanced([3,2,1,0,4])) # False