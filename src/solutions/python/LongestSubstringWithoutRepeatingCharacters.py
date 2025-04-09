from collections import deque
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 9, 2025
 	- `Answer`: lengthOfLongestSubstring / lengthOfLongestSubstringBetter / lengthOfLongestSubstringAdvanced / lengthOfLongestSubstringBest / lengthOfLongestSubstringBestOfBest
'''

class Solution:

    '''
    # Option #1
    - deque only
    - O(n^2)
    '''
    def lengthOfLongestSubstring(self, s: str) -> int:
        result = 0
        my_deque = deque()
        for c in s:
            if c in my_deque:
                while True:
                    item = my_deque.popleft()
                    if item == c:
                        break
            my_deque.append(c)
            result = max(result, len(my_deque))
        return result
    

    '''
    # Option #2
    - deque + set
    - O(n)
    '''
    def lengthOfLongestSubstringBetter(self, s: str) -> int:
        result = 0
        my_set = set()
        my_deque = deque()

        for c in s:
            if c in my_set:
                while True:
                    item = my_deque.popleft()
                    my_set.remove(item)
                    if item == c:
                        break
            my_set.add(c)
            my_deque.append(c)
            result = max(result, len(my_deque))
    
        return result


    '''
    # Option #3
    - Sliding Window + Index Map (dict + index)
    - O(n)
    - In LeetCode, it's the slowest
    '''
    def lengthOfLongestSubstringAdvanced(self, s: str) -> int:
        result = 0
        map = {}
        sliced = 0
        for i, c in enumerate(s, start=1):
            if c in map.keys():
                sliced = max(map[c], sliced)
            result = max(i - sliced, result)
            map[c] = i

        return result


    '''
    # Option #4
    - set + two pointers
    - O(n)
    '''
    def lengthOfLongestSubstringBest(self, s: str) -> int:
        my_set = set()
        left = right = res = 0
        '''
        left = Valid Starting Index
        right = Current index
        '''
        for c in s:
            while c in my_set:
                my_set.remove(s[left])
                left += 1
            my_set.add(c)
            res = max(res, right - left + 1)
            right += 1
        return res
    

    '''
    # Option #4
    - Best of Best
    - O(n) - cf> HashMap.put: O(1), HashMap.get: O(1)
    '''
    def lengthOfLongestSubstringBestOfBest(s: str) -> int:
        map = {} # char -> last seen index
        left = 0 # start of current valid window
        result = 0

        for right, c in enumerate(s):
            if c in map and map[c] >= left:
                left = map[c] + 1  # jump directly

            map[c] = right
            result = max(result, right - left + 1)

        return result



if __name__ == "__main__":
    sol = Solution()
    print(sol.lengthOfLongestSubstring("abcabcbb"))
    print(sol.lengthOfLongestSubstring("bbbbb"))
    print(sol.lengthOfLongestSubstring("pwwkew"))