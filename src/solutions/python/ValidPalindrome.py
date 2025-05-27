
'''
# Problem
	- `Link`: https://leetcode.com/problems/valid-palindrome/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 17
	- `Answer`: isPalindrome / isPalindromeAdvanced / isPalindromeAdvanced2
'''
class Solution:

    '''
	# Option #1
	- Compare valid strings(in order, in reverse) with builder
	- O(n)
	- Space Complexity: O(n)
    '''
    def isPalindrome(self, s: str) -> bool:

        # Convert to lowercase
        s = s.lower()
        
        # Make a string with valid characters.
        builder = []
        for c in s:
            if ord('0') <= ord(c) <= ord('9') \
                or ord('a') <= ord(c) <= ord('z'):
                builder.append(c)
        
        # If they are the same
        n = len(builder)
        for i in range(n // 2):
            if builder[i] != builder[n - 1 - i]:
                return False
        
        return True

    '''
	# Option #2
	- Two Pointers
	- O(n)
	- Space Complexity: O(1)
    '''
    def isPalindromeAdvanced(self, s: str) -> bool:

        s = s.lower().strip()
        if not s:
            return True
        
        left, right = 0, len(s) - 1
        while left < right:
            c1 = s[left]
            if not (ord('0') <= ord(c1) <= ord('9') \
                or ord('a') <= ord(c1) <= ord('z')):
                left += 1
                continue

            c2 = s[right]
            if not (ord('0') <= ord(c2) <= ord('9') \
                or ord('a') <= ord(c2) <= ord('z')):
                right -= 1
                continue

            if c1 != c2:
                return False
            
            left += 1
            right -= 1
            
        return True
    
    '''
	# Option #3
	- Two Pointers With Python Utility
	- O(n)
	- Space Complexity: O(1)
    '''
    def isPalindromeAdvanced2(self, s: str) -> bool:
        
        left, right = 0, len(s) - 1

        while left < right:

            while left < right and not s[left].isalnum():
                left += 1
            
            while left < right and not s[right].isalnum():
                right -= 1

            if s[left].lower() != s[right].lower():
                return False

            left += 1
            right -= 1
        
        return True