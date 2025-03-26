
'''
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025
	- `Answer`: minWindow
'''

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        
        # Edge Case #1: if the target string is shorter
        if len(t) > len(s):
            return ""
        
        # Edge Case #2: If the lengths are the same
        if len(t) == len(s):
            if "".join(sorted(t)) != "".join(sorted(s)):
                return ""
            else:
                return s
        
        # Store the candidate characters in an array
        candidate_cnt = [0] * 128
        candidate_total_character_cnt = 0
        for c in t:
            if not candidate_cnt[ord(c)]:
                candidate_total_character_cnt += 1
            candidate_cnt[ord(c)] += 1
        
        # Get the minimum window substring
        queue_cnt = [0] * 128
        queue_character_total_cnt = 0
        start, end = -1, -1
        left = 0

        for right, c in enumerate(s, start=0): # the loop index will indicate 'right' index cause it's the right most index of a scanned string

            # If this is not one of the target characters, skip the turn
            if not candidate_cnt[ord(c)]:
                continue
            
            queue_cnt[ord(c)] += 1

            # If the count of the scanned target characters are the same count
            if queue_cnt[ord(c)] == candidate_cnt[ord(c)]:
                queue_character_total_cnt += 1
            
            # If all of the counts of the target characters meet the whole target string
            # Narrow them down to minimize the substring
            while queue_character_total_cnt == candidate_total_character_cnt:

                # Check the minimum length
                if (start == -1) or (end - start > right - left):
                    start = left
                    end = right
                
                # Move to the left index to the right cause it's already checked with the minimum size of the required substring.
                c_temp = s[left]
                if queue_cnt[ord(c_temp)] > 0:
                    queue_cnt[ord(c_temp)] -= 1

                    if queue_cnt[ord(c_temp)] < candidate_cnt[ord(c_temp)]:
                        queue_character_total_cnt -= 1
                
                # Move the left index to the right and see if it's still required.
                left += 1

        return "" if start == -1 else s[start:end + 1]


        

if __name__ == '__main__':
    sol = Solution()
    print(sol.minWindow("BANCADOBECODE", "ABC")) # "BANC"
    print(sol.minWindow("ADOBECODEBANC", "ABC")) # "BANC"
    print(sol.minWindow("a", "a")) # "a"
    print(sol.minWindow("a", "aa")) # ""
    print(sol.minWindow("abc", "cba")) # "abc"
    print(sol.minWindow("bdab", "ab")) # "ab"