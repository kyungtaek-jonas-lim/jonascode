from collections import Counter

'''
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025 (minWindowAdvanced) / Apr 9, 2025 (minWindow)
	- `Answer`: minWindow / minWindowAdvanced / minWindowBest / minWindowSimpleAndBest / minWindowAdditional
'''

class Solution:

    '''
    # Option #1
    - O(n + m) (n: the length of s, m: the length of t)
    '''
    def minWindow(self, s: str, t: str) -> str:
        
        # Edge Case
        len_s = len(s)
        len_t = len(t)
        if len_s == len_t:
            if sorted(s) == sorted(t):
                return s
            else:
                return ""
        
        # Get Counts of Letters of t
        t_cnt = [0] * 128
        total_required_cnt = 0
        for c in t:
            t_cnt[ord(c)] += 1
            total_required_cnt += 1

        # Compare Counts of Letters of s
        result = ""
        min_length = float('inf')
        left = -1
        right = 0
        s_cnt = [0] * 128 # Get Counts of Letters of s, which t has 
        extra_cnt = [0] * 128 # Get Extra Counts to minimize the substring whenever it exceeeds the numbers of each character of t
        matched_cnt = 0
        for c in s:
            index = ord(c)
            if t_cnt[index] > 0:

                # Initialize left
                if left < 0:
                    left = right

                # If the right amount of the character is already found.
                if s_cnt[index] == t_cnt[index]:
                    
                    extra_cnt[index] += 1
                    
                    # Narrow them down
                    while True:
                        temp_index = ord(s[left])

                        # If t has s[left] but if there's no extra character, stop
                        if t_cnt[temp_index] > 0:
                            if extra_cnt[temp_index] > 0: # Remove the extra characters
                                extra_cnt[temp_index] -= 1
                            else:
                                break
                        left += 1

                # For the character, it encountered not enough numbers yet.
                else:
                    s_cnt[index] += 1
                    matched_cnt += 1

                # Update result
                if total_required_cnt == matched_cnt:
                    if min_length > right - left:
                        min_length = right - left
                        result = s[left:right + 1]
            right += 1
        return result


    '''
    # Option #2
    - O(n + m) (n: the length of s, m: the length of t)
    '''
    def minWindowAdvanced(self, s: str, t: str) -> str:
        
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



    '''
    # Option #3
    - O(n + m) (n: the length of s, m: the length of t)
    '''
    def minWindowBest(self, s: str, t: str) -> str:
        if not s or not t:
            return ""

        # Count required characters
        t_count = Counter(t) # dict conversion (character: count)
        required = len(t_count)

        # Sliding window pointers and tracking
        left = 0
        formed = 0
        window_counts = {}
        min_len = float("inf")
        min_window = (0, 0)

        for right, c in enumerate(s):
            # Count current character in window
            window_counts[c] = window_counts.get(c, 0) + 1

            # Check if current character's count matches t's requirement
            if c in t_count and window_counts[c] == t_count[c]:
                formed += 1

            # Try to contract the window while it's valid
            while formed == required:
                if right - left + 1 < min_len:
                    min_len = right - left + 1
                    min_window = (left, right)

                # Shrink the window
                char_left = s[left]
                window_counts[char_left] -= 1
                if char_left in t_count and window_counts[char_left] < t_count[char_left]:
                    formed -= 1
                left += 1

        l, r = min_window
        return s[l:r+1] if min_len != float("inf") else ""

    '''
    # Option #4
    - O(n + m) (n: the length of s, m: the length of t)
    '''
    def minWindowSimpleAndBest(self, s: str, t: str) -> str:
        
        number_of_characters = {}
        for c in t:
            number_of_characters[c] = number_of_characters.get(c, 0) + 1

        result = [-1, float('inf')]
        left, n = 0, len(s)
        curr_cnt, goal_cnt = 0, len(number_of_characters)
        number_of_current = {}

        for i in range(n):
            c = s[i]
            if c not in number_of_characters:
                continue

            number_of_current[c] = number_of_current.get(c, 0) + 1
            if number_of_current[c] == number_of_characters[c]:
                curr_cnt += 1

            while curr_cnt == goal_cnt:
                if i - left < result[1] - result[0]:
                    result[1] = i
                    result[0] = left
                
                c_temp = s[left]
                left += 1
                if c_temp in number_of_current:
                    number_of_current[c_temp] -= 1
                    if number_of_current[c_temp] < number_of_characters[c_temp]:
                        curr_cnt -= 1

        if result[0] < 0:
            return ""
        return s[result[0]:result[1] + 1]

    '''
    # Option #5
    - O(n + m)
    - Complicated
    - Jan 2, 2026
    '''
    def minWindowAdditional(self, s: str, t: str) -> str:
        
        n, m = len(s), len(t)
        if n < m: return ""
        if n == m:
            if sorted(s) != sorted(t):
                return ""
            return s
        
        valid_char_cnts = {}
        valid_char_total_cnt = 0

        curr_valid_char_cnt = {}
        curr_valid_char_total_cnt = 0

        for c in t:
            if c in valid_char_cnts:
                valid_char_cnts[c] = valid_char_cnts[c] + 1
            else:
                valid_char_total_cnt += 1
                valid_char_cnts[c] = 1
                curr_valid_char_cnt[c] = 0

        left = 0
        result = s + t
        for i in range(n):
            if s[i] in valid_char_cnts:
                left = i
                break
        
        for i in range(left, n):
            c = s[i]
            if c in valid_char_cnts:
                curr_valid_char_cnt[c] = curr_valid_char_cnt[c] + 1
                goal_cnt, curr_cnt = valid_char_cnts[c], curr_valid_char_cnt[c]

                if goal_cnt == curr_cnt:
                    curr_valid_char_total_cnt += 1
                elif goal_cnt < curr_cnt:
                    tobe_left = left
                    tobe_left_char = s[tobe_left]
                    while (not tobe_left_char in valid_char_cnts) or (curr_valid_char_cnt[tobe_left_char] > valid_char_cnts[tobe_left_char]):
                        if tobe_left_char in valid_char_cnts:
                            curr_valid_char_cnt[tobe_left_char] = curr_valid_char_cnt[tobe_left_char] - 1
                        tobe_left += 1
                        tobe_left_char = s[tobe_left]

                    left = tobe_left

                if curr_valid_char_total_cnt == valid_char_total_cnt and len(result) > (i - left + 1):
                    result = s[left:i + 1]
                

        if result == s + t:
            return ""
        return result
        

if __name__ == '__main__':
    sol = Solution()
    print(sol.minWindow("BANCADOBECODE", "ABC")) # "BANC"
    print(sol.minWindow("ADOBECODEBANC", "ABC")) # "BANC"
    print(sol.minWindow("a", "a")) # "a"
    print(sol.minWindow("a", "aa")) # ""
    print(sol.minWindow("abc", "cba")) # "abc"
    print(sol.minWindow("bdab", "ab")) # "ab"