from typing import List
import copy
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/word-break/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 22, 2025
 	- `Answer`: wordBreak
'''
class Solution:
    
    '''
    # Option #1
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * m) (m: max word Lenth)
    '''
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        # Convert the word list to a set for faster lookups (O(1) average case)
        word_set = set(wordDict)

        # Precompute the maximum length of words in the dictionary.
        # This allows us to avoid unnecessary checks for substrings longer than any word.
        max_len = max(map(len, word_set)) if word_set else 0

        # dp[i] is True if s[0:i] can be segmented using words from the dictionary.
        dp = [False] * (len(s) + 1)

        # Base case: empty string is always segmentable
        dp[0] = True

        # Loop over the length of the prefix we're checking (1 to len(s))
        for i in range(1, len(s) + 1):
            # Instead of checking all j from 0 to i,
            # we only check substrings of lengths that match possible word lengths.
            # This improves performance significantly when wordDict contains short words.
            for j in range(1, min(i, max_len) + 1):
                # If the prefix up to i - l is not segmentable, skip
                if not dp[i - j]:
                    continue

                # Extract the substring of length j ending at i
                if s[i - j:i] in word_set:
                    dp[i] = True
                    break # No need to check longer substrings once a match is found
        
        # The final result tells us whether the full string s[0:len(s)] is segmentable
        return dp[len(s)]

    '''
    # Option #2
    - Brute-force approach
        - Devide the string to substrings and put them into a string array.
        - Check if it's possible to make the original string out of wordDict strings.
    - O(2^n x m x k) (n: string length, m: wordDict length)
    - Time Limit Exceeded
    '''
    # def wordBreak(self, s: str, wordDict: List[str]) -> bool:
    #     s_list = [s]
    #     return self.process(s_list, wordDict)
    
    # def process(self, s_list: List[str], wordDict: List[str]) -> bool:
    #     if not s_list:
    #         return True
        
    #     for i, s in enumerate(s_list):
    #         for word in wordDict:
    #             if word in s:
    #                 index = s.find(word)
    #                 s_list_temp = copy.deepcopy(s_list)
    #                 del s_list_temp[i]
    #                 insert_index = i
    #                 if s[:index]:
    #                     s_list_temp.insert(insert_index, s[:index])
    #                     insert_index += 1
    #                 if s[index + len(word):]:
    #                     s_list_temp.insert(insert_index, s[index + len(word):])
    #                 if self.process(s_list_temp, wordDict):
    #                     return True
    #     return False
    
    
if __name__ == "__main__":
    sol = Solution()
    print(sol.wordBreak("leetcode", ["leet","code"]) == True)
    print(sol.wordBreak("applepenapple", ["apple","pen"]) == True)
    print(sol.wordBreak("catsandog", ["cats","dog","sand","and","cat"]) == False)
    print(sol.wordBreak("cars", ["car","ca","rs"]) == True)
    print(sol.wordBreak("ccbb", ["bc","cb"]) == False)