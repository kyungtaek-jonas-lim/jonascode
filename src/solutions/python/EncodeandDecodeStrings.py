from typing import List
'''
# Problem
	- `Link`: https://leetcode.com/problems/encode-and-decode-strings/
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
        encoded = ""
        for s in strs:
            encoded += str(len(s)) + "#" + s
        return encoded


    def decode(self, s: str) -> List[str]:
        decoded = []
        s_len = len(s)
        current = 0
        delimeter_index = 0
        s_start = 0
        while current < s_len:

            # Last string
            delimeter_index = s.find('#', current)
            if delimeter_index == -1:
                decoded.append(s[current:])
                break
            
            # The other string
            c_len = int(s[current: delimeter_index])
            s_start = delimeter_index + 1
            current = s_start + c_len
            decoded.append(s[s_start:current])

        return decoded