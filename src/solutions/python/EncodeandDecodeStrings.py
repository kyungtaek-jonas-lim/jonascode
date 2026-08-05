from typing import List
'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/encode-and-decode-strings/
        - `LintCode`: https://www.lintcode.com/problem/659/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 20
	- `Answer`: encode / decode
'''
class Solution:

    '''
    # Option #1
    - Using Header (metadata in it)
    '''
    def encode(self, strs: List[str]) -> str:
        result = []
        for s in strs:
            result.extend(str(len(s)), '#', s)
            
        return ''.join(result)

    def decode(self, str: str) -> List[str]:
        result = []
        start, n = 0, len(str)

        while start < n:
            sharp = str.find('#', start)
            count = int(str[start:sharp])
            start = sharp + 1 + count
            result.append(str[sharp + 1: start])
        
        return result