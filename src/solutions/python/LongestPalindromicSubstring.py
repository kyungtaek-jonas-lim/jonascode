
'''
# Problem
	- `Link`: https://leetcode.com/problems/longest-palindromic-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 17
	- `Answer`: longestPalindrome
'''
class Solution:

    '''
    # Option #1
    - Two Pointers & Devide Even and Odd cases
    - O(n^2)
    - ref: https://www.youtube.com/watch?v=XYQecbcd6_c
    '''
    def longestPalindrome(self, s: str) -> str:

        len_s = len(s)
        
        result = s[0]
        len_result = 1
        for i in range(1, len_s):

            # odd case
            left = right = i
            while left >= 0 and right < len_s and s[left] == s[right]:
                if right - left + 1 > len_result:
                    result = s[left:right+1]
                    len_result = right - left + 1
                left -= 1
                right += 1

            # even case
            left = i - 1
            right = i
            while left >= 0 and right < len_s and s[left] == s[right]:
                if right - left + 1 > len_result:
                    result = s[left:right+1]
                    len_result = right - left + 1
                left -= 1
                right += 1
        
        return result