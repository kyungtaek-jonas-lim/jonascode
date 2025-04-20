
'''
# Problem
	- `Link`: https://leetcode.com/problems/palindromic-substrings/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 20
	- `Answer`: countSubstrings
'''
class Solution:

    '''
    # Option #1
    - O(n^2)
    '''
    def countSubStrings(self, s: str) -> int:
        len_s = len(s)
        result = len_s
        for i in range(1, len_s):
            left, right = i - 1, i + 1
            while left >= 0 and right < len_s and s[left] == s[right]:
                result += 1
                left -= 1
                right += 1
            left, right = i - 1, i
            while left >= 0 and right < len_s and s[left] == s[right]:
                result += 1
                left -= 1
                right += 1
        return result