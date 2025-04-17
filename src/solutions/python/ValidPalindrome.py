
'''
# Problem
	- `Link`: https://leetcode.com/problems/valid-palindrome/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 17
	- `Answer`: isPalindrome / isPalindromeAdvanced
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
        s1 = "".join(builder)
        return s1 == s1[::-1]

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