
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/decode-ways/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: numDecodings / numDecodingsAdvanced
'''

class Solution:

    '''
    # Option #1
    - Dynamic Programming
    - Integer Conversion
    - O(n)
    '''
    def numDecodings(self, s: str) -> int:

        # Edge Cases
        if s.startswith("0"):
            return 0
        len_s = len(s)
        if len_s == 1:
            return 1
        
        dp = [0] * (len_s + 1)
        dp[0] = 1 # Empty string -> doing nothing is also 1 way
        dp[1] = 1 # If the length is 1, only 1 way

        for i in range(2, len_s + 1):
            target_num = int(s[i - 2:i])

            # If the second digit is valid (1-9), add ways from dp[i - 1]
            # Shouldn't be 0
            if 1 <= target_num % 10 <= 9:
                dp[i] += dp[i - 1]

            # If the target number is valid (10-26), add ways from dp[i - 2]
            if 10 <= target_num <= 26:
                dp[i] += dp[i - 2]

        return dp[len_s]


    '''
    # Option #2
    - Dynamic Programming
    - 3 Variables & Only String Comparison
    - O(n)
    '''
    def numDecodingsAdvanced(self, s: str) -> int:
        
        n = len(s)
        
        curr, next, next_next = 0, 1, 0

        for i in range(n - 1, -1, -1):
            
            if s[i] == '0':
                curr = 0
            else:
                curr = next
            
            if (i + 1 < n):
                if (s[i] == '1' or (s[i] == '2' and s[i + 1] in "0123456")):
                    curr += next_next

            next_next = next
            next = curr

        return curr
             


if __name__ == '__main__':
    sol = Solution()
    print(sol.numDecodings("12")) # 2
    print(sol.numDecodings("226")) # 3
    print(sol.numDecodings("06")) # 0
    print(sol.numDecodings("10")) # 1
    print(sol.numDecodings("26")) # 2

    print("---")
    print(sol.numDecodingsAdvanced("12")) # 2
    print(sol.numDecodingsAdvanced("226")) # 3
    print(sol.numDecodingsAdvanced("06")) # 0
    print(sol.numDecodingsAdvanced("10")) # 1
    print(sol.numDecodingsAdvanced("26")) # 2