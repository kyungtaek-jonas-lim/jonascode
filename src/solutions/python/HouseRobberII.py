from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/house-robber-ii/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Aor 6, 2025
 	- `Answer`: rob / robDp / robDp2
'''
class Solution:

    '''
    # Option #1
    - Recursive method with Memoization
    - O(n)
    '''
    def rob(self, nums: List[int]) -> int:
        
        # For short lengths
        if len(nums) <= 3:
            return max(nums)
        
        memo = {}

        # [House Robber Logic](https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/python/HouseRobber.py)
        def process(index: int, sub_nums: List[int]) -> int:

            if index >= len(sub_nums):
                return 0
            if index in memo:
                return memo[index]
            
            take = sub_nums[index] + process(index + 2, sub_nums)
            skip = process(index + 1, sub_nums)

            memo[index] = max(take, skip)
            return memo[index]

        left: int = process(0, nums[:-1])
        memo.clear()
        right: int = process(0, nums[1:])
        return max(left, right)


    '''
    # Option #2
    - Dynamic Programming
    - O(n)
    - Space Complexity: O(1)
    - ref: https://www.youtube.com/watch?v=rWAJCfYYOvM
    '''
    def robDp(self, nums: List[int]) -> int:
        
        if len(nums) == 1:
            return nums[0]

        def process(nums: List[int]) -> int:
            
            rob1, rob2 = 0, 0
            
            # [rob1, rob2, n, n+1, ...]
            for num in nums:
                temp = max(rob1 + num, rob2)
                rob1 = rob2
                rob2 = temp
            
            return rob2

        return max(process(nums[:-1]), process(nums[1:]))
    
    '''
    # Option #3
    - Dynamic Programming
    - O(n)
    - Space Complexity: O(n)
    '''
    def robDp2(self, nums: List[int]) -> int:
        n = len(nums)
        if n <= 2:
            return max(nums)

        def process(start: int, end: int) -> int:
            dp = [0] * (end - start + 2)
            
            for i in range(start, end):
                dp[i - start + 2] = max(nums[i] + dp[i - start], dp[i - start + 1])
            
            return dp[end - start + 1]

        return max(process(0, n - 1), process(1, n))


if __name__ == '__main__':
    sol = Solution()
    print(sol.rob([2,3,2])) # 3
    print(sol.rob([1,2,3,1])) # 4
    print(sol.rob([1,2,3])) # 3
    print(sol.rob([1,3,1,3,100])) # 103

    print("---")
    print(sol.robDp([2,3,2])) # 3
    print(sol.robDp([1,2,3,1])) # 4
    print(sol.robDp([1,2,3])) # 3
    print(sol.robDp([1,3,1,3,100])) # 103