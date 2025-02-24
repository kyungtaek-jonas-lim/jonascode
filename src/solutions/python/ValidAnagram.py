from collections import Counter
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/valid-anagram/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 24, 2025
 	- `Answer`: isAnagram / isAnagramAdvanced
 # Reference
 	- Anagrams
 		- Both strings must have the same length.
		- Both strings must have the exact same character counts.
'''

class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        
        my_array = [0] * 128

        for c in t:
            my_array[ord(c)] += 1
        
        for c in s:
            my_array[ord(c)] -= 1
            if my_array[ord(c)] < 0:
                return False
        return True
    
    def isAnagramAdvanced(self, s: str, t: str) -> bool:
        return Counter(s) == Counter(t)


if __name__ == "__main__":
    sol = Solution()
    print(sol.isAnagram("anagram", "nagaram"))
    print(sol.isAnagram("rat", "car"))