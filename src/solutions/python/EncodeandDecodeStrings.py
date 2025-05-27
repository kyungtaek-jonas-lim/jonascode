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
        encoded = []
        for s in strs:
            encoded.append(f"{str(len(s))}#{s}")
        return "".join(encoded)


    def decode(self, s: str) -> List[str]:
        result = []
        n = len(s)
        curr, next, length = 0, 0, 0
        while curr < n:
            next = s.find('#', curr)
            if next == -1:
                break
            length = int(s[curr:next])
            curr = next + length + 1
            result.append(s[next + 1:curr])
        return result