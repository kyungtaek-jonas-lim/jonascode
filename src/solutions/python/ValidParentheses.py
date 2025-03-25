'''
# Problem
	- `Link`: https://leetcode.com/problems/valid-parentheses/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025
	- `Answer`: isValid / isValidAdvanced
'''

class Solution:

    '''
    # Option #1
    - O(N)
    '''
    def isValid(self, s: str) -> bool:
        my_list = list()
        for c in s:
            if c == ')':
                if not my_list or my_list.pop() != '(':
                    return False
            elif c == '}':
                if not my_list or my_list.pop() != '{':
                    return False
            elif c == ']':
                if not my_list or my_list.pop() != '[':
                    return False
            else:
                my_list.append(c)
        return not my_list

    '''
    # Option #2
    - O(N)
    - A little bit more organized source code than Option #1
    '''
    def isValidAdvanced(self, s: str) -> bool:
        my_list = []
        my_dict = {'}': '{', ')': '(', ']': '['}
        
        for c in s:
            if c in my_dict:
                top = my_list.pop() if my_list else '#'
                if my_dict[c] != top:
                    return False
            else:
                my_list.append(c)
        
        return not my_list
            

if __name__ == '__main__':
    sol = Solution()
    print(sol.isValid("()")) # true
    print(sol.isValid("()[]{}")) # true
    print(sol.isValid("(]")) # false
    print(sol.isValid("([])")) # true

    print("---")

    print(sol.isValidAdvanced("()")) # true
    print(sol.isValidAdvanced("()[]{}")) # true
    print(sol.isValidAdvanced("(]")) # false
    print(sol.isValidAdvanced("([])")) # true