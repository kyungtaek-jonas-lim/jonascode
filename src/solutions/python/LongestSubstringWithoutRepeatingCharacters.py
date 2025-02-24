from collections import deque
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 24, 2025
 	- `Answer`: lengthOfLongestSubstring
'''

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        result = 0
        my_deque = deque()
        for c in s:
            if c in my_deque:
                result = max(result, len(my_deque))
                while True:
                    popped = my_deque.popleft()
                    if popped == c:
                        break
            my_deque.append(c)
        
        result = max(result, len(my_deque))
        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.lengthOfLongestSubstring("abcabcbb"))
    print(sol.lengthOfLongestSubstring("bbbbb"))
    print(sol.lengthOfLongestSubstring("pwwkew"))