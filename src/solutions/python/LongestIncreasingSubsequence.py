from typing import List
import bisect

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-increasing-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 18, 2025
 	- `Answer`: lengthOfLIS / lengthOfLISAdvanced / lengthOfLISMap / lengthOfLISDp
 '''
class Solution:

    # Dynamic Programming
    # O(n^2)
    def lengthOfLIS(self, nums: List[int]) -> int:
        
        nums_len = len(nums)

        # dp[i] will store the length of the longest increasing subsequence ending at index i.
        dp = [1] * nums_len # Initialize dp array where each element starts with 1 (minimum value).

        # Iterate over each element in the array.
        result = 1
        for i in range(nums_len):
            # For each element nums[i], compare it with all previous elements nums[j] where j < i.
            for j in range(i):
                # If nums[j] < nums[i], nums[i] can extend the increasing subsequence ending at j.
                if nums[j] < nums[i]:
                    # Update dp[i] to be the maximum length between the current subsequence ending at i and the one that can be extended by nums[i]
                    dp[i] = max(dp[i], dp[j] + 1)
            result = max(dp[i], result)
        
        # The result will be the maximum value in dp because that represents the longest increasing subsequence across all indices.
        return result


    # Binary Search with Dynamic Programming
    # O(n log n)
    def lengthOfLISAdvanced(self, nums: List[int]) -> int:
        
        # tails will store the smallest possible tail value for increasing subsequences of different lengths
        tails = []

        # Iterate through each number in the nums array
        for num in nums:
            # Find the index in tails where num can replace or extend the current subsequence
            idx = bisect.bisect_left(tails, num) # Binary search for the index of the smallest value >= num

            if idx == len(tails): # If num is greater than all elements in tails, append it
                tails.append(num)
            else: # Otherwise, replace the element at idx with num, as it would form a smaller tail
                tails[idx] = num
                '''
                As you process each number in the list, you're constantly checking where it could fit into an existing increasing subsequence. If the number can replace an existing value in the tails list (i.e. one that represents a potential subsequence ending), then you should update it — because a smaller value in that position increases the chance of building a longer subsequence later on.
                '''
        
        # The length of the tails array represents the length of the longest increasing subsequence
        return len(tails)


    # Map
    # O(n^2)
    # Jan 12, 2026
    def lengthOfLISMap(self, nums: List[int]) -> int:
        
        memo = {} # Key: past numbers, Value: Count from the past
        result = 0

        for num in nums:
            for n in list(memo.keys()): # Use 'list' to prevent from changing the size of the dictionary during iteration.
                if n >= num: continue
                currCnt = memo[n]
                result = max(currCnt + 1, result)
                
                existingCnt = memo.get(num, 0)
                if existingCnt <= currCnt:
                    memo[num] = currCnt + 1
                    
            if num not in memo:
                memo[num] = 1
                

        return result if result != 0 else 1
    

    # Dynamic Programming
    # O(n^2)
    # Jan 12, 2026
    def lengthOfLISDp(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [1] * n

        for i in range(n - 1, 0, -1): # Standard
            for j in range(i - 1, -1, -1): # To the left
                if nums[j] >= nums[i]: continue
                dp[j] = max(dp[j], dp[i] + 1)

        return max(dp)



if __name__ == "__main__":
    sol = Solution()
    print(sol.lengthOfLIS([10,9,2,5,3,7,101,18])) # 4
    print(sol.lengthOfLIS([0,1,0,3,2,3])) # 4
    print(sol.lengthOfLIS([7,7,7,7,7,7,7])) # 1

    print("--")
    print(sol.lengthOfLISAdvanced([10,9,2,5,3,7,101,18])) # 4
    print(sol.lengthOfLISAdvanced([0,1,0,3,2,3])) # 4
    print(sol.lengthOfLISAdvanced([7,7,7,7,7,7,7])) # 1