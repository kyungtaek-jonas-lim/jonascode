
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
    - Only String Comparison
    - O(n)
    - ref: https://www.youtube.com/watch?v=6aEyTjOwlJU
    '''
    def numDecodingsAdvanced(self, s: str) -> int:
        '''
        #1
        Start from the last character and move backward.

        #2
        Add the number of ways from dp[i + 1] if the current digit is between 1 and 9 (inclusive).

        #3
        Add the number of ways from dp[i + 2] if the two-digit number formed by s[i] and s[i + 1] is valid:
            - If s[i] is '1', s[i + 1] can be any digit (i.e., '10' to '19')
            - If s[i] is '2', s[i + 1] must be between '0' and '6' (i.e., '20' to '26')
        '''

        len_s = len(s)
        dp = [0] * len_s
        dp += [1]

        for i in range(len_s - 1, -1, -1):
            if s[i] == "0":
               continue

            dp[i] = dp[i + 1]

            if i + 1 < len_s:
                if (s[i] == "1" or (s[i] == "2" and s[i + 1] in "0123456")):
                    dp[i] += dp[i + 2]
            
        return dp[0]
             


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