from collections import Counter
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/valid-anagram/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 24, 2025
 	- `Answer`: isAnagram / isAnagramBetter / isAnagramAdvanced / isAnagramSimple
 # Reference
 	- Anagrams
 		- Both strings must have the same length.
		- Both strings must have the exact same character counts.
'''

class Solution:
    
    '''
    # Option #1
    - Common
    - O(n log n)
    '''
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False
        return sorted(s) == sorted(t)
    
    '''
    # Option #2
    - Simple
    - O(n)
    '''
    def isAnagramBetter(self, s: str, t: str) -> bool:
        return Counter(s) == Counter(t)
    
    '''
    # Option #3
    - Best
    - O(n)
    '''
    def isAnagramAdvanced(self, s: str, t: str) -> bool:
        
        if len(s) != len(t):
            return False

        char_s = [0] * 128

        for c in s:
            char_s[ord(c)] += 1
        
        for c in t:
            index = ord(c)
            char_s[index] -= 1
            if char_s[index] < 0:
                return False
            
        return True
    
    '''
    # Option #4
    - Simple
    - O(n)
    '''
    def isAnagramSimple(self, s: str, t: str) -> bool:
        
        n, m = len(s), len(t)
        if n != m: return False

        my_dict = {}

        for i in range(n):
            my_dict[s[i]] = my_dict.get(s[i], 0) + 1

        for i in range(n):
            if my_dict.get(t[i], 0) == 0:
                return False
            my_dict[t[i]] -= 1
        
        return True


if __name__ == "__main__":
    sol = Solution()
    print(sol.isAnagram("anagram", "nagaram"))
    print(sol.isAnagram("rat", "car"))